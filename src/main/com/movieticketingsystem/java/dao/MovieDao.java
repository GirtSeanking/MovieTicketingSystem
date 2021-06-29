package main.com.movieticketingsystem.java.dao;

import main.com.movieticketingsystem.java.domain.*;
import main.com.movieticketingsystem.java.utils.DBUtils;

import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * @className: MovieDao
 * @program: MovieTicketingSystem
 * @description: // 电影数据持久层
 * @author: GirtSeanking
 * @create: 2021-06-28 09:45
 **/

public class MovieDao {

    private DBUtils dbUtils = new DBUtils();

    /**
     * @Author GirtSeanking
     * @Description // 查询所有电影
     * @Date 0:18
     * @Param []
     * @return java.util.List<main.com.movieticketingsystem.java.domain.Movie>
     **/
    public List<Movie> selectAllMovieList() {
        String sql = "select * from movie order by movie_id desc";
        PreparedStatement pst = dbUtils.getPreparedStatement(sql);
        List<Movie> list = new ArrayList<Movie>();
        try {
            ResultSet rs = pst.executeQuery();
            while (rs.next()) {
                list.add(new Movie(rs.getInt(1), rs.getString(2), rs.getInt(3)));
            }
            dbUtils.close(rs);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return list;
    }

    /**
     * @Author GirtSeanking
     * @Description // 点赞电影，添加关联
     * @Date 0:18
     * @Param [movie, user]
     * @return void
     **/
    public void insertMovieUser(Movie movie, User user) {
        String sql = "insert into movie_user(movie_id, login_name) values (?, ?)";
        PreparedStatement pst = dbUtils.getPreparedStatement(sql);
        try {
            pst.setInt(1, movie.getMovieId());
            pst.setString(2, user.getLoginName());
            pst.executeUpdate();
            dbUtils.close();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    /**
     * @Author GirtSeanking
     * @Description // 修改电影点赞数
     * @Date 0:19
     * @Param [movie]
     * @return void
     **/
    public void updateLikeAmounts(Movie movie) {
        String sql = "update movie set like_amount = ? where movie_id = ?";
        PreparedStatement pst = dbUtils.getPreparedStatement(sql);
        try {
            pst.setInt(1, movie.getLikeAmounts());
            pst.setInt(2, movie.getMovieId());
            pst.executeUpdate();
            dbUtils.close();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    /**
     * @Author GirtSeanking
     * @Description // 删除点赞，电影与用户的关联
     * @Date 0:19
     * @Param [movie, user]
     * @return void
     **/
    public void deleteMovieUser(Movie movie, User user) {
        String sql = "delete from movie_user where movie_id = ? and login_name = ?";
        PreparedStatement pst = dbUtils.getPreparedStatement(sql);
        try {
            pst.setInt(1, movie.getMovieId());
            pst.setString(2, user.getLoginName());
            pst.executeUpdate();
            dbUtils.close();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    /**
     * @Author GirtSeanking
     * @Description // 判断用户是否点赞
     * @Date 0:19
     * @Param [movie, user]
     * @return boolean
     **/
    public boolean userIsLike(Movie movie, User user) {
        boolean isLike = false;
        String sql = "select * from movie_user where movie_id = ? and login_name = ?";
        PreparedStatement pst = dbUtils.getPreparedStatement(sql);
        try {
            pst.setInt(1, movie.getMovieId());
            pst.setString(2, user.getLoginName());
            ResultSet rs = pst.executeQuery();
            if (rs.next()) {
                isLike = true;
            }
            dbUtils.close(rs);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return isLike;
    }

    /**
     * @Author GirtSeanking
     * @Description // 根据一场电影查询今天的电影场次
     * @Date 0:20
     * @Param [movieId]
     * @return java.util.List<main.com.movieticketingsystem.java.domain.Session>
     **/
    public List<Session> selectAllSessionByMovieId(int movieId) {
        List<Session> results = new ArrayList<>();
        String sql = "select * from session where movie_id = ? and to_days(start_time) = to_days(now())";
        PreparedStatement pst = dbUtils.getPreparedStatement(sql);
        try {
            pst.setInt(1, movieId);
            ResultSet rs = pst.executeQuery();
            while (rs.next()) {
                results.add(new Session(rs.getInt(1), rs.getTime(2), rs.getTime(3), rs.getString(4), rs.getInt(5)));
            }
            dbUtils.close(rs);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return results;
    }

    /**
     * @Author GirtSeanking
     * @Description // 通过电影场次查找演播厅，多表左连接查找
     * @Date 0:20
     * @Param [sessionId]
     * @return main.com.movieticketingsystem.java.domain.Hall
     **/
    public Hall selectHallBySessionId(int sessionId) {
        Hall hall = new Hall();
        String sql = "select h.hall_id, h.hall_name, h.capacity from hall h left join session_hall sh on h.hall_id = sh.hall_id left join session s on sh.session_id = s.session_id where s.session_id = ?";
        PreparedStatement pst = dbUtils.getPreparedStatement(sql);
        try {
            pst.setInt(1, sessionId);
            ResultSet rs = pst.executeQuery();
            if (rs.next()) {
                hall.setHallId(rs.getInt(1));
                hall.setHallName(rs.getString(2));
                hall.setCapacity(rs.getInt(3));
            }
            dbUtils.close(rs);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return hall;
    }

    /**
     * @Author GirtSeanking
     * @Description // 嵌套查询  查询该场次有可用座位的信息
     * @Date 0:22
     * @Param [hallId]
     * @return java.util.List<main.com.movieticketingsystem.java.domain.Seat>
     **/
    public List<Seat> selectAvaliable(int hallId) {
        List<Seat> seats = new ArrayList<>();
        String sql = "select * from seat s where s.seat_id not in (select seat_id from orde where status = 1) and hall_id = ?";
        PreparedStatement pst = dbUtils.getPreparedStatement(sql);
        try {
            pst.setInt(1, hallId);
            ResultSet rs = pst.executeQuery();
            while (rs.next()) {
                seats.add(new Seat(rs.getInt(1), rs.getInt(2), rs.getInt(3), rs.getInt(4)));
            }
            dbUtils.close(rs);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return seats;
    }

    /**
     * @Author GirtSeanking
     * @Description // 增加订单
     * @Date 0:23
     * @Param [order]
     * @return void
     **/
    public void insertOrder(Order order) {
        String sql = "insert into orde(ticket_code, buy_time, hall_id, session_id, movie_id, seat_id, login_name) values(?,?,?,?,?,?,?)";
        PreparedStatement pst = dbUtils.getPreparedStatement(sql);
        try {
            pst.setString(1, order.getTicketCode());
            pst.setDate(2, new Date(order.getBuyTime().getTime()));
            pst.setInt(3, order.getHallId());
            pst.setInt(4, order.getSessionId());
            pst.setInt(5, order.getMovieId());
            pst.setInt(6, order.getSeatId());
            pst.setString(7, order.getLoginName());
            pst.executeUpdate();
            dbUtils.close();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    /**
     * @Author GirtSeanking
     * @Description // 单表查询  通过电影编号查询电影信息
     * @Date 0:23
     * @Param [movieId]
     * @return main.com.movieticketingsystem.java.domain.Movie
     **/
    public Movie selectMovieById(int movieId) {
        Movie movie = new Movie();
        String sql = "select * from movie where movie_id = ?";
        PreparedStatement pst = dbUtils.getPreparedStatement(sql);
        try {
            pst.setInt(1, movieId);
            ResultSet rs = pst.executeQuery();
            if (rs.next()) {
                movie.setMovieId(rs.getInt(1));
                movie.setMovieName(rs.getString(2));
                movie.setLikeAmounts(rs.getInt(3));
            }
            dbUtils.close(rs);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return movie;
    }

    /**
     * @Author GirtSeanking
     * @Description // 单表查询  通过座位编号查询座位信息
     * @Date 0:24
     * @Param [seatId]
     * @return main.com.movieticketingsystem.java.domain.Seat
     **/
    public Seat selectSeatById(int seatId) {
        Seat seat = new Seat();
        String sql = "select * from seat where seat_id = ?";
        PreparedStatement pst = dbUtils.getPreparedStatement(sql);
        try {
            pst.setInt(1, seatId);
            ResultSet rs = pst.executeQuery();
            if (rs.next()) {
                seat.setSeatId(rs.getInt(1));
                seat.setRowNum(rs.getInt(2));
                seat.setColNum(rs.getInt(3));
            }
            dbUtils.close(rs);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return seat;
    }

    /**
     * @Author GirtSeanking
     * @Description // 单表查询  通过观影厅编号查询观影厅信息
     * @Date 0:24
     * @Param [hallId]
     * @return main.com.movieticketingsystem.java.domain.Hall
     **/
    public Hall selectHallById(int hallId) {
        Hall hall = new Hall();
        String sql = "select * from hall where hall_id = ?";
        PreparedStatement pst = dbUtils.getPreparedStatement(sql);
        try {
            pst.setInt(1, hallId);
            ResultSet rs = pst.executeQuery();
            if (rs.next()) {
                hall.setHallId(rs.getInt(1));
                hall.setHallName(rs.getString(2));
                hall.setCapacity(rs.getInt(3));
            }
            dbUtils.close(rs);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return hall;
    }

    /**
     * @Author GirtSeanking
     * @Description // 单表查询 通过电影场次编号查询场次信息
     * @Date 0:25
     * @Param [sessionId]
     * @return main.com.movieticketingsystem.java.domain.Session
     **/
    public Session selectSessionById(int sessionId) {
        Session session = new Session();
        String sql = "select * from session where session_id = ?";
        PreparedStatement pst = dbUtils.getPreparedStatement(sql);
        try {
            pst.setInt(1, sessionId);
            ResultSet rs= pst.executeQuery();
            if (rs.next()) {
                session.setSessionId(rs.getInt(1));
                session.setStartTime(rs.getTimestamp(2));
                session.setEndTime(rs.getTimestamp(3));
                session.setPrice(rs.getString(4));
                session.setMovieId(rs.getInt(5));
            }
            dbUtils.close(rs);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return session;
    }

    /**
     * @Author GirtSeanking
     * @Description // 单表查询  通过用户信息查询读者的所有订单信息
     * @Date 0:25
     * @Param [loginName]
     * @return java.util.List<main.com.movieticketingsystem.java.domain.Order>
     **/
    public List<Order> selectAllOrderByUser(String loginName) {
        List<Order> orderList = new ArrayList<Order>();
        String sql = "select * from orde where login_name = ?";
        PreparedStatement pst = dbUtils.getPreparedStatement(sql);
        try {
            pst.setString(1, loginName);
            ResultSet rs = pst.executeQuery();
            while (rs.next()) {
                orderList.add(new Order(rs.getInt(1), rs.getString(2),
                        rs.getTimestamp(3), rs.getInt(4), rs.getInt(5),
                        rs.getInt(6), rs.getInt(8), rs.getString(9), rs.getString(7)));
            }
            dbUtils.close(rs);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return orderList;
    }

    /**
     * @Author GirtSeanking
     * @Description // 修改订单状态
     * @Date 0:26
     * @Param [orderId, status]
     * @return void
     **/
    public void updateOrderStatus(int orderId, String status) {
        String sql = "update orde set status = ? where orde_id = ?";
        PreparedStatement pst = dbUtils.getPreparedStatement(sql);
        try {
            pst.setString(1, status);
            pst.setInt(2, orderId);
            int rs = pst.executeUpdate();
            dbUtils.close();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    /**
     * @Author GirtSeanking
     * @Description // 多表左连接查询  通过用户信息查询读者收藏的电影信息
     * @Date 0:26
     * @Param [loginName]
     * @return java.util.List<main.com.movieticketingsystem.java.domain.Movie>
     **/
    public List<Movie> selectFavoriteMovie(String loginName) {
        List<Movie> list = new ArrayList<Movie>();
        String sql = "select m.movie_id, m.movie_name, m.like_amount from movie m left join favorites f on f.movie_id = m.movie_id left join user u on u.login_name = f.login_name where u.login_name = ?";
        PreparedStatement pst = dbUtils.getPreparedStatement(sql);
        try {
            pst.setString(1, loginName);
            ResultSet rs = pst.executeQuery();
            while (rs.next()) {
                list.add(new Movie(rs.getInt(1), rs.getString(2), rs.getInt(3)));
            }
            dbUtils.close(rs);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return list;
    }

    /**
     * @Author GirtSeanking
     * @Description // 单表查询  判断用户是否收藏该电影
     * @Date 0:27
     * @Param [movieId, loginName]
     * @return boolean
     **/
    public boolean userIsFavorite(int movieId, String loginName) {
        boolean isFavorite = false;
        String sql = "select * from favorites where movie_id = ? and login_name = ?";
        PreparedStatement pst = dbUtils.getPreparedStatement(sql);
        try {
            pst.setInt(1, movieId);
            pst.setString(2, loginName);
            ResultSet rs = pst.executeQuery();
            if (rs.next()) {
                isFavorite = !isFavorite;
            }
            dbUtils.close(rs);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return isFavorite;
    }

    /**
     * @Author GirtSeanking
     * @Description // 单表删除  用户取消该电影的收藏
     * @Date 0:27
     * @Param [movieId, loginName]
     * @return void
     **/
    public void cancelFavorite(int movieId, String loginName) {
        String sql = "delete from favorites where movie_id = ? and login_name = ?";
        PreparedStatement pst = dbUtils.getPreparedStatement(sql);
        try {
            pst.setInt(1, movieId);
            pst.setString(2, loginName);
            pst.executeUpdate();
            dbUtils.close();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    /**
     * @Author GirtSeanking
     * @Description // 增加  用户收藏该电影
     * @Date 0:28
     * @Param [movieId, loginName]
     * @return void
     **/
    public void favorite(int movieId, String loginName) {
        String sql = "insert into favorites(movie_id, login_name) values(?,?)";
        PreparedStatement pst = dbUtils.getPreparedStatement(sql);
        try {
            pst.setInt(1, movieId);
            pst.setString(2, loginName);
            pst.executeUpdate();
            dbUtils.close();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }
}