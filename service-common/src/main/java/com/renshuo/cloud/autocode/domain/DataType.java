package com.renshuo.cloud.autocode.domain;

import java.util.HashSet;
import java.util.Set;

/**
 * Created by chenp on 2017-06-30.
 */
public class DataType {


    private String type;
    private String location;
    private Set<String> typeSet = new HashSet<>();


    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public DataType(String type, String location) {
        this.type = type;
        this.location = location;
    }

    public Set<String> getTypeSet() {
        return typeSet;
    }

    public void setTypeSet(Set<String> typeSet) {
        this.typeSet = typeSet;
    }

    public void add(String ...t){
        for (String s:t){
            typeSet.add(s);
        }
    }
}
