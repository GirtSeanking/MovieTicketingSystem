package main.com.movieticketingsystem.java.dao;

import javafx.scene.control.Dialog;
import javafx.util.Pair;
import main.com.movieticketingsystem.java.domain.User;
import main.com.movieticketingsystem.java.utils.DBUtils;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * @className: LoginDao
 * @program: MovieTicketingSystem
 * @description: // 用户数据持久层
 * @author: GirtSeanking
 * @create: 2021-06-27 23:19
 **/

public class UserDao {

    DBUtils dbUtils = new DBUtils();

    /**
     * @Author GirtSeanking
     * @Description // 登录
     * @Date 0:18
     * @Param [user]
     * @return main.com.movieticketingsystem.java.domain.User
     **/
    public User login(User user) {
        String sql = "select login_name, phone_number, role from user where login_name = ? and password = ?";
        PreparedStatement pst = dbUtils.getPreparedStatement(sql);
        try {
            pst.setString(1, user.getLoginName());
            pst.setString(2, user.getPassword());
            ResultSet rs = pst.executeQuery();
            if (rs.next()) {
                user.setLoginName(rs.getString(1));
                user.setPhoneNumber(rs.getString(2));
                user.setRole(rs.getString(3));
            }else {
                user = null;
            }
            dbUtils.close(rs);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return user;
    }

    /**
     * @Author GirtSeanking
     * @Description // 判断账号是否重复
     * @Date 0:17
     * @Param [loginName]
     * @return boolean
     **/
    public boolean checkLoginNameIsRepeat(String loginName) {
        String sql = "select * from user where login_name = ?";
        boolean isRepeat = false;
        PreparedStatement pst = dbUtils.getPreparedStatement(sql);
        try {
            pst.setString(1, loginName);
            ResultSet rs = pst.executeQuery();
            if (rs.next()) {
                isRepeat = true;
            }
            dbUtils.close(rs);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return isRepeat;
    }

    /**
     * @Author GirtSeanking
     * @Description // 注册
     * @Date 0:17
     * @Param [user]
     * @return boolean
     **/
    public boolean register(User user) {
        String sql = "insert into user(login_name, password, phone_number, role) values(?, ?, ?, ?)";
        boolean isSuccessful = false;
        PreparedStatement pst = dbUtils.getPreparedStatement(sql);
        try {
            pst.setString(1, user.getLoginName());
            pst.setString(2, user.getPassword());
            pst.setString(3, user.getPhoneNumber());
            pst.setString(4, user.getRole());
            int rs = pst.executeUpdate();
            if (rs > 0) {
                isSuccessful = true;
            }
            dbUtils.close();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return isSuccessful;
    }

    /**
     * @Author GirtSeanking
     * @Description // 查询匹配密码
     * @Date 0:17
     * @Param [loginName]
     * @return java.lang.String
     **/
    public String selectPassword(String loginName) {
        String sql = "select password from user where login_name = ?";
        PreparedStatement pst = dbUtils.getPreparedStatement(sql);
        String password = null;
        try {
            pst.setString(1, loginName);
            ResultSet rs = pst.executeQuery();
            if (rs.next()) {
                password = rs.getString(1);
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return password;
    }

    /**
     * @Author GirtSeanking
     * @Description // 修改密码
     * @Date 0:17
     * @Param [loginName, newPassword]
     * @return void
     **/
    public void updatePassword(String loginName, String newPassword) {
        String sql = "update user set password = ? where login_name = ?";
        PreparedStatement pst = dbUtils.getPreparedStatement(sql);
        try {
            pst.setString(1, newPassword);
            pst.setString(2, loginName);
            pst.executeUpdate();
            dbUtils.close();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }
}