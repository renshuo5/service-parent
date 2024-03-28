package com.renshuo.cloud.sys.service;

import com.alibaba.excel.EasyExcel;
import com.alibaba.excel.ExcelWriter;
import com.alibaba.excel.write.metadata.WriteSheet;
import com.github.pagehelper.PageInfo;
import com.renshuo.cloud.annation.Mybatis;
import com.renshuo.cloud.common.exception.BusinessException;
import com.renshuo.cloud.reqbean.PagerInfo;
import com.renshuo.cloud.service.impl.BaseService;
import com.renshuo.cloud.sys.domain.MenuInfo;
import com.renshuo.cloud.sys.excelModel.MenuInfoExcelModel;
import com.renshuo.cloud.sys.model.MenuInfoModel;
import com.renshuo.cloud.sys.model.MenuInfoSelectModel;
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
import java.util.*;
import java.util.stream.Collectors;

/**
 * @description: 业务层
 * @author: renshuo
 * @date: 2024-03-18
 */
@Slf4j
@Service
@Mybatis(namespace = "com.renshuo.cloud.sys.dao.MenuInfoMapper")
public class MenuInfoService extends BaseService {

    public PageInfo list(PagerInfo pagerInfo) {
        Map<String, Object> param = PagerInfoUtil.pageInfoToMap(pagerInfo);
        PageInfo pr = new PageInfo();
        // 非分页查询
        if (pagerInfo.getLimit() == null || pagerInfo.getLimit() <= 0) {
            List<MenuInfo> list = findBySqlId("pagerModel", param);
            pr.setList(models(list));
        } else {
            pr = this.findPagerModel("pagerModel", param, pagerInfo.getStart(), pagerInfo.getLimit());
            List<MenuInfoModel> collect = (List<MenuInfoModel>) pr.getList().stream().map(obj -> {
                MenuInfo menuInfo = (MenuInfo) obj;
                MenuInfoModel model = MenuInfoModel.fromEntry(menuInfo);
                return model;
            }).collect(Collectors.toList());
            pr.setList(Collections.singletonList(collect));
        }

        return pr;
    }


    /**
     * 全部的菜单内容包括按钮
     * @return
     */
    public List<MenuInfoModel> listAll() {
        Map<String, Object> param = new HashMap<>();
        PageInfo pr = new PageInfo();
        // 非分页查询
        List<MenuInfo> list = findBySqlId("pagerModel", param);
        List<MenuInfoModel> models = models(list);

        return models;
    }


    /**
     * select下拉框使用：全部的菜单内容不包括按钮
     * @return
     */
    public List<MenuInfoSelectModel> getMenuAll(){
        Map<String, Object> param = new HashMap<>();
        PageInfo pr = new PageInfo();
        // 非分页查询
        List<MenuInfo> list = findBySqlId("findListMenu", param);

        List<MenuInfoSelectModel> collect = list.stream().map(menuInfo -> {
            MenuInfoSelectModel model = MenuInfoSelectModel.fromEntry(menuInfo);
            return model;
        }).collect(Collectors.toList());

        List<MenuInfoSelectModel> menuTree  = new ArrayList<>();

        for (MenuInfoSelectModel menu : collect) {
            if(menu.getIsRoot().intValue() == 1){
                //说明是root节点 top菜单
                menuTree.add(findSelectChildren(menu, collect));
            }

        }
        return menuTree;
    }
    /**
     * select下拉框使用：全部的菜单内容不包括按钮
     * @return
     */
    public List<MenuInfoSelectModel> menuInfosTreeCheck(){
        Map<String, Object> param = new HashMap<>();
        PageInfo pr = new PageInfo();
        // 非分页查询
        List<MenuInfo> list = findBySqlId("pagerModel", param);

        List<MenuInfoSelectModel> collect = list.stream().map(menuInfo -> {
            MenuInfoSelectModel model = MenuInfoSelectModel.fromEntry(menuInfo);
            return model;
        }).collect(Collectors.toList());

        List<MenuInfoSelectModel> menuTree  = new ArrayList<>();

        for (MenuInfoSelectModel menu : collect) {
            if(menu.getIsRoot().intValue() == 1){
                //说明是root节点 top菜单
                menuTree.add(findSelectChildren(menu, collect));
            }

        }
        return menuTree;
    }

    /**
     * 菜单使用
     * @return
     */
    public List<MenuInfoModel> getMenuTree(){

        Map<String, Object> param = new HashMap<>();
        PageInfo pr = new PageInfo();
        // 非分页查询
        List<MenuInfo> list = findBySqlId("findListMenu", param);
        List<MenuInfoModel> menus = models(list);

        List<MenuInfoModel> menuTree  = new ArrayList<>();
        for (MenuInfoModel menu : menus) {
            if(menu.getIsRoot().intValue() == 1){
                //说明是root节点 top菜单
                menuTree.add(findChildren(menu, menus));
            }

        }
        return menuTree;
    }

    /**
     * 查找节点下面的节点
     * @param parentMenu
     * @param list
     * @return
     */
    private MenuInfoModel findChildren(MenuInfoModel parentMenu, List<MenuInfoModel> list) {
        for (MenuInfoModel menu : list) {
            //递归查找叶子节点
            if(parentMenu.getId().equals(menu.getParentId())){
                if (parentMenu.getChild() == null) {
                    parentMenu.setChild(new ArrayList<>());
                }
                parentMenu.getChild().add(findChildren(menu,list));
            }
        }
        return parentMenu;
    }
    /**
     * 查找节点下面的节点
     * @param parentMenu
     * @param list
     * @return
     */
    private MenuInfoSelectModel findSelectChildren(MenuInfoSelectModel parentMenu, List<MenuInfoSelectModel> list) {
        for (MenuInfoSelectModel menu : list) {
            //递归查找叶子节点
            if(parentMenu.getId().equals(menu.getParentId())){
                if (parentMenu.getChildren() == null) {
                    parentMenu.setChildren(new ArrayList<>());
                }
                parentMenu.getChildren().add(findSelectChildren(menu,list));
            }
        }
        return parentMenu;
    }



    private List<MenuInfoModel> models(List<MenuInfo> entries) {
        List<MenuInfoModel> collect = entries.stream().map(menuInfo -> {
            MenuInfoModel model = MenuInfoModel.fromEntry(menuInfo);
            return model;
        }).collect(Collectors.toList());
        return collect;
    }

    public MenuInfoModel get(String id) {
        Map<String, Object> params = new HashMap<>();
        params.put("id", id);
        MenuInfo entry = unique("findById", params);
        if (entry == null) {
            throw new RuntimeException("对象已不存在");
        }
        MenuInfoModel model = MenuInfoModel.fromEntry(entry);
        return model;

    }

    public void insert(MenuInfoModel model) {
        String errorInfo = validaModel(model);
        if (StringUtils.isNotBlank(errorInfo)) {
            throw new RuntimeException(errorInfo);
        }
        MenuInfoModel parMenu = get(model.getParentId());
        String parentPath = parMenu.getPath();

        MenuInfo obj = new MenuInfo();
        BeanUtils.copyProperties(model, obj);
        String id = UtilHelper.getUUID();
        obj.setId(id);
        obj.setPath(parentPath+ id + "/");
        String createTime = DateUtil.getNow();
        obj.setCreateTime(createTime);
        obj.setCreateBy("renshuo");
        insert(obj);
    }

    public void update(MenuInfoModel model) {
        String errorInfo = validaModel(model);
        if (StringUtils.isNotBlank(errorInfo)) {
            throw new RuntimeException(errorInfo);
        }
        Map<String, Object> params = new HashMap<>();
        params.put("id", model.getId());
        MenuInfo entry = unique("findById", params);
        if (entry == null) {
            throw new RuntimeException("车辆记录不存在");
        }

        MenuInfoModel parMenu = get(model.getParentId());
        String parentPath = parMenu.getPath();

        MenuInfo obj = new MenuInfo();
        BeanUtils.copyProperties(model, obj);
        obj.setPath(parentPath+ model.getId() + "/");
        String updateTime = DateUtil.getNow();
        obj.setUpdateTime(updateTime);
        obj.setUpdateBy("renshuo");
        params.putAll(MapperUtil.Object2Map(obj));
        int res = super.updateByMap(params);
        if (res == 0) {
            throw new RuntimeException("更新失败");
        }

    }

    private String validaModel(MenuInfoModel model) {
        //返回null说明校验通过，返回校验字符串说明校验不通过
        if(!"0".equals(model.getParentId()) && StringUtils.isBlank(model.getParentId())) {
            throw new BusinessException("上一级不能为空");
        }
        return null;
    }

    private void setResponseInfo(HttpServletResponse response) throws UnsupportedEncodingException {
        String date = DateUtil.getNowNotBar();
        String moduleName = "";
        String suffix = "xlsx";
        String fileName = String.format("%s-%s.%s", new Object[]{moduleName, date, suffix});
        response.setContentType("application/vnd.openxmlformats-officedocument.spreadsheetml.sheet;charset=utf-8");
        response.setHeader("Content-Disposition", "attachment;filename=" + URLEncoder.encode(fileName, "utf-8"));
    }

    public void exportAll(HttpServletResponse response, PagerInfo pagerInfo) {
        Map<String, Object> param = PagerInfoUtil.pageInfoToMap(pagerInfo);

        try {
            String sheetName = "";
            setResponseInfo(response);

            List<MenuInfo> list = this.findBySqlId("pagerModel", param);
            List<MenuInfoExcelModel> collect = list.stream().map(MenuInfoExcelModel::fromEntry).collect(Collectors.toList());
            EasyExcel.write(response.getOutputStream(), MenuInfoExcelModel.class).sheet(sheetName).doWrite(collect);
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
            String sheetName = "";
            setResponseInfo(response);
            try (ExcelWriter excelWriter = EasyExcel.write(response.getOutputStream(), MenuInfoExcelModel.class).build()) {
                for (int i = 0; i < pageTotal; i++) {
                    // 每次都要创建writeSheet 这里注意必须指定sheetNo 而且sheetName必须不一样
                    WriteSheet writeSheet = EasyExcel.writerSheet(i, sheetName + (i + 1)).build();
                    //在数据库分页查询
                    PageInfo pagerModel = this.findPagerModel("pagerModel", param, i, size);
                    List<MenuInfoExcelModel> collect = ((List<MenuInfo>) pagerModel.getList()).stream().map(MenuInfoExcelModel::fromEntry).collect(Collectors.toList());
                    excelWriter.write(collect, writeSheet);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

}