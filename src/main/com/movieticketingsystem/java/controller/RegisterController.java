package main.com.movieticketingsystem.java.controller;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import main.com.movieticketingsystem.java.Main;
import main.com.movieticketingsystem.java.Service.Impl.UserServiceImpl;
import main.com.movieticketingsystem.java.common.constant.Constant;
import main.com.movieticketingsystem.java.domain.User;
import main.com.movieticketingsystem.java.utils.AlertUtils;

/**
 * @className: RegisterController
 * @program: MovieTicketingSystem
 * @description: // 用户注册控制层
 * @author: GirtSeanking
 * @create: 2021-06-28 00:23
 **/

public class RegisterController {

    @FXML
    private TextField loginName;

    @FXML
    private TextField password;

    @FXML
    private TextField confirmPassword;

    @FXML
    private TextField phoneNumber;

    @FXML
    private Button registerBtn;

    @FXML
    private Label backBtn;

    private UserServiceImpl userServiceImpl = new UserServiceImpl();

    /**
     * @Author GirtSeanking
     * @Description // 注册按钮点击响应事件
     * @Date 7:00
     * @Param [e]
     * @return void
     **/
    public void registerBtnOnMouseClicked(MouseEvent e) {
        //1.判断账号有没有重复
        if(userServiceImpl.checkLoginNameIsRepeat(loginName.getText())) {
            AlertUtils.errorAlert("账号重复!!!");
            return;
        }
        //2.判断两次密码是否一致
        if (!password.getText().equals(confirmPassword.getText())) {
            AlertUtils.errorAlert("两次密码不一致!!!");
            return;
        }
        //3.判断手机号格式是否正确
        if (!phoneNumber.getText().matches(Constant.MOBILE_PHONE_NUMBER_PATTERN)) {
            AlertUtils.errorAlert("输入的手机号格式不正确");
            return;
        }
        //4.判断账号和密码是否有空格
        if (!loginName.getText().matches(Constant.SPACE_REG) || !password.getText().matches(Constant.SPACE_REG)) {
            AlertUtils.errorAlert("账号或者密码存在空格");
            return;
        }
        //5.存入数据库
        User user = new User();
        user.setLoginName(loginName.getText());
        user.setPassword(password.getText());
        user.setPhoneNumber(phoneNumber.getText());
        user.setRole(Constant.COMMON_ROLE);
        boolean isSuccess = userServiceImpl.register(user);
        if (isSuccess) {
            Main.getLoginStage().show();
            Main.getRegisterStage().close();
        } else {
            AlertUtils.errorAlert("注册失败!!!\n发生未知错误，请联系管理员。");
        }
    }

    public void backBtnOnMouseClicked() {
        Main.getLoginStage().show();
        Main.getRegisterStage().close();
    }

    public void backBtnOnMouseEntered() {
        backBtn.setUnderline(true);
    }

    public void backBtnOnMouseExited() {
        backBtn.setUnderline(false);
    }

}