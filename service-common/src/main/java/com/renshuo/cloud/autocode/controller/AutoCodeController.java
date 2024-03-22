package com.renshuo.cloud.autocode.controller;

import com.renshuo.cloud.autocode.model.AutoCodeModel;
import com.renshuo.cloud.autocode.service.AutoCodeService;
import com.renshuo.cloud.util.DateUtil;
import freemarker.template.TemplateException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by Lenovo on 2023/10/25.
 */
@RestController
@RequestMapping("/autocode")
public class AutoCodeController {

    @Autowired
    private AutoCodeService autoCodeService;

    @PostMapping("/create")
    public String autoCode(@Validated AutoCodeModel acm){

        //编写生成代码的代码
        Map<String,Object> data = new HashMap<>();
        data.put("date", DateUtil.getShortDate());
        try {
            boolean bool = autoCodeService.createCodeFile(acm,data);
            return "代码生成成功";
        } catch (IOException e) {
            e.printStackTrace();
        } catch (TemplateException e) {
            e.printStackTrace();
        }


        return "代码生成成功";
    }

}
