package main.com.movieticketingsystem.java.controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.image.ImageView;
import main.com.movieticketingsystem.java.Main;
import main.com.movieticketingsystem.java.Service.Impl.MovieServiceImpl;
import main.com.movieticketingsystem.java.domain.Movie;
import main.com.movieticketingsystem.java.domain.Session;
import main.com.movieticketingsystem.java.utils.list.SessionListUtils;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

/**
 * @className: TicketController
 * @program: MovieTicketingSystem
 * @description: // 订票控制层
 * @author: GirtSeanking
 * @create: 2021-06-28 11:27
 **/

public class TicketController implements Initializable {

    private Movie movie;
    private MovieServiceImpl movieServiceImpl = new MovieServiceImpl();

    @FXML
    private Label movieName;

    @FXML
    private ImageView cover;

    @FXML
    private ListView sessionListView;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        movie = Main.getMovie();
        movieName.setText(movie.getMovieName());

        /* 设置场次列表 */
        setShowTimeList();
    }

    public void setShowTimeList() {
        sessionListView.setStyle("-fx-background-color: #00000000");
        List<Session> sessions = movieServiceImpl.selectAllSessionByMovieId(movie.getMovieId());
        ObservableList<SessionListUtils.SessionAnchorPaneCell> sessionsList = FXCollections.observableArrayList(new SessionListUtils().getSessionList(sessions));
        sessionListView.setItems(sessionsList);
    }
}