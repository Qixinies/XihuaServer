package com.qixiny.xihuaserver.service;

import com.qixiny.xihuaserver.pojo.HomeWork;
import com.qixiny.xihuaserver.pojo.User;

import java.util.Date;
import java.util.List;

public interface HomeWorkService {
    List<HomeWork> getHomeWorkList();
    HomeWork getHomeWorkById(int id);
    int addHomeWork(User user, String name, String description, Date startDate, Date endDate);
    int deleteHomeWork(int id);

}
