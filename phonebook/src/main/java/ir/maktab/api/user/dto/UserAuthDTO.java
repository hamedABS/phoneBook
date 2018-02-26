package ir.maktab.api.user.dto;

import ir.maktab.model.user.User;

/**
 * Created by Hamed-Abbaszadeh on 2/21/2018.
 */
public class UserAuthDTO {

    private String username;
    private String password;
    private String token;

    public UserAuthDTO(String username , String password , String token){
        this.username = username;
        this.password = User.hashGenerator(password);
        this.token = token;
    }

    public UserAuthDTO(String username, String password) {
        this.username = username;
        this.password = password;
    }

    public UserAuthDTO(){}

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = User.hashGenerator(password);
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }
}
