package main.com.movieticketingsystem.java.utils.list;

import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Font;
import main.com.movieticketingsystem.java.Main;
import main.com.movieticketingsystem.java.Service.Impl.MovieServiceImpl;
import main.com.movieticketingsystem.java.common.constant.Constant;
import main.com.movieticketingsystem.java.controller.UserMainController;
import main.com.movieticketingsystem.java.domain.Movie;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * @className: MovieListUtils
 * @program: MovieTicketingSystem
 * @description: // 加载电影信息列表
 * @author: GirtSeanking
 * @create: 2021-06-28 09:10
 **/

public class MovieListUtils {

    public List<MovieAnchorPaneCell> getMovieList(List<Movie> movieList, String type, UserMainController controller) {
        List<MovieAnchorPaneCell> movieItemList = new ArrayList<>();
        for (Movie movie : movieList) {
            movieItemList.add(new MovieAnchorPaneCell(movie, type, controller));
        }
        return movieItemList;
    }

    public static class MovieAnchorPaneCell extends AnchorPane {
        private ImageView movieImage = new ImageView();
        private Label movieLabel = new Label();
        private Button likeButton = new Button();
        private Button ticketBtn = new Button();
        private Button favoriteBtn = new Button();
        private boolean isLike = false;
        private boolean isFavorite = false;

        private MovieServiceImpl movieServiceImpl = new MovieServiceImpl();


        public MovieAnchorPaneCell(Movie movie, String type, UserMainController controller) {
            isFavorite = movieServiceImpl.userIsFavorite(movie.getMovieId(), Main.getUser().getLoginName());
            movieImage.setImage(new Image(Objects.requireNonNull(Main.class.getResourceAsStream("../resources/static/img/3DMovie.png"))));
            movieImage.setFitWidth(100);
            movieImage.setFitHeight(100);
            movieImage.setLayoutX(5);
            movieImage.setLayoutY(5);

            movieLabel.setText(movie.getMovieName());
            movieLabel.setLayoutX(150);
            movieLabel.setLayoutY(30);
            movieLabel.setFont(new Font("Cambria", 25));

            if (type.equals(Constant.FAVORITES_TYPE)) {
                favoriteBtn.setText("取消收藏");
                favoriteBtn.setLayoutX(735);
                favoriteBtn.setLayoutY(45);
                favoriteBtn.setStyle("-fx-background-radius: 5em;");
            } else if (!isFavorite) {
                favoriteBtn.setText("收藏");
                favoriteBtn.setLayoutX(680);
                favoriteBtn.setLayoutY(45);
                favoriteBtn.setStyle("-fx-background-radius: 5em;");
            }
            favoriteBtn.setOnMouseClicked(new EventHandler<MouseEvent>() {
                @Override
                public void handle(MouseEvent event) {
                    if (isFavorite) {
                        movieServiceImpl.cancelFavorite(movie.getMovieId(), Main.getUser().getLoginName());
                        controller.setMovieList(movieServiceImpl.selectFavoriteMovie(Main.getUser().getLoginName()), Constant.FAVORITES_TYPE);
                    } else {
                        movieServiceImpl.favorite(movie.getMovieId(), Main.getUser().getLoginName());
                    }
                    favoriteBtn.setVisible(false);
                }
            });

            ticketBtn.setText("订票");
            ticketBtn.setLayoutX(735);
            ticketBtn.setLayoutY(45);
            ticketBtn.setStyle("-fx-background-radius: 5em;");
            ticketBtn.setOnMouseClicked(new EventHandler<MouseEvent>() {
                @Override
                public void handle(MouseEvent event) {
                    try {
                        Main.setMovie(movie);
                        FXMLLoader loader = new FXMLLoader(getClass().getResource("/main/com/movieticketingsystem/resources/view/ticket.fxml"));
                        Parent root = loader.load();
                        Main.getTicketStage().setScene(new Scene(root));
                        Main.getTicketStage().show();
                        Main.getTicketStage().setResizable(false
                        );
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            });

            if (movieServiceImpl.userIsLike(movie)) {
                likeButton.setText(String.valueOf(movie.getLikeAmounts() - 1) + " + " + 1);
                isLike = true;
            } else {
                likeButton.setText("点赞");
                isLike = false;
            }
            likeButton.setLayoutX(790);
            likeButton.setLayoutY(45);
            likeButton.setStyle("-fx-background-radius: 5em;");
            likeButton.setOnMouseClicked(new EventHandler<MouseEvent>() {
                @Override
                public void handle(MouseEvent event) {
                    if (!isLike) {
                        likeButton.setText(movie.getLikeAmounts() + " + " + 1);
                        movieServiceImpl.likeHandle(movie, "like");
                    } else {
                        likeButton.setText("点赞");
                        movieServiceImpl.likeHandle(movie, "dislike");
                    }
                    isLike = !isLike;
                }
            });

            if (type.equals(Constant.LIKE_TYPE)) {
                ticketBtn.setVisible(true);
                likeButton.setVisible(true);
            } else if (type.equals(Constant.FAVORITES_TYPE)) {
                ticketBtn.setVisible(false);
                likeButton.setVisible(false);
            }

            this.getChildren().addAll(movieImage, movieLabel, favoriteBtn, ticketBtn, likeButton);
        }
    }

}