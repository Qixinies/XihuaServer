package com.qixiny.xihuaserver.service;

import com.qixiny.xihuaserver.pojo.SchoolClass;
import com.qixiny.xihuaserver.pojo.Serial;
import com.qixiny.xihuaserver.pojo.User;

import java.util.List;

public interface SchoolClassService {
    List<SchoolClass> findAll();
    SchoolClass find(int id);
    int delete(SchoolClass schoolClass);
    int add(SchoolClass schoolClass);
    int update(SchoolClass schoolClass);
}
