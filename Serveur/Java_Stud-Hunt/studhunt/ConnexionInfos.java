package studhunt;

public class ConnexionInfos {
    private boolean validPassword;
    private UserTypes user;
    
    public ConnexionInfos(boolean validPassword, UserTypes user) {
    	this.validPassword = validPassword;
    	this.user = user;
    }


    public boolean isValidPassword() {
        return validPassword && user!=null;
    }

    public UserTypes getUser() {
        return user;
    }
}
