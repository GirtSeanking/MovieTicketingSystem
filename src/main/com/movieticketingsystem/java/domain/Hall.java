package main.com.movieticketingsystem.java.domain;

/**
 * @className: Hall
 * @program: MovieTicketingSystem
 * @description: // 观影厅
 * @author: GirtSeanking
 * @create: 2021-06-28 15:57
 **/

public class Hall {

    private int hallId;

    private String hallName;

    private int capacity;

    public int getHallId() {
        return hallId;
    }

    public void setHallId(int hallId) {
        this.hallId = hallId;
    }

    public String getHallName() {
        return hallName;
    }

    public void setHallName(String hallName) {
        this.hallName = hallName;
    }

    public int getCapacity() {
        return capacity;
    }

    public void setCapacity(int capacity) {
        this.capacity = capacity;
    }

    public Hall(int hallId, String hallName, int capacity) {
        this.hallId = hallId;
        this.hallName = hallName;
        this.capacity = capacity;
    }

    public Hall() {
    }

    @Override
    public String toString() {
        return "Hall{" +
                "hallId=" + hallId +
                ", hallName='" + hallName + '\'' +
                ", capacity=" + capacity +
                '}';
    }
}