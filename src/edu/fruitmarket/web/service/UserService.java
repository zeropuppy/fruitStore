package edu.fruitmarket.web.service;

import edu.fruitmarket.web.beans.User;
import edu.fruitmarket.web.dao.UserDao;

import java.util.ArrayList;

public class UserService {
    private UserDao dao=new UserDao();
    public String login(String name, String password) {
        UserDao dao = new UserDao();

        if (name != null && name.length() > 0) {
            return dao.login(name, password);
        } else { //没有输入值时
            return "5";
        }
    }
    public ArrayList<User> queryAllUser(){
        ArrayList<User> users=dao.queryAllUser();
        return users;
    }

    public ArrayList<User> queryUserById(User user){
        ArrayList<User> users=dao.queryUserById(user);
        return users;
    }
    public boolean addUser(User user){
        ArrayList<User> users=queryAllUser();
        for(User data:users){
            if(user.getId()==(data.getId()))
                return false;
        }
        dao.addUser(user);
        return true;
    }

    public boolean updateUser(User user){
        ArrayList<User> users=queryAllUser();
        dao.updateUser(user);
        return true;

    }
}