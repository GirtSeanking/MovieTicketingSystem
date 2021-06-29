package main.com.movieticketingsystem.java.controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.util.Pair;
import main.com.movieticketingsystem.java.Main;
import main.com.movieticketingsystem.java.Service.Impl.MovieServiceImpl;
import main.com.movieticketingsystem.java.Service.Impl.UserServiceImpl;
import main.com.movieticketingsystem.java.common.constant.Constant;
import main.com.movieticketingsystem.java.domain.Movie;
import main.com.movieticketingsystem.java.utils.AlertUtils;
import main.com.movieticketingsystem.java.utils.DragUtil;
import main.com.movieticketingsystem.java.utils.list.MovieListUtils;
import main.com.movieticketingsystem.java.utils.list.OrderListUtils;

import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.Optional;
import java.util.ResourceBundle;

/**
 * @className: UserMainController
 * @program: MovieTicketingSystem
 * @description: // 用户端主界面
 * @author: GirtSeanking
 * @create: 2021-06-28 08:34
 **/

public class UserMainController implements Initializable {

    @FXML
    private Label home;

    @FXML
    private Label myOrder;

    @FXML
    private ListView mainListView = new ListView();

    @FXML
    private MenuButton userMenu = new MenuButton();

    private MovieServiceImpl movieServiceImpl = new MovieServiceImpl();
    private UserServiceImpl userServiceImpl = new UserServiceImpl();

    /**
     * @Author GirtSeanking
     * @Description // 初始化界面
     * @Date 8:54
     * @Param [location, resources]
     * @return void
     **/
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        /* 设置电影列表 */
        setMovieList(movieServiceImpl.selectAllMovieList(), Constant.LIKE_TYPE);

        /* 设置用户按钮 */
        setUserBtn();
    }

    private void setUserBtn() {
        MenuItem m1 = new MenuItem("收藏夹");
        MenuItem m2 = new MenuItem("修改密码");
        MenuItem m3 = new MenuItem("退出登录");

        userMenu.getItems().add(m1);
        userMenu.getItems().add(m2);
        userMenu.getItems().add(m3);

        EventHandler<ActionEvent> event = new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent e)
            {
                String value = ((MenuItem)e.getSource()).getText();
                if (value.equals("收藏夹")) {
                    setMovieList(movieServiceImpl.selectFavoriteMovie(Main.getUser().getLoginName()), Constant.FAVORITES_TYPE);
                }
                if (value.equals("修改密码")) {
                    updatePassword();
                }
                if (value.equals("退出登录")) {
                    logOut();
                }
            }
        };

        m1.setOnAction(event);
        m3.setOnAction(event);
        m2.setOnAction(event);
    }

    private void updatePassword() {
        Optional<Pair<String, String>> result = AlertUtils.updatePasswordAlert();
        result.ifPresent(passwords -> {
            if (passwords.getKey().equals(passwords.getValue())) {
                String password = passwords.getValue();
                userServiceImpl.updatePassword(Main.getUser().getLoginName(), password);
                AlertUtils.infoAlert("修改密码成功，请重新登录");
                logOut();
            } else {
                AlertUtils.errorAlert("两次密码输入不一致!!!");
            }
        });
    }

    public void setMovieList(List<Movie> movieList, String type) {
        mainListView.getItems().clear();
        mainListView.setStyle("-fx-background-color: #00000000");
        ObservableList<MovieListUtils.MovieAnchorPaneCell> movies = FXCollections.observableArrayList(new MovieListUtils().getMovieList(movieList, type, this));
        mainListView.setItems(movies);
    }

    public void setOrderList() {
        mainListView.getItems().clear();
        mainListView.setStyle("-fx-background-color: #00000000");
        ObservableList<OrderListUtils.OrderAnchorPaneCell> orderList = FXCollections.observableArrayList(new OrderListUtils().getOrderListUtils(movieServiceImpl.selectAllOrderByUser(Main.getUser().getLoginName())));
        mainListView.setItems(orderList);
    }

    public void homeOnMouseClicked() {
        setMovieList(movieServiceImpl.selectAllMovieList(), Constant.LIKE_TYPE);
    }

    public void myOrderOnMouseClicked() {
        setOrderList();
    }

    public void logOut() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/main/com/movieticketingsystem/resources/view/login.fxml"));
            Parent root = loader.load();
            Main.getLoginStage().setScene(new Scene(root));
            DragUtil.addDragListener(Main.getLoginStage(), root);
            Main.getLoginStage().show();
            Main.getUserMainStage().close();
            Main.setUser(null);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void homeOnMouseEntered() {
        home.setUnderline(true);
    }
    public void homeOnMouseExited() {
        home.setUnderline(false);
    }

    public void myOrderOnMouseEntered() {
        myOrder.setUnderline(true);
    }
    public void myOrderOnMouseExited() {
        myOrder.setUnderline(false);
    }
}