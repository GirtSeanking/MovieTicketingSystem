package main.com.movieticketingsystem.java.domain;

/**
 * @className: User
 * @program: MovieTicketingSystem
 * @description: // 哟用户实体类
 * @author: GirtSeanking
 * @create: 2021-06-27 23:14
 **/

public class User {

    private String loginName;

    private String password;

    private String phoneNumber;

    private String favorites;

    private String role;

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public String getFavorites() {
        return favorites;
    }

    public void setFavorites(String favorites) {
        this.favorites = favorites;
    }

    public String getLoginName() {
        return loginName;
    }

    public void setLoginName(String loginName) {
        this.loginName = loginName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    @Override
    public String toString() {
        return "User{" +
                "loginName='" + loginName + '\'' +
                ", password='" + password + '\'' +
                ", phoneNumber='" + phoneNumber + '\'' +
                ", favorites='" + favorites + '\'' +
                ", role='" + role + '\'' +
                '}';
    }
}