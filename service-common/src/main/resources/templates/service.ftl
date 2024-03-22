package com.renshuo.cloud.${domain.module}.service;

import com.renshuo.cloud.annation.Mybatis;
import com.renshuo.cloud.service.impl.BaseService;
import com.github.pagehelper.PageInfo;
import com.renshuo.cloud.util.DateUtil;
import com.renshuo.cloud.reqbean.PagerInfo;
import com.renshuo.cloud.util.PagerInfoUtil;
import com.renshuo.cloud.util.UtilHelper;
import com.renshuo.cloud.util.MapperUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import com.alibaba.excel.EasyExcel;
import com.alibaba.excel.ExcelWriter;
import com.alibaba.excel.write.metadata.WriteSheet;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;

import com.renshuo.cloud.${domain.module}.domain.${domain.name};
import com.renshuo.cloud.${domain.module}.model.${domain.name}Model;
import com.renshuo.cloud.${domain.module}.excelModel.${domain.name}ExcelModel;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.List;
import java.util.Map;
import java.util.Collections;
import java.util.HashMap;
import java.util.stream.Collectors;

/**
* @description: ${domain.comments}业务层
* @author: renshuo
* @date: ${date}
*/
@Slf4j
@Service
@Mybatis(namespace="com.renshuo.cloud.${domain.module}.dao.${domain.name}Mapper")
public class ${domain.name}Service extends BaseService {

    public PageInfo list(PagerInfo pagerInfo){
        Map<String, Object> param = PagerInfoUtil.pageInfoToMap(pagerInfo);
        PageInfo pr = new PageInfo();
        // 非分页查询
        if (pagerInfo.getLimit() == null || pagerInfo.getLimit() <= 0) {
            List<${domain.name}> list = findBySqlId("pagerModel", param);
            pr.setList(models(list));
        } else {
            pr = this.findPagerModel("pagerModel", param, pagerInfo.getStart(), pagerInfo.getLimit());
            List<${domain.name}Model> collect = (List<${domain.name}Model>) pr.getList().stream().map(obj -> {
                ${domain.name} ${domain.instName} = (${domain.name}) obj;
                ${domain.name}Model model = ${domain.name}Model.fromEntry(${domain.instName});
                return model;
            }).collect(Collectors.toList());
            pr.setList(Collections.singletonList(collect));
        }

        return pr;
    }

    private List<${domain.name}Model> models(List<${domain.name}> entries) {
        List<${domain.name}Model> collect = entries.stream().map(${domain.instName} -> {
            ${domain.name}Model model = ${domain.name}Model.fromEntry(${domain.instName});
            return model;
        }).collect(Collectors.toList());
        return collect;
    }

    public ${domain.name}Model get(String id){
        Map<String, Object> params = new HashMap<>();
        params.put("id", id);
        ${domain.name} entry = unique("findById", params);
        if (entry == null) {
            throw new RuntimeException("对象已不存在");
        }
        ${domain.name}Model model = ${domain.name}Model.fromEntry(entry);
        return model;

    }

    public void insert(${domain.name}Model model){
        String errorInfo = validaModel(model);
        if(StringUtils.isNotBlank(errorInfo)){
            throw new RuntimeException(errorInfo);
        }

        ${domain.name} obj = new ${domain.name}();
        BeanUtils.copyProperties(model, obj);
        String id = UtilHelper.getUUID();
        String createTime = DateUtil.getNow();
        obj.setId(id);
        obj.setCreateTime(createTime);
        insert(obj);
    }

    public void update(${domain.name}Model model){
        String errorInfo = validaModel(model);
        if(StringUtils.isNotBlank(errorInfo)){
            throw new RuntimeException(errorInfo);
        }
        Map<String, Object> params = new HashMap<>();
        params.put("id", model.getId());
        ${domain.name} entry = unique("findById", params);
        if (entry == null) {
            throw new RuntimeException("车辆记录不存在");
        }
        ${domain.name} obj = new ${domain.name}();
        BeanUtils.copyProperties(model, obj);
        String updateTime = DateUtil.getNow();
        obj.setUpdateTime(updateTime);
        params.putAll(MapperUtil.Object2Map(obj));
        int res = super.updateByMap(params);
        if (res == 0) {
            throw new RuntimeException("更新失败");
        }

    }

    private String validaModel(${domain.name}Model model) {
        //返回null说明校验通过，返回校验字符串说明校验不通过
        return null;
    }

    private void setResponseInfo(HttpServletResponse response) throws UnsupportedEncodingException {
        String date = DateUtil.getNowNotBar();
        String moduleName = "${domain.comments}";
        String suffix = "xlsx";
        String fileName = String.format("%s-%s.%s", new Object[]{moduleName, date, suffix});
        response.setContentType("application/vnd.openxmlformats-officedocument.spreadsheetml.sheet;charset=utf-8");
        response.setHeader("Content-Disposition", "attachment;filename=" + URLEncoder.encode(fileName, "utf-8"));
    }

    public void exportAll(HttpServletResponse response, PagerInfo pagerInfo) {
        Map<String, Object> param = PagerInfoUtil.pageInfoToMap(pagerInfo);

        try {
            String sheetName = "${domain.comments}";
            setResponseInfo(response);

            List<${domain.name}> list = this.findBySqlId("pagerModel", param);
            List<${domain.name}ExcelModel> collect = list.stream().map(${domain.name}ExcelModel::fromEntry).collect(Collectors.toList());
            EasyExcel.write(response.getOutputStream(), ${domain.name}ExcelModel.class).sheet(sheetName).doWrite(collect);
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
            String sheetName = "${domain.comments}";
            setResponseInfo(response);
            try (ExcelWriter excelWriter = EasyExcel.write(response.getOutputStream(), ${domain.name}ExcelModel.class).build()) {
                for (int i = 0; i < pageTotal; i++) {
                    // 每次都要创建writeSheet 这里注意必须指定sheetNo 而且sheetName必须不一样
                    WriteSheet writeSheet = EasyExcel.writerSheet(i, sheetName + (i + 1)).build();
                    //在数据库分页查询
                    PageInfo pagerModel = this.findPagerModel("pagerModel", param, i, size);
                    List<${domain.name}ExcelModel> collect = ((List<${domain.name}>)pagerModel.getList()).stream().map(${domain.name}ExcelModel::fromEntry).collect(Collectors.toList());
                    excelWriter.write(collect, writeSheet);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

}