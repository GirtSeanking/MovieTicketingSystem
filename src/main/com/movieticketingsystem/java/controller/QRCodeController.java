package main.com.movieticketingsystem.java.controller;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import main.com.movieticketingsystem.java.Main;
import main.com.movieticketingsystem.java.Service.Impl.MovieServiceImpl;
import main.com.movieticketingsystem.java.domain.*;

import java.util.Date;

/**
 * @className: QRCodeController
 * @program: MovieTicketingSystem
 * @description: //
 * @author: GirtSeanking
 * @create: 2021-06-28 16:42
 **/

public class QRCodeController {

    @FXML
    private Button paidBtn;

    private MovieServiceImpl movieServiceImpl = new MovieServiceImpl();

    public void paidBtnOnMouseClicked() {
        Seat seat = movieServiceImpl.selectAvaliable(Main.getHall().getHallId());
        Order order = new Order();
        order.setTicketCode("G-" + Main.getHall().getHallId() + "-" + seat.getRowNum() + "-" + seat.getColNum() + "-" + Main.getMovie().getMovieId());
        order.setBuyTime(new Date());
        order.setHallId(Main.getHall().getHallId());
        order.setSessionId(Main.getSession().getSessionId());
        order.setMovieId(Main.getMovie().getMovieId());
        order.setSeatId(seat.getSeatId());
        order.setLoginName(Main.getUser().getLoginName());
        movieServiceImpl.insertOrder(order);
        Main.getUserMainStage().show();
        Main.getQrCodeStage().close();
    }

}