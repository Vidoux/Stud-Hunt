package studhunt;

public class ConnexionInfos {
    private boolean validPassword;
    private UserTypes userType;
    
    public ConnexionInfos(boolean validPassword, UserTypes user) {
    	this.validPassword = validPassword;
    	this.userType = user;
    }


    public boolean isValidPassword() {
        return validPassword && userType!=null;
    }

    public UserTypes getUserType() {
        return userType;
    }
}
