package main.com.movieticketingsystem.java.Service;

import main.com.movieticketingsystem.java.domain.*;

import java.util.List;

public interface MovieService {

    public List<Movie> selectAllMovieList();

    public void likeHandle(Movie movie, String type);

    public boolean userIsLike(Movie movie);

    public List<Session> selectAllSessionByMovieId(int movieId);

    public Hall selectHallBySessionId(int sessionId);

    public Seat selectAvaliable(int hallId);

    public void insertOrder(Order order);

    public Movie selectMovieById(int movieId);

    public Seat selectSeatById(int seatId);

    public Hall selectHallById(int hallId);

    public Session selectSessionById(int sessionId);

    public List<Order> selectAllOrderByUser(String loginName);

    public void updateOrderStatus(int orderId, String status);

    public List<Movie> selectFavoriteMovie(String loginName);

    public boolean userIsFavorite(int movieId, String loginName);

    public void cancelFavorite(int movieId, String loginName);

    public void favorite(int movieId, String loginName);
}
