package main.com.movieticketingsystem.java.domain;

/**
 * @className: Movie
 * @program: MovieTicketingSystem
 * @description: // 电影
 * @author: GirtSeanking
 * @create: 2021-06-28 09:12
 **/

public class Movie {

    private int movieId;

    private String movieName;

    private int likeAmounts;

    public Movie(int movieId, String movieName, int likeAmounts) {
        this.movieId = movieId;
        this.movieName = movieName;
        this.likeAmounts = likeAmounts;
    }

    public Movie() {
    }

    public int getMovieId() {
        return movieId;
    }

    public void setMovieId(int movieId) {
        this.movieId = movieId;
    }

    public String getMovieName() {
        return movieName;
    }

    public void setMovieName(String movieName) {
        this.movieName = movieName;
    }

    public int getLikeAmounts() {
        return likeAmounts;
    }

    public void setLikeAmounts(int likeAmounts) {
        this.likeAmounts = likeAmounts;
    }

    @Override
    public String toString() {
        return "Movie{" +
                "movieId=" + movieId +
                ", movieName='" + movieName + '\'' +
                ", likeAmounts=" + likeAmounts +
                '}';
    }
}