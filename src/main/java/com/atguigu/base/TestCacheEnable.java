package com.atguigu.base;

import com.atguigu.base.dao.BlogMapper;
import com.atguigu.base.entity.Blog;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import java.io.IOException;
import java.io.InputStream;

public class TestCacheEnable {

    public static void main(String[] args) throws IOException {
        String resource = "mybatis-config.xml";
        InputStream inputStream = Resources.getResourceAsStream(resource);
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);

        SqlSession sqlSession = sqlSessionFactory.openSession();
        BlogMapper mapper = sqlSession.getMapper(BlogMapper.class);
        Blog blog = mapper.selectBlog("31");
        sqlSession.close();
        System.out.println(blog);

        SqlSession sqlSession1 = sqlSessionFactory.openSession();
        BlogMapper mapper1 = sqlSession1.getMapper(BlogMapper.class);
        Blog blog1 = mapper1.selectBlog("31");
        sqlSession.close();
        System.out.println(blog1);
    }
}
