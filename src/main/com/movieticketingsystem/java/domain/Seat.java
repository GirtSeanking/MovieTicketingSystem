package main.com.movieticketingsystem.java.domain;

/**
 * @className: Seat
 * @program: MovieTicketingSystem
 * @description: // 座位实体类
 * @author: GirtSeanking
 * @create: 2021-06-28 17:15
 **/

public class Seat {

    private int seatId;

    private int rowNum;

    private int colNum;

    private int hallId;

    public Seat(int seatId, int rowNum, int colNum, int hallId) {
        this.seatId = seatId;
        this.rowNum = rowNum;
        this.colNum = colNum;
        this.hallId = hallId;
    }

    public Seat() {
    }

    public int getSeatId() {
        return seatId;
    }

    public void setSeatId(int seatId) {
        this.seatId = seatId;
    }

    public int getRowNum() {
        return rowNum;
    }

    public void setRowNum(int rowNum) {
        this.rowNum = rowNum;
    }

    public int getColNum() {
        return colNum;
    }

    public void setColNum(int colNum) {
        this.colNum = colNum;
    }

    @Override
    public String toString() {
        return "Seat{" +
                "seatId=" + seatId +
                ", rowNum=" + rowNum +
                ", colNum=" + colNum +
                ", hallId=" + hallId +
                '}';
    }
}