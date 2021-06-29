package main.com.movieticketingsystem.java;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import main.com.movieticketingsystem.java.domain.Hall;
import main.com.movieticketingsystem.java.domain.Movie;
import main.com.movieticketingsystem.java.domain.Session;
import main.com.movieticketingsystem.java.domain.User;
import main.com.movieticketingsystem.java.utils.DragUtil;

public class Main extends Application {

    private static User user;
    private static Movie movie;
    private static Hall hall;
    private static Session session;

    /** 登陆界面 */
    private static Stage loginStage;

    /** 注册界面 */
    private static Stage registerStage = null;

    /** 用户端首页 */
    private static Stage userMainStage = null;

    /** 订票界面 */
    private static Stage ticketStage = null;

    /** 二维码付款界面 */
    private static Stage qrCodeStage = null;

    @Override
    public void start(Stage primaryStage) throws Exception{
        Parent root = FXMLLoader.load(getClass().getResource("/main/com/movieticketingsystem/resources/view/login.fxml"));
        primaryStage.setTitle("登录");
        primaryStage.initStyle(StageStyle.UNDECORATED);
        primaryStage.setScene(new Scene(root));

        //窗口拖动监听器
        DragUtil.addDragListener(primaryStage, root);
        loginStage = primaryStage;
        primaryStage.show();

        //创建注册窗口
        createRegisterStage();

        //创建用户端窗口
        createUserMainStage();

        //创建订票窗口
        createTicketStage();

        //创建付款窗口
        createQRCodeStage();

        startSucceeded();
    }

    public static Hall getHall() {
        return hall;
    }

    public static void setHall(Hall hall) {
        Main.hall = hall;
    }

    public static Session getSession() {
        return session;
    }

    public static void setSession(Session session) {
        Main.session = session;
    }

    public static Movie getMovie() {
        return movie;
    }

    public static void setMovie(Movie movie) {
        Main.movie = movie;
    }

    public static User getUser() {
        return user;
    }

    public static void setUser(User user) {
        Main.user = user;
    }

    public void createQRCodeStage() {
        qrCodeStage = new Stage();
        qrCodeStage.setTitle("付款");
        qrCodeStage.initStyle(StageStyle.UNDECORATED);
        qrCodeStage.getIcons().add(new Image(Main.class.getResourceAsStream("../resources/static/img/Logo.png")));
    }

    public void createTicketStage() {
        ticketStage = new Stage();
        ticketStage.setTitle("订票");
        ticketStage.getIcons().add(new Image(Main.class.getResourceAsStream("../resources/static/img/Logo.png")));
    }

    public void createRegisterStage() {
        registerStage = new Stage();
        registerStage.setTitle("注册");
        registerStage.getIcons().add(new Image(Main.class.getResourceAsStream("../resources/static/img/Logo.png")));
    }

    public void createUserMainStage() {
        userMainStage = new Stage();
        userMainStage.setTitle("电影售票系统");
        userMainStage.getIcons().add(new Image(Main.class.getResourceAsStream("../resources/static/img/Logo.png")));
    }

    public static Stage getUserMainStage() {
        return userMainStage;
    }

    public static Stage getTicketStage() {
        return ticketStage;
    }

    public static Stage getQrCodeStage() {
        return qrCodeStage;
    }

    public static Stage getRegisterStage() {
        return registerStage;
    }

    public static Stage getLoginStage() {
        return loginStage;
    }

    /** 启动窗口 */
    public void startApplication() {
        launch();
    }
    public void startSucceeded() {
        System.out.println("电影售票系统启动成功");
    }
}
