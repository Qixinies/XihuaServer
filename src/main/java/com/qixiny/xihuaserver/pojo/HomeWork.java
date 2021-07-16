package com.qixiny.xihuaserver.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.Date;


@Data
@NoArgsConstructor
public class HomeWork implements Serializable {
    private int id;
    private int classId;
    private String name;
    private String type;

    private String description;
    private Date startDate;
    private Date endDate;
    private String publisher;

    public HomeWork(String name, int classId,String type, String description, Date startDate, Date endDate, String publisher) {
        this.name = name;
        this.classId = classId;
        this.type = type;
        this.description = description;
        this.startDate = startDate;
        this.endDate = endDate;
        this.publisher = publisher;
    }
}
