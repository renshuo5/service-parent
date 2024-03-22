package com.renshuo.cloud.device.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;
import lombok.Setter;
import com.alibaba.excel.annotation.ExcelIgnore;
import com.alibaba.excel.annotation.ExcelProperty;
import io.swagger.annotations.ApiModelProperty;
import io.swagger.annotations.ApiModel;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import org.springframework.beans.BeanUtils;
import com.renshuo.cloud.device.domain.Repair;

/**
* @description: 设备维修记录表模型
* @author: renshuo
* @date: 2023-11-10
*/
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ApiModel(value = "RepairModel", description = "设备维修记录表Model")
public class RepairModel {


    @ApiModelProperty(value = "主键")
    @ExcelIgnore
    public String id;
    @ApiModelProperty(value = "医院id")
    @ExcelProperty("医院id")
    public String officeId;
    @ApiModelProperty(value = "设备id")
    @ExcelProperty("设备id")
    public String deviceId;
    @ApiModelProperty(value = "设备的故障id")
    @ExcelProperty("设备的故障id")
    public String deviceFaultId;
    @ApiModelProperty(value = "维修人员")
    @ExcelProperty("维修人员")
    public String repairPerson;
    @ApiModelProperty(value = "维修人工号")
    @ExcelProperty("维修人工号")
    public String repairPersonNo;
    @ApiModelProperty(value = "维修人员电话")
    @ExcelProperty("维修人员电话")
    public String repairPhone;
    @ApiModelProperty(value = "维修类型 0:维修 1:更改部件 2:报废")
    @ExcelProperty("维修类型 0:维修 1:更改部件 2:报废")
    public Integer repairType;
    @ApiModelProperty(value = "维修价格")
    @ExcelProperty("维修价格")
    public Double repairPrice;
    @ApiModelProperty(value = "维修部件名称")
    @ExcelProperty("维修部件名称")
    public String repairParts;
    @ApiModelProperty(value = "维修部件价格")
    @ExcelProperty("维修部件价格")
    public Double repairPartsPrice;
    @ApiModelProperty(value = "维修公司")
    @ExcelProperty("维修公司")
    public String repairCompany;
    @ApiModelProperty(value = "维修结果 0: 未修复 1: 修复")
    @ExcelProperty("维修结果 0: 未修复 1: 修复")
    public Integer repairResult;
    @ApiModelProperty(value = "维修保质期 到期日")
    @ExcelProperty("维修保质期 到期日")
    public String repairExpiration;
    @ApiModelProperty(value = "医院方备注")
    @ExcelProperty("医院方备注")
    public String repairHospitalNote;
    @ApiModelProperty(value = "维修时间")
    @ExcelProperty("维修时间")
    public String repairTime;
    @ApiModelProperty(value = "人工费用")
    @ExcelProperty("人工费用")
    public Double repairCost;
    @ApiModelProperty(value = "维修计划")
    @ExcelProperty("维修计划")
    public String repairPlan;
    @ApiModelProperty(value = "维修过程")
    @ExcelProperty("维修过程")
    public String repairCourse;
    @ApiModelProperty(value = "维修结果(文本)")
    @ExcelProperty("维修结果(文本)")
    public String repairResultText;
    @ApiModelProperty(value = "删除标记")
    @ExcelProperty("删除标记")
    public String delFlag;

    @ApiModelProperty(value = "创建者")
    @ExcelIgnore
    public String createBy;

    @ApiModelProperty(value = "创建时间")
    @ExcelIgnore
    public String createTime;

    @ApiModelProperty(value = "更新者")
    @ExcelIgnore
    public String updateBy;

    @ApiModelProperty(value = "更新时间")
    @ExcelIgnore
    public String updateTime;
    @ApiModelProperty(value = "备注")
    @ExcelProperty("备注")
    public String remarks;
    @ApiModelProperty(value = "数字型备用字段")
    @ExcelProperty("数字型备用字段")
    public Integer reserveInt;
    @ApiModelProperty(value = "字符型备用字段")
    @ExcelProperty("字符型备用字段")
    public String reserveVarchar;

    /**
    * 将实体转为前台model
    *
    * @param entry {@link Repair}
    * @return {@link RepairModel}
    */
    public static RepairModel fromEntry(Repair entry) {
        RepairModel m = new RepairModel();
        BeanUtils.copyProperties(entry, m);
        return m;
    }


}