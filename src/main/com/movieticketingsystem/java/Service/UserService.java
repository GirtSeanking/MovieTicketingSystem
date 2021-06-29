package main.com.movieticketingsystem.java.Service;

import javafx.scene.control.Dialog;
import javafx.util.Pair;
import main.com.movieticketingsystem.java.domain.User;

/**
 * @className: LoginService
 * @program: MovieTicketingSystem
 * @description: // 登录服务层接口
 * @author: GirtSeanking
 * @create: 2021-06-27 23:13
 **/

public interface UserService {

    public User login(User user);

    public boolean checkLoginNameIsRepeat(String loginName);

    public boolean register(User user);

    public String selectPassword(String loginName);

    public void updatePassword(String loginName, String newPassword);
}