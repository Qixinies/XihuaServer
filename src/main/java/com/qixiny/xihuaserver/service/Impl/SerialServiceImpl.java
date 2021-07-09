package com.qixiny.xihuaserver.service.Impl;

import com.qixiny.xihuaserver.common.Utils;
import com.qixiny.xihuaserver.mapper.SerialMapper;
import com.qixiny.xihuaserver.pojo.Serial;
import com.qixiny.xihuaserver.pojo.User;
import com.qixiny.xihuaserver.service.SerialService;
import com.qixiny.xihuaserver.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.UUID;

@Service
public class SerialServiceImpl implements SerialService {

    private SerialMapper serialMapper;
    private UserService userService;
    @Autowired
    public void setUserService(UserService userService) {
        this.userService = userService;
    }

    @Autowired
    public void setSerialMapper(SerialMapper serialMapper) {
        this.serialMapper = serialMapper;
    }

    @Override
    public List<Serial> findAll() {
        return serialMapper.findAll();
    }

    @Override
    public Serial findBy(String serial) {
        return serialMapper.findBy(serial);
    }

    @Override
    public int deleteSerial(Serial serial) {
        return serialMapper.deleteSerial(serial);
    }

    @Override
    public String generateSerial(String type) {
        if (userService.getUserTypeString(type) == null) {
            return null;
        }
        String serial = Utils.randomUtil();
        serialMapper.addSerial(new Serial(serial, type, new Date()));
        Utils.logger.info("序列号："+serial);
        return serial;
    }

    @Override
    public int activateSerial(User user, String serialStr) {
        Serial serial = findBy(serialStr);
        if (serial != null) {
            deleteSerial(serial);
            user.setType(serial.getType());
            userService.updateUser(user);
            return 1;
        }
        Utils.logger.warn("用户:"+user.getUsername()+"激活失败");
        return 0;
    }
}
