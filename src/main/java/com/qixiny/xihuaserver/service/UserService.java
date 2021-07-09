package com.qixiny.xihuaserver.service;

import com.qixiny.xihuaserver.mapper.UserMapper;
import com.qixiny.xihuaserver.pojo.User;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Map;

public interface UserService {
    User findUserByID(String id);
    User findUserByToken(String token);
    int addUser(User user);
    int deleteUser(User user);
    int updateUser(User user);
    String getToken(User user);
    int registerUser(String openid, String sessionKey, Map<String, String> userInfoMap);
    String getUserTypeString(String type);
}
