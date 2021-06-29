package main.com.movieticketingsystem.java.utils;

import java.sql.*;

/**
 * @className: DBUtils
 * @program: MovieTicketingSystem
 * @description: // 数据库连接工具
 * @author: GirtSeanking
 * @create: 2021-06-27 23:20
 **/

public class DBUtils {

    private String url = "jdbc:mysql://localhost:3306/movie_ticketing?useSSL=false&allowPublicKeyRetrieval=true&serverTimezone=GMT%2B8&zeroDateTimeBehavior=convertToNull";
    private String user = "root";
    private String password = "718820";
    private String driver = "com.mysql.cj.jdbc.Driver";

    private PreparedStatement pst = null;
    private Connection conn = null;

    public PreparedStatement getPreparedStatement(String sql) {
        try {
            Class.forName(driver);
            conn = DriverManager.getConnection(url, user, password);
            pst = conn.prepareStatement(sql);
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
        return pst;
    }

    public void close(ResultSet rs) {
        try {
            if (rs != null) {
                rs.close();
            }
            if (conn != null) {
                conn.close();
            }
            if (pst != null) {
                pst.close();
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    public void close() {
        try {
            if (conn != null) {
                conn.close();
            }
            if (pst != null) {
                pst.close();
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

}