package main.com.movieticketingsystem.java.domain;

import java.util.Date;

/**
 * @className: Order
 * @program: MovieTicketingSystem
 * @description: // 订单实体类
 * @author: GirtSeanking
 * @create: 2021-06-28 17:12
 **/

public class Order {

    private int orderId;

    private String ticketCode;

    private Date buyTime;

    private int hallId;

    private int sessionId;

    private int movieId;

    private int seatId;

    private String status;

    private String loginName;

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getLoginName() {
        return loginName;
    }

    public void setLoginName(String loginName) {
        this.loginName = loginName;
    }

    public int getOrderId() {
        return orderId;
    }

    public void setOrderId(int orderId) {
        this.orderId = orderId;
    }

    public String getTicketCode() {
        return ticketCode;
    }

    public void setTicketCode(String ticketCode) {
        this.ticketCode = ticketCode;
    }

    public Date getBuyTime() {
        return buyTime;
    }

    public void setBuyTime(Date buyTime) {
        this.buyTime = buyTime;
    }

    public int getHallId() {
        return hallId;
    }

    public void setHallId(int hallId) {
        this.hallId = hallId;
    }

    public int getSessionId() {
        return sessionId;
    }

    public void setSessionId(int sessionId) {
        this.sessionId = sessionId;
    }

    public int getMovieId() {
        return movieId;
    }

    public void setMovieId(int movieId) {
        this.movieId = movieId;
    }

    public int getSeatId() {
        return seatId;
    }

    public void setSeatId(int seatId) {
        this.seatId = seatId;
    }

    public Order() {
    }

    public Order(int orderId, String ticketCode, Date buyTime, int hallId, int sessionId, int movieId, int seatId, String status, String loginName) {
        this.orderId = orderId;
        this.ticketCode = ticketCode;
        this.buyTime = buyTime;
        this.hallId = hallId;
        this.sessionId = sessionId;
        this.movieId = movieId;
        this.seatId = seatId;
        this.status = status;
        this.loginName = loginName;
    }

    @Override
    public String toString() {
        return "Order{" +
                "orderId=" + orderId +
                ", ticketCode='" + ticketCode + '\'' +
                ", buyTime=" + buyTime +
                ", hallId=" + hallId +
                ", sessionId=" + sessionId +
                ", movieId=" + movieId +
                ", seatId=" + seatId +
                ", status=" + status +
                ", loginName='" + loginName + '\'' +
                '}';
    }
}