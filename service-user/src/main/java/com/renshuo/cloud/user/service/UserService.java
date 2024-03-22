package com.renshuo.cloud.user.service;

import com.alibaba.excel.EasyExcel;
import com.alibaba.excel.ExcelWriter;
import com.alibaba.excel.write.metadata.WriteSheet;
import com.github.pagehelper.PageInfo;
import com.renshuo.cloud.annation.Mybatis;
import com.renshuo.cloud.reqbean.PagerInfo;
import com.renshuo.cloud.reqbean.PagerResult;
import com.renshuo.cloud.service.impl.BaseService;
import com.renshuo.cloud.user.client.WsServiceClient;
import com.renshuo.cloud.user.domain.User;
import com.renshuo.cloud.user.model.OffLineExportPageModel;
import com.renshuo.cloud.user.model.UserModel;
import com.renshuo.cloud.util.DateUtil;
import com.renshuo.cloud.util.PagerInfoUtil;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * @description: 用户接口
 * @author: renshuo
 * @date: 2020/12/4
 */
@Service
@Slf4j
@Mybatis(namespace = "com.renshuo.cloud.user.dao.UserMapper")
public class UserService extends BaseService {

    @Autowired
    private WsServiceClient wsServiceClient;

    public PageInfo list(PagerInfo pagerInfo) {
        Map<String, Object> param = PagerInfoUtil.pageInfoToMap(pagerInfo);
        PageInfo pr = new PageInfo();
        // 非分页查询
        if (pagerInfo.getLimit() == null || pagerInfo.getLimit() <= 0) {
            List<User> list = findBySqlId("pagerModel", param);
            pr.setList(models(list));
        } else {
            pr = this.findPagerModel("pagerModel", param, pagerInfo.getStart(), pagerInfo.getLimit());
            List<UserModel> collect = (List<UserModel>) pr.getList().stream().map(obj -> {
                User user = (User) obj;
                UserModel model = UserModel.fromEntry(user);
                return model;
            }).collect(Collectors.toList());
            pr.setList(Collections.singletonList(collect));
        }

        return pr;
    }

    private List<UserModel> models(List<User> entries) {

        List<UserModel> collect = entries.stream().map(user -> {
            UserModel model = UserModel.fromEntry(user);

            return model;
        }).collect(Collectors.toList());

//        List<UserModel> models = new ArrayList<>();
//        for (User entry : entries) {
//            UserModel model = UserModel.fromEntry(entry);
//            models.add(model);
//        }
        return collect;
    }

    public UserModel get(String id){
        Map<String, Object> params = new HashMap<>();
        params.put("id", id);
        User entry = unique("findById", params);
        if (entry == null) {
            throw new RuntimeException("对象已不存在");
        }
        UserModel model = UserModel.fromEntry(entry);
        return model;

    }

    public void exportPage(HttpServletResponse response, PagerInfo pagerInfo) {
        Map<String, Object> param = PagerInfoUtil.pageInfoToMap(pagerInfo);
        //每页10000条数据
        int size = 10000;
        //总条数
        int total = this.unique("pagerModel_COUNT", param);
        int pageTotal = total / size;
        if (total % size != 0) {
            pageTotal += 1;
        }

        // 这里注意 有同学反应使用swagger 会导致各种问题，请直接用浏览器或者用postman
        // 这里URLEncoder.encode可以防止中文乱码 当然和easyexcel没有关系
        // 这里 需要指定写用哪个class去写
        try {
            String sheetName = "用户列表";
            setResponseInfo(response);
            try (ExcelWriter excelWriter = EasyExcel.write(response.getOutputStream(), UserModel.class).build()) {
                // 这里注意 如果同一个sheet只要创建一次
//                    WriteSheet writeSheet = EasyExcel.writerSheet("用户列表").build();
                // 去调用写入,这里我调用了五次，实际使用时根据数据库分页的总的页数来
                for (int i = 0; i < pageTotal; i++) {
                    // 每次都要创建writeSheet 这里注意必须指定sheetNo 而且sheetName必须不一样
                    WriteSheet writeSheet = EasyExcel.writerSheet(i, sheetName + (i + 1)).build();
                    //在数据库分页查询
                    PageInfo pagerModel = this.findPagerModel("pagerModel", param, i, size);
                    List<Object> data = pagerModel.getList();
                    excelWriter.write(data, writeSheet);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public void exportAll(HttpServletResponse response, PagerInfo pagerInfo) {
        Map<String, Object> param = PagerInfoUtil.pageInfoToMap(pagerInfo);

        // 这里注意 有同学反应使用swagger 会导致各种问题，请直接用浏览器或者用postman
        // 这里URLEncoder.encode可以防止中文乱码 当然和easyexcel没有关系
        try {
            String sheetName = "用户列表";
            setResponseInfo(response);

            List<User> list = this.findBySqlId("pagerModel", param);
            EasyExcel.write(response.getOutputStream(), UserModel.class).sheet(sheetName).doWrite(list);

//            try (ExcelWriter excelWriter = EasyExcel.write(response.getOutputStream(), User.class).build()) {
//
//                WriteSheet writeSheet = EasyExcel.writerSheet("用户列表").build();
//
//                List<User> list = this.findBySqlId("pagerModel", param);
//                excelWriter.write(list, writeSheet);
//            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void setResponseInfo(HttpServletResponse response) throws UnsupportedEncodingException {
        String date = DateUtil.getNowNotBar();
        String moduleName = "用户信息";
        String suffix = "xlsx";
        String fileName = String.format("%s-%s.%s", new Object[]{moduleName, date, suffix});
        response.setContentType("application/vnd.openxmlformats-officedocument.spreadsheetml.sheet;charset=utf-8");
        response.setHeader("Content-Disposition", "attachment;filename=" + URLEncoder.encode(fileName, "utf-8"));
    }


    public void offLineExport(HttpServletResponse response, PagerInfo pagerInfo) {
        Map<String, Object> param = PagerInfoUtil.pageInfoToMap(pagerInfo);
        //总条数
        int total = this.unique("pagerModel_COUNT", param);
        if (total > 0) {
            throw new RuntimeException("没有查询到数据");
        }

        OffLineExportPageModel pm = new OffLineExportPageModel(total);

        //1、固定页数，计算出每页的条数（pageSize）+最后一页剩下的条数（lastPageSize）
//        int pageTotal = 20;
//        int pageSize = total / pageTotal;
//        if(total % pageTotal!=0){
//            pageSize+=1;
//        }
//        int lastPageSize = total - (pageSize*(pageTotal-1));
//        if(total>=10000){
//            pageModel.setPageTotal(20);
//        }else{
//
//        }


        //2、固定每页导出条数，计算页数
        //每页100条数据
//        int size = 1000;
//        int pageTotal = total / size;
//        if (total % size != 0) {
//            pageTotal += 1;
//        }

        String date = DateUtil.getNowNotBar();
        String moduleName = "用户信息";
        String suffix = "xlsx";
        String fileName = String.format("%s-%s.%s", new Object[]{moduleName, date, suffix});

        // 这里注意 有同学反应使用swagger 会导致各种问题，请直接用浏览器或者用postman
        // 这里URLEncoder.encode可以防止中文乱码 当然和easyexcel没有关系
//        response.setContentType("application/octet-stream");
        // 这里 需要指定写用哪个class去写
        try {
            response.setContentType("application/vnd.openxmlformats-officedocument.spreadsheetml.sheet;charset=utf-8");
            response.setHeader("Content-Disposition", "attachment;filename=" + URLEncoder.encode(fileName, "utf-8"));

            try (ExcelWriter excelWriter = EasyExcel.write(response.getOutputStream(), User.class).build()) {
                // 这里注意 如果同一个sheet只要创建一次
                WriteSheet writeSheet = EasyExcel.writerSheet("用户列表").build();
                // 去调用写入,这里我调用了五次，实际使用时根据数据库分页的总的页数来
                for (int i = 0; i < pm.getPageTotal(); i++) {
                    //在数据库分页查询
                    int querySize;
                    if (i == (pm.getPageTotal() - 1)) {
                        querySize = pm.getLastPageSize();
                    } else {
                        querySize = pm.getPageSize();
                    }
                    PageInfo pagerModel = this.findPagerModel("pagerModel", param, i, querySize);
                    List<Object> data = pagerModel.getList();
                    excelWriter.write(data, writeSheet);
                }
            }
//            long fileMaxSize = 10 * 1024 * 1024;
//            if (file.getName().endsWith(".csv") && file.length() > fileMaxSize) {
//                // 更改离线任务记录的`导出文件名称`后缀名，改为zip
//                String exportFileName = task.getExportFileName().substring(0, task.getExportFileName().lastIndexOf(".")).concat(".zip");
//                // csv压缩为zip
//                String zipPath = file.getAbsolutePath().substring(0, file.getAbsolutePath().lastIndexOf(".")).concat(".zip");
//                File zFile = new File(zipPath);
//                ZipFile zipFile = new ZipFile(zFile);
//                ZipParameters parameters = new ZipParameters();
//                parameters.setCompressionMethod(Zip4jConstants.COMP_DEFLATE);
//                parameters.setCompressionLevel(Zip4jConstants.DEFLATE_LEVEL_NORMAL);
//                zipFile.addFile(file, parameters);
//            }
        } catch (IOException e) {
            e.printStackTrace();
        }
//        catch (ZipException e) {
//            e.printStackTrace();
//        }

    }

    public void sendMessage(String message) {
        log.info("开始调用ws接口发送消息");
        wsServiceClient.sendToAll(message);
    }

    //未添加baseservice时候代码开发模式
//    @Autowired
//    private UserMapper userMapper;
//
//    /**
//     * 通过ID查询单条数据
//     *
//     * @param id 主键
//     * @return 实例对象
//     */
//    public User findById(String id) {
//        return this.userMapper.findById(id);
//    }
//
//    /**
//     * 查询多条数据
//     *
//     * @param offset 查询起始位置
//     * @param limit 查询条数
//     * @return 对象列表
//     */
//    public List<User> findAllByLimit(int offset, int limit) {
//        return this.userMapper.findAllByLimit(offset, limit);
//    }
//    /**
//     * 查询多条数据
//     *
//     * @param user 对象信息
//     * @return 对象列表
//     */
//    public List<User> findAllByUser(User user) {
//        return this.userMapper.findAll(user);
//    }
//
//    /**
//     * 新增数据
//     *
//     * @param user 实例对象
//     * @return 实例对象
//     */
//    public User insert(User user) {
//        this.userMapper.insert(user);
//        return user;
//    }
//
//    /**
//     * 修改数据
//     *
//     * @param user 实例对象
//     * @return 实例对象
//     */
//    public User update(User user) {
//        this.userMapper.update(user);
//        return this.findById(String.valueOf(user.getId()));
//    }
//
//    /**
//     * 通过主键删除数据
//     *
//     * @param id 主键
//     * @return 是否成功
//     */
//    public boolean deleteById(Integer id) {
//        return this.userMapper.deleteById(id) > 0;
//    }


}
