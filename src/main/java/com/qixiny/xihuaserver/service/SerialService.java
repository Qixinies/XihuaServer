package com.qixiny.xihuaserver.service;

import com.qixiny.xihuaserver.pojo.Serial;
import com.qixiny.xihuaserver.pojo.User;

import java.util.List;

public interface SerialService {
    List<Serial> findAll();
    Serial findBy(String serial);
    int deleteSerial(Serial serial);
    String generateSerial(String type);
    int activateSerial(User user, String serial);
}
