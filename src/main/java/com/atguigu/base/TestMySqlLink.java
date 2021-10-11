package com.atguigu.base;

import com.atguigu.base.dao.BlogMapper;
import com.atguigu.base.entity.Blog;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import java.io.IOException;
import java.io.InputStream;

public class TestMySqlLink {

    public static void main(String[] args) throws IOException {

        String resource = "mybatis-config.xml";

        InputStream inputStream = Resources.getResourceAsStream(resource);

        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);

        SqlSession sqlSession = sqlSessionFactory.openSession();

        BlogMapper mapper = sqlSession.getMapper(BlogMapper.class); // 动态代理去实现的

//        Blog blog = mapper.selectBlog("31");

        Blog blog = mapper.selectResultMapBlog("31");

//        mapper.updateBlogById(blog);

        Blog blog1 = mapper.selectResultMapBlog("31");
        /** 从缓存里面获取数据 localCache.getObject(key)
         *
         * 1 缓存是什么时候被创建的：是在执行sql后别创建 localCache.putObject(key,value)，
         * 将sql,参数,环境id等信息封装成一个key,value是查询的结果
         *
         * 2 缓存的使用：查询前先从缓存里面取，看是否可以取到
         *
         * 一级缓存什么情况下：
         * 1 查询不同数据，也就是参数不一样
         * 2 增删改操作可能会改变数据，缓存会失效
         * 3 查询不同的mapper.xml sql不一样 key 会有很多元素（sql 入参 环境id(是不是一个库)）
         * 4 手动清理缓存，sqlSession.clearCache()
         *
         * */

        sqlSession.close();

        System.out.println(blog.toString());
        System.out.println(blog1.toString());
    }

}
