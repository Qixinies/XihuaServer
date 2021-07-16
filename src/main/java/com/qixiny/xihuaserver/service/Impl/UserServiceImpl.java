package com.qixiny.xihuaserver.service.Impl;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTDecodeException;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.qixiny.xihuaserver.common.Utils;
import com.qixiny.xihuaserver.mapper.UserMapper;
import com.qixiny.xihuaserver.pojo.User;
import com.qixiny.xihuaserver.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.MessageFormat;
import java.util.Date;
import java.util.Map;


@Service
public class UserServiceImpl implements UserService {
    private static String JWT_SECRET = "FZT&QXN";
    private JWTVerifier jwtVerifier = JWT.require(Algorithm.HMAC256(JWT_SECRET)).build();
    private UserMapper userMapper;
    @Autowired
    public void setUserMapper(UserMapper userMapper) {
        this.userMapper = userMapper;
    }

    @Override
    public User findUserByID(String id) {
        return userMapper.queryUserByOpenid(id);
    }

    @Override
    public User findUserByToken(String token) {
        if (token == null) {
            return null;
        }
        System.out.println("token in service"+token);
        String openid = null;
        try {
            openid = JWT.decode(token).getAudience().get(0);
        } catch (JWTDecodeException e) {
            Utils.logger.error("Token格式不正确，Token:"+token);
            return null;
        }
        User user = findUserByID(openid);
        if (user == null) {
            Utils.logger.error("无法从Token中解析Openid");
            return null;
        }
        try {
            jwtVerifier.verify(token);
        } catch (JWTVerificationException e) {
            Utils.logger.error("成功从Token中获取用户但验证失败");
            return null;
        }
        return user;
    }

    public String getToken(User user) {
        String token= JWT.create().withAudience(user.getOpenid()).sign(Algorithm.HMAC256(JWT_SECRET));
        return token;
    }

    @Override
    public int addUser(User user) {
        return userMapper.addUser(user);
    }

    @Override
    public int deleteUser(User user) {
        return userMapper.deleteUser(user);
    }

    @Override
    public int updateUser(User user) {
        return userMapper.updateUser(user);
    }

    @Override
    public int registerUser(String openid, String sessionKey, Map<String, String> userInfoMap) {
        User user = findUserByID(openid);
        if(user==null){
            //TODO type 和 officeName为null
            User newUser = new User(openid, userInfoMap.get("nickName"),userInfoMap.get("avatarUrl"), sessionKey,null,null,new Date(),-1);
            addUser(newUser);
            Utils.logger.info(MessageFormat.format("{1}成功注册账号,id:{0}",newUser.getOpenid(),newUser.getUsername()));
        }
        //老账号=>更新数据
        else{
            user.setSessionKey(sessionKey);
            user.setUsername(userInfoMap.get("nickName"));
            user.setHeadImage(userInfoMap.get("avatarUrl"));

            updateUser(user);

            Utils.logger.info(MessageFormat.format("{1}成功更新账号Session,id：{0}",user.getOpenid(),user.getUsername()));
        }
        return 1;
    }

    @Override
    public String getUserTypeString(String type) {
        String typeName = null;
        switch (type){
            case "user":
                typeName = "用户";
                break;
            case "teacher":
                typeName = "其他老师";
                break;
            case "admin":
                typeName="测试";
                break;
            case "chinese":
                typeName="语文";
                break;
            case "math":
                typeName="数学";
                break;
            case "english":
                typeName="英语";
                break;
            default:
                Utils.logger.warn("未知的用户类型:"+type);
                return null;
        }
        return typeName;
    }

}
