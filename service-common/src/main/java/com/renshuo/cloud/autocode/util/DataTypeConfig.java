package com.renshuo.cloud.autocode.util;

import com.renshuo.cloud.autocode.domain.DataType;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.*;

/**
 * Created by chenp on 2017-06-30.
 */
@Component
public class DataTypeConfig {

    public DataTypeConfig() {

    }

    private List<DataType> dataTypeList = new ArrayList<>();

    public List<DataType> getDataTypeList() {
        return dataTypeList;
    }

    public void setDataTypeList(List<DataType> dataTypeList) {
        this.dataTypeList = dataTypeList;
    }

    private Map<String, DataType> typeMap = new HashMap<>();

    public Map<String, DataType> getTypeMap() {
        return typeMap;
    }

    public void setTypeMap(Map<String, DataType> typeMap) {
        this.typeMap = typeMap;
    }

    /**
     * 初始化类型配置，并转为map，方便查询
     */
    @PostConstruct
    public void init() {

        DataType tInteger = new DataType("Integer", Integer.class.getName());
        tInteger.add("tinyint", "int");
        dataTypeList.add(tInteger);

        DataType tLong = new DataType("Long", Long.class.getName());
        tInteger.add("bigint");
        dataTypeList.add(tLong);

        DataType tFloat = new DataType("Float", Float.class.getName());
        tFloat.add("float");
        dataTypeList.add(tFloat);

        DataType tDouble = new DataType("Double", Double.class.getName());
        tDouble.add("double", "real", "number");
        dataTypeList.add(tDouble);

        DataType tDecimal = new DataType("Decimal", BigDecimal.class.getName());
        tDecimal.add("decimal");
        dataTypeList.add(tDecimal);

        DataType tString = new DataType("String", String.class.getName());
        tString.add("varchar", "char", "varchar2", "text", "clob", "nchar");
        dataTypeList.add(tString);

        DataType tDate = new DataType("Timestamp", Timestamp.class.getName());
        tDate.add("timestamp", "time", "date");
        dataTypeList.add(tDate);


        for (DataType dt : dataTypeList) {

            Set<String> typeSet = dt.getTypeSet();
            for (String t : typeSet) {
                getTypeMap().put(t, dt);
            }
        }


    }

    /**
     * 根据数据类型，获取JAVA TYPE
     *
     * @param type
     * @return
     */
    public DataType getByType(String type) {

        type = type.toLowerCase();
        DataType dataType = getTypeMap().getOrDefault(type, new DataType("String", String.class.getName()));
        return dataType;
    }
}
