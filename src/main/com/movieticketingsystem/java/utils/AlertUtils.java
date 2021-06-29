package main.com.movieticketingsystem.java.utils;

import javafx.application.Platform;
import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.util.Pair;
import main.com.movieticketingsystem.java.Main;
import main.com.movieticketingsystem.java.Service.Impl.UserServiceImpl;

import java.util.Optional;
import java.util.concurrent.atomic.AtomicReference;

/**
 * @className: AlertUtils
 * @program: MovieTicketingSystem
 * @description: // 弹窗工具类
 * @author: GirtSeanking
 * @create: 2021-06-28 07:10
 **/

public class AlertUtils {

    public static void errorAlert(String message) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("错误");
        alert.setContentText(message);
        alert.showAndWait();
    }

    public static Optional<ButtonType> confirmAlert(String message) {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("确认消息");
        alert.setContentText(message);
        Optional<ButtonType> result = alert.showAndWait();
        return result;
    }

    public static void infoAlert(String message) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("消息提示框");
        alert.setContentText(message);
        alert.showAndWait();
    }

    public static Optional<Pair<String, String>> updatePasswordAlert() {
        UserServiceImpl userServiceImpl = new UserServiceImpl();
        AtomicReference<String> password = new AtomicReference<>();
        Dialog<Pair<String, String>> dialog = new Dialog<>();
        dialog.setTitle("修改密码");
        dialog.setHeaderText("请填入以下信息");

        dialog.setGraphic(new ImageView(new Image(Main.class.getResourceAsStream("../resources/static/img/password.png"))));
        ButtonType updateButtonType = new ButtonType("确认修改", ButtonBar.ButtonData.OK_DONE);
        dialog.getDialogPane().getButtonTypes().addAll(updateButtonType, ButtonType.CANCEL);

        GridPane grid = new GridPane();
        grid.setHgap(10);
        grid.setVgap(10);
        grid.setPadding(new Insets(20, 150, 10, 10));

        PasswordField oldPassword = new PasswordField();
        oldPassword.setPromptText("原密码");
        PasswordField newPassword = new PasswordField();
        newPassword.setPromptText("新密码");
        PasswordField confirmPassword = new PasswordField();
        confirmPassword.setPromptText("再次确认");

        grid.add(new Label("原始密码:"), 0, 0);
        grid.add(oldPassword, 1, 0);
        grid.add(new Label("新密码:"), 0, 1);
        grid.add(newPassword, 1, 1);
        grid.add(new Label("再次确认:"), 0, 2);
        grid.add(confirmPassword, 1, 2);

        Node updateButton = dialog.getDialogPane().lookupButton(updateButtonType);
        updateButton.setDisable(true);

        oldPassword.textProperty().addListener(((observable, oldValue, newValue) -> {
            updateButton.setDisable(newValue.trim().isEmpty());
        }));
        newPassword.textProperty().addListener(((observable, oldValue, newValue) -> {
            updateButton.setDisable(newValue.trim().isEmpty());
        }));
        confirmPassword.textProperty().addListener(((observable, oldValue, newValue) -> {
            updateButton.setDisable(newValue.trim().isEmpty());
        }));

        dialog.getDialogPane().setContent(grid);

        Platform.runLater(oldPassword::requestFocus);

        dialog.setResultConverter(dialogButton -> {
            if (dialogButton == updateButtonType) {
                if (oldPassword.getText().equals(new UserServiceImpl().selectPassword(Main.getUser().getLoginName()))) {
                    return new Pair<>(newPassword.getText(), confirmPassword.getText());
                } else {
                    errorAlert("原始密码不正确!!!");
                }
            }
            return null;
        });

        Optional<Pair<String, String>> result = dialog.showAndWait();
        return result;
    }
}