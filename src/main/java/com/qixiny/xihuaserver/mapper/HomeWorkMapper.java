package com.qixiny.xihuaserver.mapper;

import com.qixiny.xihuaserver.pojo.HomeWork;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface HomeWorkMapper {
    List<HomeWork> queryAllHomeWork();
    HomeWork queryHomeWorkById(int id);
    int addHomeWork(HomeWork homeWork);
    int updateHomeWork(HomeWork homeWork);
    int deleteHomeWork(HomeWork homeWork);
}
