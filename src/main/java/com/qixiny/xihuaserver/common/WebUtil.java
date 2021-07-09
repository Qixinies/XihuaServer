package com.qixiny.xihuaserver.common;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.TypeReference;
import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.qixiny.xihuaserver.pojo.User;

import java.util.Map;

public class WebUtil {
    public static Map<String, String> ConvertJsonToMap(String content){
        return JSON.parseObject(content, new TypeReference<Map<String, String>>() {});
    }
}
