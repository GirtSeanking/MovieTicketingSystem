package main.com.movieticketingsystem.java.utils.list;

import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Font;
import main.com.movieticketingsystem.java.Main;
import main.com.movieticketingsystem.java.Service.Impl.MovieServiceImpl;
import main.com.movieticketingsystem.java.domain.Hall;
import main.com.movieticketingsystem.java.domain.Session;
import main.com.movieticketingsystem.java.utils.DragUtil;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

/**
 * @className: SessionListUtils
 * @program: MovieTicketingSystem
 * @description: // 电影场次列表控件
 * @author: GirtSeanking
 * @create: 2021-06-28 15:46
 **/

public class SessionListUtils {

    public List<SessionAnchorPaneCell> getSessionList(List<Session> sessionList) {
        List<SessionAnchorPaneCell> sessionItemList = new ArrayList<>();
        for (Session session : sessionList) {
            sessionItemList.add(new SessionAnchorPaneCell(session));
        }
        return sessionItemList;
    }

    public static class SessionAnchorPaneCell extends AnchorPane {
        private Label startTime = new Label();
        private Label endTime = new Label();
        private Label sessionName = new Label();
        private Label price = new Label();
        private Button buyBtn = new Button();

        private MovieServiceImpl movieServiceImpl = new MovieServiceImpl();

        public SessionAnchorPaneCell(Session session) {
            Hall hall = movieServiceImpl.selectHallBySessionId(session.getSessionId());
            SimpleDateFormat sdf = new SimpleDateFormat("HH:mm");
            startTime.setText(sdf.format(session.getStartTime()));
            startTime.setFont(new Font("Cambria", 20));
            startTime.setLayoutX(5);
            startTime.setLayoutY(5);

            endTime.setText(sdf.format(session.getEndTime()) + "散场");
            endTime.setFont(new Font("Cambria", 13));
            endTime.setLayoutX(7);
            endTime.setLayoutY(25);
            endTime.setStyle("-fx-text-fill: #8b8989;");

            sessionName.setText(hall.getHallName());
            sessionName.setFont(new Font("Cambria", 15));
            sessionName.setLayoutX(90);
            sessionName.setLayoutY(15);

            price.setText(session.getPrice());
            price.setFont(new Font("Cambria", 15));
            price.setLayoutX(250);
            price.setLayoutY(15);
            price.setStyle("-fx-text-fill: #ff1e00;");

            buyBtn.setText("购买");
            buyBtn.setLayoutX(300);
            buyBtn.setLayoutY(10);
            buyBtn.setStyle("-fx-background-radius: 5em;");
            buyBtn.setOnMouseClicked(new EventHandler<MouseEvent>() {
                @Override
                public void handle(MouseEvent event) {
                    try {
                        FXMLLoader loader = new FXMLLoader(getClass().getResource("/main/com/movieticketingsystem/resources/view/QRCode.fxml"));
                        Parent root = loader.load();
                        DragUtil.addDragListener(Main.getQrCodeStage(), root);
                        Main.getQrCodeStage().setScene(new Scene(root));
                        Main.getQrCodeStage().show();
                        Main.getTicketStage().close();
                        Main.getUserMainStage().hide();
                        Main.setSession(session);
                        Main.setHall(hall);
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            });

            this.getChildren().addAll(startTime, endTime, sessionName, price, buyBtn);
        }
    }

}