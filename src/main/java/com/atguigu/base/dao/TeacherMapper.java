package com.atguigu.base.dao;

import com.atguigu.base.entity.Teacher;

import java.util.List;

public interface TeacherMapper {

    List<Teacher> selectList();

    List<Teacher> selectListBySecond();

}
