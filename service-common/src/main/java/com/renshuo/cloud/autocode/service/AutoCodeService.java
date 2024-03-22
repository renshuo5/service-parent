package com.renshuo.cloud.autocode.service;

import com.github.pagehelper.util.StringUtil;
import com.renshuo.cloud.annation.Mybatis;
import com.renshuo.cloud.autocode.domain.DataType;
import com.renshuo.cloud.autocode.domain.Domain;
import com.renshuo.cloud.autocode.domain.Field;
import com.renshuo.cloud.autocode.model.AutoCodeModel;
import com.renshuo.cloud.autocode.util.DataTypeConfig;
import com.renshuo.cloud.service.impl.BaseService;
import freemarker.template.Template;
import freemarker.template.TemplateException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.view.freemarker.FreeMarkerConfig;

import javax.validation.constraints.NotEmpty;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;


/**
 * @description: 自动生成代码
 * @author: renshuo
 * @date: 2023/10/30
 */
@Service
@Slf4j
@Mybatis(namespace = "com.renshuo.cloud.autocode.mapper.AutoCodeMapper")
public class AutoCodeService extends BaseService {

    @Autowired
    private FreeMarkerConfig freeMarkerConfigurer;

    @Autowired
    private DataTypeConfig dataTypeConfig;

    public Template getTemplate(String templateName) throws IOException {

        Template template = freeMarkerConfigurer.getConfiguration().getTemplate(templateName + ".ftl");
        return template;
    }


    public boolean createCodeFile(AutoCodeModel acm, Map<String, Object> map) throws IOException, TemplateException {

        map.put("auth", acm.getAuth());
        Domain domain = findByTableName(acm.getTableName(), acm.getModule());
        String tableName = domain.getName();
        map.put("domain", domain);
        map.put("tag", "#");


        String path = getPath();
//        String path2 = this.getClass().getResource("").getPath();
//        String srcBase = RequestContext.class.getResource("/").getFile();
//        log.info("path1:{},path2:{},srcBase:{}",path,path2,srcBase);

        //有xml
        if (true) {
            String templateName = "mapperJava";
            String xmlPath = String.format("%s/%s/dao/", path, domain.getModule());
            String fileName = String.format("%sMapper.java", tableName);
            generateFile(templateName, fileName, map, xmlPath);
        }
        //有xml
        if (true) {
            String templateName = "mapper";
            String xmlPath = String.format("%s/%s/dao/mapper/", path, domain.getModule());
            String fileName = String.format("%sMapper.xml", tableName);
            generateFile(templateName, fileName, map, xmlPath);
        }
        //业务层类
        if (true) {
            String templateName = "service";
            String xmlPath = String.format("%s/%s/service/", path, domain.getModule());
            String fileName = String.format("%sService.java", tableName);
            generateFile(templateName, fileName, map, xmlPath);
        }

        //查询数据库对象信息
        if (true) {
            String templateName = "domain";
            String xmlPath = String.format("%s/%s/domain/", path, domain.getModule());
            String fileName = String.format("%s.java", tableName);
            generateFile(templateName, fileName, map, xmlPath);
        }
        //传入出参对象模型
        if (true) {
            String templateName = "model";
            String xmlPath = String.format("%s/%s/model/", path, domain.getModule());
            String fileName = String.format("%sModel.java", tableName);
            generateFile(templateName, fileName, map, xmlPath);
        }

        //传入出参对象模型
        if (true) {
            String templateName = "excelModel";
            String xmlPath = String.format("%s/%s/excelModel/", path, domain.getModule());
            String fileName = String.format("%sExcelModel.java", tableName);
            generateFile(templateName, fileName, map, xmlPath);
        }
        //控制层类
        if (true) {
            String templateName = "controller";
            String xmlPath = String.format("%s/%s/controller/", path, domain.getModule());
            String fileName = String.format("%sController.java", tableName);
            generateFile(templateName, fileName, map, xmlPath);
        }


        return true;
    }

    private Domain findByTableName(@NotEmpty(message = "表名不能为空") String tableName, String defineModule) {

        Map<String, Object> params = new HashMap<>();
        params.put("table_name", tableName);
        Domain domain = unique("findByTableName", params);

        if (domain != null) {
            if (domain.getTableName().contains("_")) {


                String module = domain.getTableName().substring(0, domain.getTableName().indexOf("_"));
                if (StringUtil.isNotEmpty(defineModule)) {
                    module = defineModule;
                }
                domain.setModule(module);
                String name = domain.getTableName().substring(domain.getTableName().indexOf("_") + 1);
                domain.setName(capitalize(name));
                domain.setInstName(humpStr(name));
            }
            List<Field> list = findBySqlId("findColumnsByTableName", params);

            List<String> importList = new ArrayList<>();
            List<Field> resList = list.stream().map(f -> {
                DataType byType = dataTypeConfig.getByType(f.getType());
                f.setAllType(byType.getLocation());
                f.setType(byType.getType());
                //字段转为驼峰
                f.setAttr(humpStr(f.getName()));
                importList.add(byType.getLocation());
                return f;
            }).collect(Collectors.toList());
            domain.setFields(resList);

        }
        return domain;
    }

    public String capitalize(String str) {
        if (str == null || str.isEmpty()) {
            return str;
        } else {
            String camelCaseStr = "";
            String[] words = str.split("_"); // 按照下划线分割字符串
            for (String word : words) {
                if (word.length() > 0) { // 避免处理空字符串
                    camelCaseStr += word.substring(0, 1).toUpperCase() + word.substring(1);
                }
            }
            return camelCaseStr;
        }
    }

    public String humpStr(String str) {
        String camelCaseStr = "";

        String[] words = str.split("_"); // 按照下划线分割字符串
        for (int i = 0; i < words.length; i++) {
            String word = words[i];
            if (i == 0) {
                camelCaseStr += word;
                continue;
            }
            if (word.length() > 0) { // 避免处理空字符串
                camelCaseStr += word.substring(0, 1).toUpperCase() + word.substring(1);
            }
        }
        return camelCaseStr;
    }

    private void generateFile(String templateName, String fileName, Map<String, Object> map, String path) throws IOException, TemplateException {
        File dir = new File(path);
        if (!dir.exists()) {
            dir.mkdirs();
        }
        File f = new File(path + File.separatorChar + fileName);
        FileWriter fw = new FileWriter(f);
        BufferedWriter bw = new BufferedWriter(fw);
        Template template = getTemplate(templateName);
        template.process(map, bw);
    }

    private String getPath() {
        String projectPath = this.getClass().getClassLoader().getResource("").getPath() + "../..";
        String path = projectPath + "/src/main/java/com/renshuo/cloud";
        return path;
    }

}
