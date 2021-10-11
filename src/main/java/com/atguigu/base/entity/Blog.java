package com.atguigu.base.entity;

import lombok.Data;
import org.apache.ibatis.type.Alias;

import java.io.Serializable;

@Data
@Alias("payment")
public class Blog implements Serializable {

    private String id;

    private String serial;

    @Override
    public String toString() {
        return "Blog{" +
                "id='" + id + '\'' +
                ", serial='" + serial + '\'' +
                '}';
    }
}
