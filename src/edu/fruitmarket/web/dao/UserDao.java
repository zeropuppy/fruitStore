package edu.fruitmarket.web.dao;

import edu.fruitmarket.web.beans.User;
import edu.fruitmarket.web.tools.JDBCUtils;

import java.sql.*;
import java.util.ArrayList;

public class UserDao {
    public String login(String name, String password) {
        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        String sql = "select * from user where username=? and password=?";
        try {
            conn = JDBCUtils.getConnect();
            pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, name);
            pstmt.setString(2, password);
            rs = pstmt.executeQuery();
            if (rs.next()) {
                return "1";
            } else {
                return "2";
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return "4";
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
            return "3";
        } finally {
            JDBCUtils.release(conn, pstmt, rs);
        }

    }

    //查询所有数据
    public ArrayList<User> queryAllUser() {
        Statement stmt = null;
        ResultSet rs = null;
        Connection conn = null;
        ArrayList<User> users = new ArrayList<User>();
        try {
            conn = JDBCUtils.getConnect();
            stmt = conn.createStatement();
            String sql = "select * from user";
            rs = stmt.executeQuery(sql);
            while (rs.next()) {
//                Fruit fruit=new Fruit();
                User user = new User();
                user.setId(rs.getInt("id"));
                user.setUsername(rs.getString("username"));
                user.setPassword(rs.getString("password"));

                users.add(user);
            }
            return users;
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } finally {
            JDBCUtils.release(conn, stmt, rs);
        }
        return null;
    }

    //查询数据
    public ArrayList<User> queryUserById(User user) {

        Statement stmt = null;
        ResultSet rs = null;
        Connection conn = null;
        ArrayList<User> users = new ArrayList<User>();
        try {
            conn = JDBCUtils.getConnect();
            stmt = conn.createStatement();

            String sql = "select * from user where 1=1";
            if (user.getId() != 0)
                sql = sql + " and id='" + user.getId() + "'";
            if (user.getUsername() != null && user.getUsername().length() > 0)
                sql = sql + " and username like '%" + user.getUsername() + "%'";
            if (user.getPassword() != null && user.getPassword().length() > 0)
                sql = sql + " and password like '%" + user.getPassword() + "%'";

            rs = stmt.executeQuery(sql);
            while (rs.next()) {
                User date = new User();
                date.setId(rs.getInt("id"));
                date.setUsername(rs.getString("username"));
                date.setPassword(rs.getString("password"));

                users.add(date);
            }
            return users;
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } finally {
            JDBCUtils.release(conn, stmt, rs);
        }
        return null;
    }

    //添加数据
    public void addUser(User user) {
//        Statement stmt = null;
        PreparedStatement pstmt = null;
        Connection conn = null;
        try {
            conn = JDBCUtils.getConnect();
//            stmt=conn.createStatement();
//            String sql="insert into fruit(number,name,price,unit) values('"+fruit.getNumber()+"','"+fruit.getName()
//                    +"','"+fruit.getPrice()+"','"+fruit.getUnit()+"')";
//            int num=stmt.executeUpdate(sql);
            String sql = "insert into user(id,username,password) values(?,?,?)";
            pstmt = conn.prepareStatement(sql);
            pstmt.setInt(1, user.getId());
            pstmt.setString(2, user.getUsername());
            pstmt.setString(3, user.getPassword());

            int num = pstmt.executeUpdate();
            if (num > 0)
                System.out.println("添加数据成功！");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } finally {
//            JDBCTools.release(conn,stmt);
            JDBCUtils.release(conn, pstmt);
        }
    }

    //修改数据
    public void updateUser(User user) {
        PreparedStatement pstmt = null;
        Connection conn = null;
        try {
            conn = JDBCUtils.getConnect();
            //            stmt=conn.createStatement();
//            String sql="update fruit set name='"+fruit.getName()+"',price='"+fruit.getPrice()
//                    +"',unit='"+fruit.getUnit()+"' where number='"+fruit.getNumber()+"'";
//            int num=stmt.executeUpdate(sql);

            String sql = "update user set password=" + user.getPassword() + " where username='" + user.getUsername() + "'";
            pstmt = conn.prepareStatement(sql);
            System.out.println(user.getPassword() + ' ' + user.getUsername());

//            String sql = "update user set password=? where username=?";
//            System.out.println(user.getPassword()+' '+user.getUsername());
//            pstmt = conn.prepareStatement(sql);
//            pstmt.setString(1, user.getPassword());
//            pstmt.setString(2, user.getUsername());


            int num = pstmt.executeUpdate();

            if (num > 0)
                System.out.println("修改数据成功！");

        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } finally {
//            JDBCTools.release(conn,stmt);
            JDBCUtils.release(conn, pstmt);
        }
    }
}
