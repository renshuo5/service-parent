<?xml version="1.0" encoding="UTF-8"?>
<!DOCTYPE mapper PUBLIC "-//org//DTD Mapper 3.0//EN" "http://org/dtd/mybatis-3-mapper.dtd">
<mapper namespace="com.renshuo.cloud.${domain.module}.dao.${domain.name}Mapper">
    <resultMap id="${domain.instName}Results" type="com.renshuo.cloud.${domain.module}.domain.${domain.name}"
               autoMapping="true">
    </resultMap>

    <!-- 通用查询结果列 -->
    <sql id="baseColumns">
        id<#list domain.fields as f><#if f.attr!="id">,${f.name}</#if></#list>
    </sql>
    <sql id="moreColumns">
        id<#list domain.fields as f><#if f.attr!="id">,${f.name}</#if></#list>
    </sql>

    <sql id="pagerModelWhereSqlId">
        <where>

        </where>
    </sql>


    <!-- 分页查询 -->
    <select id="pagerModel" resultMap="${domain.instName}Results" parameterType="java.util.HashMap" resultOrdered="true">
        select
            <include refid="moreColumns"/>
        from
        ${domain.tableName}
        <include refid="pagerModelWhereSqlId" />
    </select>

    <!-- 分页查询 -->
    <select id="pagerModel_COUNT" resultType="java.lang.Integer" parameterType="java.util.HashMap">
        select
            count(1)
        from
        ${domain.tableName}
        <include refid="pagerModelWhereSqlId" />
    </select>

    <!-- excel分页查询 -->
    <select id="pagerExcelModelCount" resultType="java.lang.Integer" parameterType="java.util.HashMap">
        select
            count(1)
        from
        ${domain.tableName}
        <include refid="pagerModelWhereSqlId" />
    </select>

    <!-- 根据id查询 -->
    <select id="findById" resultType="com.renshuo.cloud.${domain.module}.domain.${domain.name}"
            parameterType="java.util.HashMap">
        select
        <include refid="moreColumns"/>
        from
        ${domain.tableName}
        <where>
            id=${tag}{id}
        </where>
    </select>

    <!-- 增加 -->
    <insert id="insert" parameterType="java.util.HashMap">
        insert into
        ${domain.tableName}
        (id<#list domain.fields as f><#if f.attr!="id" && f.attr!="updateBy" && f.attr!="updateTime">
        ,${f.name}</#if></#list>)
        values
        (${tag}{id}<#list domain.fields as f><#if f.name!="id" && f.attr!="updateBy" && f.attr!="updateTime">
        ,${tag}{${f.attr}}</#if></#list>)
    </insert>

    <!-- 更新 -->
    <update id="update" parameterType="java.util.HashMap">
        update ${domain.tableName} set
        id=id<#list domain.fields as f><#if f.name!="id" && f.attr!="createBy" && f.attr!="createTime">
        ,${f.name}=${tag}{${f.attr}}</#if></#list>
        <where>
            id = ${tag}{id}
        </where>

    </update>

    <!-- 删除 -->
    <delete id="delete" parameterType="java.util.HashMap">
        delete from
        ${domain.tableName}
        <where>
            id = ${tag}{id}
        </where>
    </delete>

</mapper>