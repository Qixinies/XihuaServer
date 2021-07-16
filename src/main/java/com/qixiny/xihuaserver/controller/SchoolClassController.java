package com.qixiny.xihuaserver.controller;

import com.qixiny.xihuaserver.common.Result;
import com.qixiny.xihuaserver.common.Utils;
import com.qixiny.xihuaserver.pojo.SchoolClass;
import com.qixiny.xihuaserver.pojo.User;
import com.qixiny.xihuaserver.service.SchoolClassService;
import com.qixiny.xihuaserver.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/class")
public class SchoolClassController {
    private SchoolClassService schoolClassService;
    @Autowired
    public void setSchoolClassService(SchoolClassService schoolClassService) { this.schoolClassService = schoolClassService; }

    private UserService userService;
    @Autowired
    public void setUserService(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/all")
    public Result all(){
        return Result.success("查询成功",schoolClassService.findAll());
    }

    @GetMapping("/find")
    public Result find(int id){
        SchoolClass schoolClass = schoolClassService.find(id);
        if (schoolClass != null) {
            return Result.success("查询成功",schoolClass);
        }
        return Result.ClientError("找不到指定项");
    }
    @GetMapping("create")
    public Result createNewClass(@RequestHeader("Authorization") String token,String name,String description){
        User user = userService.findUserByToken(token);
        if (user == null) {
            return Result.ClientError("你输入token无效");
        }
        SchoolClass schoolClass = new SchoolClass(name, description);
        if (schoolClassService.add(schoolClass)==1) {
            Utils.logger.info("班级创建成功,ID为："+schoolClass.getId());
            user.setClassId(schoolClass.getId());
            userService.updateUser(user);
            return Result.success("创建成功");
        }
        else {
            return Result.ServerError("创建失败");
        }
    }
    @GetMapping("/getMyClass")
    public Result getMyClass(@RequestHeader("Authorization") String token){
        User user = userService.findUserByToken(token);
        if (user == null) {
            return Result.ClientError("输入token无效");
        }
        int classId = user.getClassId();
        if(classId==-1 || classId==0){
            return Result.success("你没有加入任何学团");
        }
        else{
            SchoolClass schoolClass =  schoolClassService.find(classId);
            return Result.success("查询成功",schoolClass);
        }
    }
    @GetMapping("/join")
    public Result joinClass(@RequestHeader("Authorization") String token,int id){
        User user = userService.findUserByToken(token);
        if (user == null) {
            return Result.ClientError("输入token无效");
        }
        SchoolClass schoolClass = schoolClassService.find(id);
        if (schoolClass == null) {
            return Result.ClientError("班级ID无效");
        }
        user.setClassId(id);
        userService.updateUser(user);
        return Result.success("加入成功");
    }
    @GetMapping("/leave")
    public Result leaveClass(@RequestHeader("Authorization") String token){
        User user = userService.findUserByToken(token);
        if (user == null) {
            return Result.ClientError("输入token无效");
        }
        user.setClassId(-1);
        if (userService.updateUser(user)==1) {
            return Result.success("退出成功");
        }
        else{
            return Result.ServerError("退出失败");
        }
    }
}
