package com.qixiny.xihuaserver.pojo;

import lombok.Data;

import java.io.Serializable;

@Data
public class SchoolClass implements Serializable {
    private int id;
    private String name;
    private String description;
    public SchoolClass(String name, String description){
        this.name = name;
        this.description = description;
    }
}
