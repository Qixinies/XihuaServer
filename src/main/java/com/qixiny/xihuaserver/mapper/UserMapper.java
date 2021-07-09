package com.qixiny.xihuaserver.mapper;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.qixiny.xihuaserver.pojo.User;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface UserMapper {
    List<User> queryUserList();
    User queryUserByOpenid(String openid);
    int addUser(User user);
    int updateUser(User user);
    int deleteUser(User user);
}
