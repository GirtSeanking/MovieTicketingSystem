package main.com.movieticketingsystem.java.Service.Impl;

import javafx.scene.control.Dialog;
import javafx.util.Pair;
import main.com.movieticketingsystem.java.Service.UserService;
import main.com.movieticketingsystem.java.dao.UserDao;
import main.com.movieticketingsystem.java.domain.User;

/**
 * @className: LoginServiceImpl
 * @program: MovieTicketingSystem
 * @description: // 登录服务层实现类
 * @author: GirtSeanking
 * @create: 2021-06-27 23:14
 **/

public class UserServiceImpl implements UserService {

    private UserDao userDao = new UserDao();

    @Override
    public User login(User user) {
       return userDao.login(user);
    }

    @Override
    public boolean checkLoginNameIsRepeat(String loginName) {
        return userDao.checkLoginNameIsRepeat(loginName);
    }

    @Override
    public boolean register(User user) {
        return userDao.register(user);
    }

    @Override
    public String selectPassword(String loginName) {
        return userDao.selectPassword(loginName);
    }

    @Override
    public void updatePassword(String loginName, String newPassword) {
    System.out.println(loginName + "  " + newPassword);
        userDao.updatePassword(loginName, newPassword);
    }
}