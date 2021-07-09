package com.qixiny.xihuaserver.service.Impl;

import com.qixiny.xihuaserver.common.Utils;
import com.qixiny.xihuaserver.mapper.HomeWorkMapper;
import com.qixiny.xihuaserver.pojo.HomeWork;
import com.qixiny.xihuaserver.pojo.User;
import com.qixiny.xihuaserver.service.HomeWorkService;
import com.qixiny.xihuaserver.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Date;
import java.util.List;

public class HomeWorkServiceImpl implements HomeWorkService {
    private HomeWorkMapper homeWorkMapper;

    private UserService userService;
    @Autowired
    public void setUserService(UserService userService) {
        this.userService = userService;
    }

    public UserService getUserService() {
        return userService;
    }

    @Autowired
    public void setHomeWorkMapper(HomeWorkMapper homeWorkMapper) {
        this.homeWorkMapper = homeWorkMapper;
    }

    @Override
    public List<HomeWork> getHomeWorkList() {
        return homeWorkMapper.queryAllHomeWork();
    }

    @Override
    public HomeWork getHomeWorkById(int id) {
        return homeWorkMapper.queryHomeWorkById(id);
    }

    @Override
    public int addHomeWork(User user, String name, String description, Date startDate, Date endDate) {

        String typeName = userService.getUserTypeString(user.getType());
        if (typeName == null) {
            return 0;
        }
        HomeWork hw = new HomeWork(name,typeName,description,startDate,endDate,user.getUsername());
        return homeWorkMapper.addHomeWork(hw);
    }

    @Override
    public int deleteHomeWork(int id) {
        return homeWorkMapper.deleteHomeWork(getHomeWorkById(id));
    }
}
