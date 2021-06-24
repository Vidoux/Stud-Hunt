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

	public UserTypes getUserType() {
		return userType;
	}

	public void setUserType(UserTypes userType) {
		this.userType = userType;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public List<Industry> getIndustries() {
		return industries;
	}

	public void setIndustries(List<Industry> industries) {
		this.industries = industries;
	}
}