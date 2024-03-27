package com.renshuo.cloud.sys.service;

import com.alibaba.excel.EasyExcel;
import com.alibaba.excel.ExcelWriter;
import com.alibaba.excel.write.metadata.WriteSheet;
import com.github.pagehelper.PageInfo;
import com.renshuo.cloud.annation.Mybatis;
import com.renshuo.cloud.reqbean.PagerInfo;
import com.renshuo.cloud.service.impl.BaseService;
import com.renshuo.cloud.sys.domain.RoleMenuLk;
import com.renshuo.cloud.sys.excelModel.RoleMenuLkExcelModel;
import com.renshuo.cloud.sys.model.RoleMenuLkModel;
import com.renshuo.cloud.util.DateUtil;
import com.renshuo.cloud.util.MapperUtil;
import com.renshuo.cloud.util.PagerInfoUtil;
import com.renshuo.cloud.util.UtilHelper;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;
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
* @description: 角色菜单中间|角色菜单中间表|rmlk业务层
* @author: renshuo
* @date: 2024-03-27
*/
@Slf4j
@Service
@Mybatis(namespace="com.renshuo.cloud.sys.dao.RoleMenuLkMapper")
public class RoleMenuLkService extends BaseService {

    public PageInfo list(PagerInfo pagerInfo){
        Map<String, Object> param = PagerInfoUtil.pageInfoToMap(pagerInfo);
        PageInfo pr = new PageInfo();
        // 非分页查询
        if (pagerInfo.getLimit() == null || pagerInfo.getLimit() <= 0) {
            List<RoleMenuLk> list = findBySqlId("pagerModel", param);
            pr.setList(models(list));
        } else {
            pr = this.findPagerModel("pagerModel", param, pagerInfo.getStart(), pagerInfo.getLimit());
            List<RoleMenuLkModel> collect = (List<RoleMenuLkModel>) pr.getList().stream().map(obj -> {
                RoleMenuLk roleMenuLk = (RoleMenuLk) obj;
                RoleMenuLkModel model = RoleMenuLkModel.fromEntry(roleMenuLk);
                return model;
            }).collect(Collectors.toList());
            pr.setList(Collections.singletonList(collect));
        }

        return pr;
    }

    private List<RoleMenuLkModel> models(List<RoleMenuLk> entries) {
        List<RoleMenuLkModel> collect = entries.stream().map(roleMenuLk -> {
            RoleMenuLkModel model = RoleMenuLkModel.fromEntry(roleMenuLk);
            return model;
        }).collect(Collectors.toList());
        return collect;
    }

    public RoleMenuLkModel get(String id){
        Map<String, Object> params = new HashMap<>();
        params.put("id", id);
        RoleMenuLk entry = unique("findById", params);
        if (entry == null) {
            throw new RuntimeException("对象已不存在");
        }
        RoleMenuLkModel model = RoleMenuLkModel.fromEntry(entry);
        return model;

    }

    public void insert(RoleMenuLkModel model){
        String errorInfo = validaModel(model);
        if(StringUtils.isNotBlank(errorInfo)){
            throw new RuntimeException(errorInfo);
        }

        RoleMenuLk obj = new RoleMenuLk();
        BeanUtils.copyProperties(model, obj);
        String id = UtilHelper.getUUID();
        String createTime = DateUtil.getNow();
        obj.setId(id);
        obj.setCreateTime(createTime);
        insert(obj);
    }

    public void update(RoleMenuLkModel model){
        String errorInfo = validaModel(model);
        if(StringUtils.isNotBlank(errorInfo)){
            throw new RuntimeException(errorInfo);
        }
        Map<String, Object> params = new HashMap<>();
        params.put("id", model.getId());
        RoleMenuLk entry = unique("findById", params);
        if (entry == null) {
            throw new RuntimeException("车辆记录不存在");
        }
        RoleMenuLk obj = new RoleMenuLk();
        BeanUtils.copyProperties(model, obj);
        params.putAll(MapperUtil.Object2Map(obj));
        int res = super.updateByMap(params);
        if (res == 0) {
            throw new RuntimeException("更新失败");
        }

    }

    private String validaModel(RoleMenuLkModel model) {
        //返回null说明校验通过，返回校验字符串说明校验不通过
        return null;
    }

    private void setResponseInfo(HttpServletResponse response) throws UnsupportedEncodingException {
        String date = DateUtil.getNowNotBar();
        String moduleName = "角色菜单中间|角色菜单中间表|rmlk";
        String suffix = "xlsx";
        String fileName = String.format("%s-%s.%s", new Object[]{moduleName, date, suffix});
        response.setContentType("application/vnd.openxmlformats-officedocument.spreadsheetml.sheet;charset=utf-8");
        response.setHeader("Content-Disposition", "attachment;filename=" + URLEncoder.encode(fileName, "utf-8"));
    }

    public void exportAll(HttpServletResponse response, PagerInfo pagerInfo) {
        Map<String, Object> param = PagerInfoUtil.pageInfoToMap(pagerInfo);

        try {
            String sheetName = "角色菜单中间|角色菜单中间表|rmlk";
            setResponseInfo(response);

            List<RoleMenuLk> list = this.findBySqlId("pagerModel", param);
            List<RoleMenuLkExcelModel> collect = list.stream().map(RoleMenuLkExcelModel::fromEntry).collect(Collectors.toList());
            EasyExcel.write(response.getOutputStream(), RoleMenuLkExcelModel.class).sheet(sheetName).doWrite(collect);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void exportPage(HttpServletResponse response, PagerInfo pagerInfo) {
        Map<String, Object> param = PagerInfoUtil.pageInfoToMap(pagerInfo);
        //每页10000条数据
        int size = 10000;
        //总条数
        int total = this.unique("pagerExcelModelCount", param);
        //总页数
        int pageTotal = total / size;
        if (total % size != 0) {
            pageTotal += 1;
        }

        try {
            String sheetName = "角色菜单中间|角色菜单中间表|rmlk";
            setResponseInfo(response);
            try (ExcelWriter excelWriter = EasyExcel.write(response.getOutputStream(), RoleMenuLkExcelModel.class).build()) {
                for (int i = 0; i < pageTotal; i++) {
                    // 每次都要创建writeSheet 这里注意必须指定sheetNo 而且sheetName必须不一样
                    WriteSheet writeSheet = EasyExcel.writerSheet(i, sheetName + (i + 1)).build();
                    //在数据库分页查询
                    PageInfo pagerModel = this.findPagerModel("pagerModel", param, i, size);
                    List<RoleMenuLkExcelModel> collect = ((List<RoleMenuLk>)pagerModel.getList()).stream().map(RoleMenuLkExcelModel::fromEntry).collect(Collectors.toList());
                    excelWriter.write(collect, writeSheet);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

}