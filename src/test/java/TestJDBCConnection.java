import com.atguigu.base.entity.Blog;
import org.junit.Test;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.sql.*;

public class TestJDBCConnection {

    @Test
    public void test(){

        // 加载jdbc驱动
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        // 数据库连接参数
        String url = "jdbc:mysql://localhost:3306/cloud?serverTimezone=UTC";
        String username = "root";
        String password = "123456";

        String sql = "select * from payment";

        // 创建连接
        Connection con = null;
        try {
            con = DriverManager.getConnection(url, username, password);

            con.setTransactionIsolation(0);  // 设置事务隔离级别

            // 创建一个statement
            PreparedStatement preparedStatement = con.prepareStatement(sql);

            // 执行查询sql
            ResultSet resultSet = preparedStatement.executeQuery(sql);

            while (resultSet.next()){
                System.out.println("id："+resultSet.getString("id")+"    serial:"
                        +resultSet.getString("serial"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void test2(){
        Object[] params = {"1"};

        Blog blog = new Blog();

        Class clazz = blog.getClass();

        Method[] declaredMethods = clazz.getDeclaredMethods();

        for(Method method:declaredMethods){
            if(method.getName().contains("set")){
                // 表示是set方法
                try {
                    method.invoke(blog,params);
                } catch (IllegalAccessException e) {
                    e.printStackTrace();
                } catch (InvocationTargetException e) {
                    e.printStackTrace();
                }
            }
        }

        System.out.println(blog.toString());

    }

}
