package com.qixiny.xihuaserver.service.Impl;

import com.qixiny.xihuaserver.mapper.SchoolClassMapper;
import com.qixiny.xihuaserver.pojo.SchoolClass;
import com.qixiny.xihuaserver.service.SchoolClassService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SchoolClassServiceImpl implements SchoolClassService {

    private SchoolClassMapper schoolClassMapper;

    @Autowired
    public void setSchoolClassMapper(SchoolClassMapper schoolClassMapper) {
        this.schoolClassMapper = schoolClassMapper;
    }

    @Override
    public List<SchoolClass> findAll() {
        return schoolClassMapper.queryAll();
    }

    @Override
    public SchoolClass find(int id) {
        return schoolClassMapper.queryById(id);
    }

    @Override
    public int delete(SchoolClass schoolClass) {
        return schoolClassMapper.delete(schoolClass);
    }

    @Override
    public int add(SchoolClass schoolClass) {
        return schoolClassMapper.add(schoolClass);
    }

    @Override
    public int update(SchoolClass schoolClass) {
        return schoolClassMapper.update(schoolClass);
    }
}
