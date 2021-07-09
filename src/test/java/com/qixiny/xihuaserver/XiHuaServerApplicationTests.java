package com.qixiny.xihuaserver;

import com.qixiny.xihuaserver.service.UserService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class XiHuaServerApplicationTests {
    @Autowired
    public UserService userService;

    @Test
    void contextLoads() {
        System.out.println(userService);
    }

}
