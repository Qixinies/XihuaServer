package com.qixiny.xihuaserver.controller;

import com.qixiny.xihuaserver.common.Result;
import com.qixiny.xihuaserver.common.Utils;
import com.qixiny.xihuaserver.pojo.User;
import com.qixiny.xihuaserver.service.HomeWorkService;
import com.qixiny.xihuaserver.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/work")
public class WorkController {
    private HomeWorkService homeWorkService;
    @Autowired
    public void setHomeWorkService(HomeWorkService homeWorkService) {
        this.homeWorkService = homeWorkService;
    }
    private UserService userService;

    @Autowired
    public void setUserService(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/all")
    public Result getAll(){
        try {
            return Result.success("获取成功",homeWorkService.getHomeWorkList());
        } catch (Exception e) {
            e.printStackTrace();
            return Result.ServerError("获取失败");
        }
    }

    @GetMapping("/addHomework")
    public Result addHomeWork(@RequestHeader("Authorization") String token, String name, String description, String startDate, String endDate){
        User user = userService.findUserByToken(token);
        if(user.getType().equals("user")){
            Utils.logger.warn("普通用户："+user.getOpenid()+"妄图创建作业");
            return Result.ClientError("你没有创建作业的权限");
        }
        if (homeWorkService.addHomeWork(user, name, description, Utils.stringToDate(startDate), Utils.stringToDate(endDate))==1) {
            Utils.logger.info("用户添加作业成功！");
            return Result.success("添加成功");
        }
        else{
            Utils.logger.info("用户添加作业失败");
            return Result.ClientError("添加失败");
        }

    }
    @GetMapping("/get")
    public Result get(int id){
        return Result.success("获取成功",homeWorkService.getHomeWorkById(id));
    }
}
