package main.com.movieticketingsystem.java.utils.list;

import javafx.event.EventHandler;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Font;
import main.com.movieticketingsystem.java.Main;
import main.com.movieticketingsystem.java.Service.Impl.MovieServiceImpl;
import main.com.movieticketingsystem.java.domain.*;
import main.com.movieticketingsystem.java.utils.AlertUtils;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;


/**
 * @className: OrderListUtils
 * @program: MovieTicketingSystem
 * @description: // 加载订单列表
 * @author: GirtSeanking
 * @create: 2021-06-28 19:30
 **/

public class OrderListUtils {

    public List<OrderAnchorPaneCell> getOrderListUtils(List<Order> orderList) {
        List<OrderAnchorPaneCell> orderItemList = new ArrayList<>();
        for (Order orderItem : orderList) {
            orderItemList.add(new OrderAnchorPaneCell(orderItem));
        }
        return orderItemList;
    }

    public static class OrderAnchorPaneCell extends AnchorPane {
        private ImageView movieImage = new ImageView();
        private Label movieLabel = new Label();
        private Label hallLabel = new Label();
        private Label seatLabel = new Label();
        private Label startTime = new Label();
        private Label endTime = new Label();
        private Label buyTime = new Label();
        private Label statusLabel = new Label();
        private Button cancelBtn = new Button();
        private boolean isCancel = false;

        private MovieServiceImpl movieServiceImpl = new MovieServiceImpl();

        public OrderAnchorPaneCell(Order order) {
            Movie movie = movieServiceImpl.selectMovieById(order.getMovieId());
            Seat seat = movieServiceImpl.selectSeatById(order.getSeatId());
            Hall hall = movieServiceImpl.selectHallById(order.getHallId());
            Session session = movieServiceImpl.selectSessionById(order.getSessionId());
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            isCancel =  order.getStatus().equals("1");

            movieImage.setImage(new Image(Objects.requireNonNull(Main.class.getResourceAsStream("../resources/static/img/3DMovie.png"))));
            movieImage.setFitWidth(200);
            movieImage.setFitHeight(200);
            movieImage.setLayoutX(5);
            movieImage.setLayoutY(5);

            movieLabel.setText(movie.getMovieName());
            movieLabel.setFont(new Font("Cambria", 25));
            movieLabel.setLayoutX(250);
            movieLabel.setLayoutY(5);

            hallLabel.setText(hall.getHallName());
            hallLabel.setFont(new Font("Cambria", 17));
            hallLabel.setLayoutX(250);
            hallLabel.setLayoutY(50);

            seatLabel.setText(seat.getRowNum() + "排" + seat.getColNum() + "座");
            seatLabel.setFont(new Font("Cambria", 13));
            seatLabel.setLayoutX(250);
            seatLabel.setLayoutY(80);

            startTime.setText("开场时间：" + sdf.format(session.getStartTime()));
            startTime.setFont(new Font("Cambria", 17));
            startTime.setLayoutX(250);
            startTime.setLayoutY(110);

            endTime.setText("散场时间：" + sdf.format(session.getEndTime()));
            endTime.setFont(new Font("Cambria", 17));
            endTime.setLayoutX(250);
            endTime.setLayoutY(140);

            buyTime.setText("购买时间：" + sdf.format(order.getBuyTime()));
            buyTime.setFont(new Font("Cambria", 17));
            buyTime.setLayoutX(250);
            buyTime.setLayoutY(170);

            if (isCancel) {
                statusLabel.setVisible(false);
                cancelBtn.setVisible(true);
            } else {
                statusLabel.setVisible(true);
                cancelBtn.setVisible(false);
            }

            statusLabel.setText("订单已取消");
            statusLabel.setFont(new Font("Cambria", 17));
            statusLabel.setLayoutX(750);
            statusLabel.setLayoutY(170);

            cancelBtn.setText("取消订单");
            cancelBtn.setLayoutX(750);
            cancelBtn.setLayoutY(170);
            cancelBtn.setStyle("-fx-background-radius: 5em;");
            cancelBtn.setOnMouseClicked(new EventHandler<MouseEvent>() {
                @Override
                public void handle(MouseEvent event) {
                    Optional<ButtonType> resultType = AlertUtils.confirmAlert("是否确认取消订单？");
                    if (resultType.get() == ButtonType.OK) {
                        movieServiceImpl.updateOrderStatus(order.getOrderId(), "0");
                        statusLabel.setVisible(true);
                        cancelBtn.setVisible(false);
                        isCancel = !isCancel;
                    }
                }
            });

            this.getChildren().addAll(movieImage, movieLabel, hallLabel, seatLabel, startTime, endTime, buyTime, statusLabel, cancelBtn);
        }
    }

}