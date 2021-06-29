package main.com.movieticketingsystem.java.controller;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import main.com.movieticketingsystem.java.Main;
import main.com.movieticketingsystem.java.Service.Impl.UserServiceImpl;
import main.com.movieticketingsystem.java.common.constant.Constant;
import main.com.movieticketingsystem.java.domain.User;
import main.com.movieticketingsystem.java.utils.AlertUtils;

import java.io.IOException;

public class LoginController {

    @FXML
    private Button loginBtn;

    @FXML
    private TextField loginName;

    @FXML
    private TextField password;

    @FXML
    private Button closeBtn;

    @FXML
    private Label register;

    private UserServiceImpl userServiceImpl = new UserServiceImpl();

    /**
     * @Author GirtSeanking
     * @Description // 登录按钮相应事件
     * @Date 0:05
     * @Param [e]
     * @return void
     **/
    public void loginBtnMouseRelease(MouseEvent mouseEvent) {
        User user = new User();
        user.setLoginName(loginName.getText());
        user.setPassword(password.getText());
        user = userServiceImpl.login(user);
        if (user != null) {
            if (user.getRole().equals(Constant.COMMON_ROLE)) {
                try {
                    Main.setUser(user);
                    FXMLLoader loader = new FXMLLoader(getClass().getResource("/main/com/movieticketingsystem/resources/view/UserMain.fxml"));
                    Parent root = loader.load();
                    Main.getUserMainStage().setScene(new Scene(root));
                    Main.getUserMainStage().show();
                    Main.getUserMainStage().setResizable(false);
                    Main.getLoginStage().close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        } else {
            AlertUtils.errorAlert("账号或者密码错误!!!");
        }
    }

    /**
     * @Author GirtSeanking
     * @Description // 注册按钮响应事件
     * @Date 10:18
     * @Param [mouseEvent]
     * @return void
     **/
    public void registerMouseClick(MouseEvent mouseEvent) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/main/com/movieticketingsystem/resources/view/register.fxml"));
            Parent root = loader.load();
            Main.getRegisterStage().setScene(new Scene(root));
            Main.getLoginStage().hide();
            Main.getRegisterStage().show();
            Main.getRegisterStage().setResizable(false);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * @Author GirtSeanking
     * @Description // 关闭窗口按钮点击事件响应
     * @Date 10:17
     * @Param [mouseEvent]
     * @return void
     **/
    public void closeMouseClick(MouseEvent mouseEvent) {
        System.out.println("已退出电影售票系统");
        System.exit(0);
    }

    /**
     * @Author GirtSeanking
     * @Description // 登录按钮样式变化设置
     * @Date 10:15
     * @Param [mouseEvent]
     * @return void
     **/
    public void loginBtnMouseEnter(MouseEvent mouseEvent) {
        loginBtn.setStyle("-fx-background-color: linear-gradient(to top, #48c6ef 0%, #6f86d6 100%)");
    }
    public void loginBtnMouseExit(MouseEvent mouseEvent) {
        loginBtn.setStyle("-fx-background-color: linear-gradient(to right, #4facfe 0%, #00f2fe 100%)");
    }
    public void loginBtnMousePress(MouseEvent mouseEvent) {
        loginBtn.setStyle("-fx-background-color: linear-gradient(to top, #30cfd0 0%, #330867 100%)");
    }

    /**
     * @Author GirtSeanking
     * @Description // 注册按钮样式变化设置
     * @Date 10:19
     * @Param [mouseEvent]
     * @return void
     **/
    public void registerMouseEnter(MouseEvent mouseEvent) {
        register.setTextFill(Color.web("#4facfe"));
        register.setUnderline(true);
    }
    public void registerMouseExit(MouseEvent mouseEvent) {
        register.setTextFill(Color.web("#a1a1a1"));
        register.setUnderline(false);
    }

    /**
     * @Author GirtSeanking
     * @Description // 关闭窗口按钮样式变化设置
     * @Date 20:43
     * @Param [mouseEvent]
     * @return void
     **/
    public void closeMouseEnter(MouseEvent mouseEvent) {
        closeBtn.setStyle("-fx-background-color: #99999999");
    }
    public void closeMouseExit(MouseEvent mouseEvent) {
        closeBtn.setStyle("-fx-background-color: #00000000");
    }

}
