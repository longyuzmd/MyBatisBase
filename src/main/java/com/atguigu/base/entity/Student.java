package com.atguigu.base.entity;

import lombok.Data;

@Data
public class Student {
    private String id;
    private String name;
    private String tid;

    private Teacher teacher;
}
