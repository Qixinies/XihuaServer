package com.qixiny.xihuaserver.config;

import com.qixiny.xihuaserver.service.HomeWorkService;
import com.qixiny.xihuaserver.service.Impl.HomeWorkServiceImpl;
import com.qixiny.xihuaserver.service.Impl.SerialServiceImpl;
import com.qixiny.xihuaserver.service.SerialService;
import com.qixiny.xihuaserver.service.UserService;
import com.qixiny.xihuaserver.service.Impl.UserServiceImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ServiceConfig {

    @Bean
    public UserService userService(){
        return new UserServiceImpl();
    }
    @Bean
    public HomeWorkServiceImpl homeWorkServiceImpl(){return new HomeWorkServiceImpl(); }
    @Bean
    public SerialService serialService(){return new SerialServiceImpl(); }
}
