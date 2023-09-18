package edu.fruitmarket.web.tools;

import java.sql.*;

//连接数据库
public class JDBCUtils {
    //加载驱动，建立数据库的连接
    public static Connection getConnect() throws SQLException,ClassNotFoundException
    {
        //1.注册一个数据库的驱动程序
        Class.forName("com.mysql.cj.jdbc.Driver");
        //2.通过DriverManager获取数据库连接
        String url="jdbc:mysql://localhost:3306/fruit?serverTimezone=GMT%2B8&useSSL=false";
        String  username="root";
        String password="18307075634";
        Connection conn= DriverManager.getConnection(url,username,password);
        return conn;
    }
    //适合添，删，改操作后的资源释放
    public static  void release(Connection conn, Statement stmt)
    {
        if(stmt!=null)
        {
            try{
                stmt.close();
            }catch (SQLException e)
            {
                e.printStackTrace();
            }
            stmt=null;
        }
        if(conn!=null)
        {
            try{
                conn.close();
            }catch (SQLException e)
            {
                e.printStackTrace();
            }
            conn=null;
        }

    }
    //适合查询后资源释放
    public static void release(Connection conn, Statement stmt, ResultSet rs)
    {
        if(rs!=null)
        {
            try{
                rs.close();
            }catch (SQLException e)
            {
                e.printStackTrace();
            }
            rs=null;
        }
        release(conn,stmt);
    }

}
