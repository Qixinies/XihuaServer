package com.qixiny.xihuaserver.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Serial implements Serializable {
    private String serial;
    private String type;
    private Date date;
}
