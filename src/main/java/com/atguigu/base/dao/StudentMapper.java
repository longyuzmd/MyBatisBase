package com.atguigu.base.dao;

import com.atguigu.base.entity.Student;

import java.util.List;

public interface StudentMapper {

    List<Student> selectList();

    List<Student> selectListBySecond();
}
