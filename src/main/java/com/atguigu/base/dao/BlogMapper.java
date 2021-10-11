package com.atguigu.base.dao;


import com.atguigu.base.entity.Blog;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import java.util.List;
import java.util.Map;

public interface BlogMapper {

    Blog selectBlog(String id);

    Blog selectResultMapBlog(String id);

    void updateBlogById(Blog blog);

    List<Blog> selectList(Map<String,Integer> map);

    List<Blog> selectListByRowBounds();

    @Select("select * from payment")
    List<Blog> findList();

    @Update("update payment set serial = #{serial} where id = #{id}")
    void updateById(Blog blog);
}
