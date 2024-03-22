package com.renshuo.cloud.device.domain;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;
import lombok.Setter;

/**
* @description: 设备维修记录表实体
* @author: renshuo
* @date: 2023-11-10
*/
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Repair {

    /** 主键 **/
    private String id;

    /** 医院id **/
    private String officeId;

    /** 设备id **/
    private String deviceId;

    /** 设备的故障id **/
    private String deviceFaultId;

    /** 维修人员 **/
    private String repairPerson;

    /** 维修人工号 **/
    private String repairPersonNo;

    /** 维修人员电话 **/
    private String repairPhone;

    /** 维修类型 0:维修 1:更改部件 2:报废 **/
    private Integer repairType;

    /** 维修价格 **/
    private Double repairPrice;

    /** 维修部件名称 **/
    private String repairParts;

    /** 维修部件价格 **/
    private Double repairPartsPrice;

    /** 维修公司 **/
    private String repairCompany;

    /** 维修结果 0: 未修复 1: 修复 **/
    private Integer repairResult;

    /** 维修保质期 到期日 **/
    private String repairExpiration;

    /** 医院方备注 **/
    private String repairHospitalNote;

    /** 维修时间 **/
    private String repairTime;

    /** 人工费用 **/
    private Double repairCost;

    /** 维修计划 **/
    private String repairPlan;

    /** 维修过程 **/
    private String repairCourse;

    /** 维修结果(文本) **/
    private String repairResultText;

    /** 删除标记 **/
    private String delFlag;

    /** 创建者 **/
    private String createBy;

    /** 创建时间 **/
    private String createTime;

    /** 更新者 **/
    private String updateBy;

    /** 更新时间 **/
    private String updateTime;

    /** 备注 **/
    private String remarks;

    /** 数字型备用字段 **/
    private Integer reserveInt;

    /** 字符型备用字段 **/
    private String reserveVarchar;


}
