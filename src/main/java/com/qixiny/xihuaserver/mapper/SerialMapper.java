package com.qixiny.xihuaserver.mapper;

import com.qixiny.xihuaserver.pojo.Serial;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface SerialMapper {
    List<Serial> findAll();
    Serial findBy(String serial);
    int addSerial(Serial serial);
    int deleteSerial(Serial serial);
}
