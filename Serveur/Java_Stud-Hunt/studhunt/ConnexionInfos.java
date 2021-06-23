package studhunt;

import util.UserTypes;

public class ConnexionInfos {
    private boolean validPassword;
    private UserTypes userType;
    private String name;
    private String forname;
    
    public ConnexionInfos(boolean validPassword, UserTypes user, String name, String forname) {
    	this.validPassword = validPassword;
    	this.userType = user;
    	this.name = name;
    	this.forname = forname;
    }
    
    public ConnexionInfos(boolean validPassword, UserTypes user, String name) {
    	this.validPassword = validPassword;
    	this.userType = user;
    	this.name = name;
    	this.forname = "";
    }

    public boolean isValidPassword() {
        return validPassword && userType!=null;
    }

    public UserTypes getUserType() {
        return userType;
    }


	public String getUser() {
		return this.name + " " + this.forname;
	}

    @Override
    public String toString() {
    	String fornameLine = userType.equals(UserTypes.STUDENT) ? ", forname='" + forname + '\'' : "";
        return "ConnexionInfos{" +
                "validPassword=" + validPassword +
                ", userType=" + userType +
                ", name='" + name + '\'' +
                fornameLine +
                '}';
    }
}
