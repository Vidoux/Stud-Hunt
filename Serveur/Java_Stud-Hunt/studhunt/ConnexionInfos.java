package studhunt;

public class ConnexionInfos {
    boolean validPassword;
    UserTypes user;
    
    public ConnexionInfos(boolean validPassword, UserTypes user) {
    	this.validPassword = validPassword;
    	this.user = user;
    }
}
