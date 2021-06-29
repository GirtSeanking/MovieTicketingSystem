package main.com.movieticketingsystem.java.domain;

import java.util.Date;

/**
 * @className: Session
 * @program: MovieTicketingSystem
 * @description: // 电影场次
 * @author: GirtSeanking
 * @create: 2021-06-28 14:56
 **/

public class Session {

    private int sessionId;

    private Date startTime;

    private Date endTime;

    private String price;

    private int movieId;

    public int getSessionId() {
        return sessionId;
    }

    public void setSessionId(int sessionId) {
        this.sessionId = sessionId;
    }

    public Date getStartTime() {
        return startTime;
    }

    public void setStartTime(Date startTime) {
        this.startTime = startTime;
    }

    public Date getEndTime() {
        return endTime;
    }

    public void setEndTime(Date endTime) {
        this.endTime = endTime;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public int getMovieId() {
        return movieId;
    }

    public void setMovieId(int movieId) {
        this.movieId = movieId;
    }

    public Session(int sessionId, Date startTime, Date endTime, String price, int movieId) {
        this.sessionId = sessionId;
        this.startTime = startTime;
        this.endTime = endTime;
        this.price = price;
        this.movieId = movieId;
    }

    public Session() {
    }

    @Override
    public String toString() {
        return "Session{" +
                "sessionId=" + sessionId +
                ", startTime=" + startTime +
                ", endTime=" + endTime +
                ", price='" + price + '\'' +
                ", movieId=" + movieId +
                '}';
    }
}