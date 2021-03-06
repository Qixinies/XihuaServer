package com.qixiny.xihuaserver.controller;

import com.alibaba.fastjson.TypeReference;
import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.qixiny.xihuaserver.common.Result;
import com.qixiny.xihuaserver.common.Utils;
import com.qixiny.xihuaserver.common.WebUtil;
import com.qixiny.xihuaserver.mapper.UserMapper;
import com.qixiny.xihuaserver.pojo.User;
import com.qixiny.xihuaserver.service.SerialService;
import com.qixiny.xihuaserver.service.UserService;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;
import com.alibaba.fastjson.JSON;

import java.text.MessageFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


@RestController
@RequestMapping("/user")
public class UserController {

    private UserService userService;
    @Autowired
    public void setUserService(UserService userService) {
        this.userService = userService;
    }

    private SerialService serialService;
    @Autowired
    public void setSerialService(SerialService serialService) {
        this.serialService = serialService;
    }

    @GetMapping("/login")
    public Result login(@RequestParam("code")String code,@RequestParam("userInfo")String userinfo){

        RestTemplate restTemplate = new RestTemplate();
        String result =restTemplate.getForObject("https://api.weixin.qq.com/sns/jscode2session" +
                "?appid=wx3859ec44f66e8bed" +
                "&secret=b5892ebe24bec4e69df9fa05e3451b83" +
                "&grant_type=authorization_code" +
                "&js_code="+code,
                String.class);
        Map<String, String> resultMap = WebUtil.ConvertJsonToMap(result);
        String openid =resultMap.get("openid");
        String sessionKey = resultMap.get("session_key");

        Map<String, String> userInfoMap = WebUtil.ConvertJsonToMap(userinfo);

        if(openid==null){
            Utils.logger.error("??????code????????????");
            return Result.ClientError("?????????code????????????");
        }
        if (userService.registerUser(openid,sessionKey,userInfoMap)==1) {
            //TODO ?????????
            return Result.success("????????????",userService.getToken(userService.findUserByID(openid)));
        }
        return Result.ServerError("????????????");
    }

    @GetMapping("/getProfile")
    public Result getProfile(@RequestHeader("Authorization") String token)
    {
        User user = userService.findUserByToken(token);
        if (user == null) {
            return  Result.ClientError("????????????");
        }
        user.setSessionKey(null);
        return Result.success("????????????",user);

    }
    @GetMapping("/getByOpenid")
    public Result getUserByOpenid(String openid){
        User user = userService.findUserByID(openid);
        if (user == null) {
            return Result.ClientError("?????????????????????");
        }
        user.setSessionKey(null);
        return Result.success("????????????",user);
    }

    @GetMapping("/activateSerial")
    public Result activateSerial(@RequestHeader("Authorization") String token,@RequestParam String serial){
        User user = userService.findUserByToken(token);
        if (user == null) {
            Utils.logger.error("token??????????????????");
            return  Result.ClientError("????????????");
        }
        if (serialService.activateSerial(user,serial)==1) {
            Utils.logger.info("????????????");
            return  Result.success("????????????");
        }
        Utils.logger.info("????????????");
        return Result.ClientError("????????????");

    }
}
