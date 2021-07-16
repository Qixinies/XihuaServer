package com.qixiny.xihuaserver.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.Date;


@Data
@NoArgsConstructor
@AllArgsConstructor
public class User implements Serializable {
    //TODO 储存token，token过期
    private String openid;
    private String username;
    private String headImage;
    private String sessionKey;
    //TODO 客户端更改职务
    private String type;
    private String officialName;
    private Date registerDate;
    private int classId;

}
