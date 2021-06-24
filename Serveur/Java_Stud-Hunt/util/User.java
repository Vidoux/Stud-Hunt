package util;

import java.util.List;

import util.user.Industry;

public abstract class User {
	private UserTypes userType;
	private String email;
	private String name;
	private String password;
	private List<Industry> industries;

    public User(UserTypes userType, String email, String name, String password, List<Industry> industries){
        this.userType = userType;
        this.email = email;
        this.name = name;
        this.password = password;
        this.industries = industries;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public UserTypes getUserType() {
        return userType;
    }

    @Override
    public String toString() {
        return "User{" +
                "username='" + email + '\'' +
                ", password='" + password + '\'' +
                ", userType=" + userType +
                '}';
    }
}