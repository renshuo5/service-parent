package com.renshuo.cloud.autocode.model;

import com.renshuo.cloud.autocode.domain.Field;
import io.swagger.annotations.ApiModel;
import lombok.Data;

import javax.validation.constraints.NotEmpty;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Lenovo on 2023/10/30.
 */
@ApiModel(value = "AutoCodeModel", description = "自动生成代码")
@Data
public class AutoCodeModel {

    /**
     * 模块名
     **/
    private String module;
    /**
     * 表名
     **/
    @NotEmpty(message = "表名不能为空")
    private String tableName;

    /**
     * 作者
     */
    private String auth;


}
