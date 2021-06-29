package main.com.movieticketingsystem.java.Service.Impl;

import main.com.movieticketingsystem.java.Main;
import main.com.movieticketingsystem.java.Service.MovieService;
import main.com.movieticketingsystem.java.dao.MovieDao;
import main.com.movieticketingsystem.java.domain.*;

import java.util.List;
import java.util.Random;

/**
 * @className: MovieServiceImpl
 * @program: MovieTicketingSystem
 * @description: // 电影服务层实现类
 * @author: GirtSeanking
 * @create: 2021-06-28 09:43
 **/

public class MovieServiceImpl implements MovieService {

    private MovieDao movieDao = new MovieDao();

    @Override
    public List<Movie> selectAllMovieList() {
        return movieDao.selectAllMovieList();
    }

    @Override
    public void likeHandle(Movie movie, String type) {
        User user = Main.getUser();
        if (type.equals("like")) {
            movie.setLikeAmounts(movie.getLikeAmounts() + 1);
            movieDao.insertMovieUser(movie, user);
        } else if (type.equals("dislike")) {
            movie.setLikeAmounts(movie.getLikeAmounts() - 1);
            movieDao.deleteMovieUser(movie, user);
        }
        movieDao.updateLikeAmounts(movie);
    }

    @Override
    public boolean userIsLike(Movie movie) {
        return movieDao.userIsLike(movie, Main.getUser());
    }

    @Override
    public List<Session> selectAllSessionByMovieId(int movieId) {
        return movieDao.selectAllSessionByMovieId(movieId);
    }

    @Override
    public Hall selectHallBySessionId(int sessionId) {
        return movieDao.selectHallBySessionId(sessionId);
    }

    @Override
    public Seat selectAvaliable(int hallId) {
        List<Seat> seats = movieDao.selectAvaliable(hallId);
        Random random = new Random();
        int n = random.nextInt(seats.size());
        return seats.get(n);
    }

    @Override
    public void insertOrder(Order order) {
        movieDao.insertOrder(order);
    }

    @Override
    public Movie selectMovieById(int movieId) {
        return movieDao.selectMovieById(movieId);
    }

    @Override
    public Seat selectSeatById(int seatId) {
        return movieDao.selectSeatById(seatId);
    }

    @Override
    public Hall selectHallById(int hallId) {
        return movieDao.selectHallById(hallId);
    }

    @Override
    public Session selectSessionById(int sessionId) {
        return movieDao.selectSessionById(sessionId);
    }

    @Override
    public List<Order> selectAllOrderByUser(String loginName) {
        return movieDao.selectAllOrderByUser(loginName);
    }

    @Override
    public void updateOrderStatus(int orderId, String status) {
        movieDao.updateOrderStatus(orderId, status);
    }

    @Override
    public List<Movie> selectFavoriteMovie(String loginName) {
        return movieDao.selectFavoriteMovie(loginName);
    }

    @Override
    public boolean userIsFavorite(int movieId, String loginName) {
        return movieDao.userIsFavorite(movieId, loginName);
    }

    @Override
    public void cancelFavorite(int movieId, String loginName) {
        movieDao.cancelFavorite(movieId, loginName);
    }

    @Override
    public void favorite(int movieId, String loginName) {
        movieDao.favorite(movieId, loginName);
    }

}