package com.atguigu.base;

import com.atguigu.base.dao.BlogMapper;
import com.atguigu.base.dao.StudentMapper;
import com.atguigu.base.dao.TeacherMapper;
import com.atguigu.base.entity.Blog;
import com.atguigu.base.entity.Student;
import com.atguigu.base.entity.Teacher;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.RowBounds;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.Test;

import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.List;

public class TestMode {

    final static String resource = "mybatis-config.xml";

    static InputStream inputStream = null;

    static {
        try {
            inputStream = Resources.getResourceAsStream(resource);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    final static SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
    final static SqlSession sqlSession = sqlSessionFactory.openSession(true);
    static BlogMapper blogMapper;
    static StudentMapper studentMapper;
    static TeacherMapper teacherMapper;
    {
        blogMapper = sqlSession.getMapper(BlogMapper.class);
        studentMapper = sqlSession.getMapper(StudentMapper.class);
        teacherMapper = sqlSession.getMapper(TeacherMapper.class);
    }

    @Test
    public void test(){
        HashMap<String, Integer> map = new HashMap<>();
        map.put("pageNo",1);
        map.put("pageSize",2);
        List<Blog> blogs = blogMapper.selectList(map);
        for(Blog blog:blogs){
            System.out.println(blog);
        }
    }

    @Test
    public void test1(){

        RowBounds rowBounds = new RowBounds(0, 2);

        List<Blog> list = sqlSession.selectList("com.atguigu.base.dao.BlogMapper.selectListByRowBounds",
                null, rowBounds);

        for(Blog blog : list){
            System.out.println(blog);
        }
    }

    // pageHelper 插件
    @Test
    public void test2(){
        // 尚硅谷111
        List<Blog> list = blogMapper.findList();
        for(Blog blog:list){
            System.out.println(blog);
        }
    }

    @Test
    public void test3(){
        Blog blog = new Blog();
        blog.setId("31");
        blog.setSerial("尚硅谷111");
        blogMapper.updateById(blog);
    }

    @Test
    public void test4(){
        List<Student> students = studentMapper.selectList();
        for (Student student:students){
            System.out.println(student.getName()+"====="+student.getTeacher().getName());
        }
    }

    @Test
    public void test5(){
        List<Student> students = studentMapper.selectListBySecond();
        for (Student student:students){
            System.out.println(student.getName()+"====="+student.getTeacher().getName());
        }
    }

    @Test
    public void test6(){
        List<Teacher> teachers = teacherMapper.selectList();

        teachers.get(0).getStudents();
//        for (Teacher teacher:teachers){+
//            teacher.getStudents().get(0).getName();
//            System.out.println(teacher.getName()+"=====");
//        }
    }

    @Test
    public void test7(){
        List<Teacher> teachers = teacherMapper.selectListBySecond();
        for (Teacher teacher:teachers){
            System.out.println(teacher.getName()+"=====");
        }
    }
}
