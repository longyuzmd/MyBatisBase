package com.atguigu.base.entity;

import lombok.Data;

import java.util.List;

@Data
public class Teacher {
    private String id;
    private String name;

    private List<Student> students;
}
