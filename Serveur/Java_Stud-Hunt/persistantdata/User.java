package persistantdata;

import studhunt.UserTypes;

public class User {
    private String username;
    private String password;
    private UserTypes userType;

    public User(String username,String password, UserTypes userType){
        this.userType = userType;
        this.password = password;
        this.username = username;
    }

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
        this.password = password;
    }

    public UserTypes getUserType() {
        return userType;
    }
}
