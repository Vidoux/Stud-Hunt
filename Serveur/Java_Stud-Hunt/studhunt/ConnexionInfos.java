package studhunt;

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
        return "ConnexionInfos{" +
                "validPassword=" + validPassword +
                ", userType=" + userType +
                ", name='" + name + '\'' +
                ", forname='" + forname + '\'' +
                '}';
    }
}
