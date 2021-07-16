package com.qixiny.xihuaserver.mapper;

import com.qixiny.xihuaserver.pojo.SchoolClass;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface SchoolClassMapper {
    List<SchoolClass> queryAll();
    SchoolClass queryById(int id);
    int add(SchoolClass schoolClass);
    int update(SchoolClass schoolClass);
    int delete(SchoolClass schoolClass);
}
