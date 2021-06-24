package util;

public abstract class User {
	private UserTypes userType;
	private String email;
	private String name;
	private String password;
	private String bio;
    
    public User(UserTypes userType, String email, String name, String password){
        this.userType = userType;
        this.email = email;
        this.name = name;
        this.password = password;
    }

    public User(UserTypes userType, String email, String name, String password, String bio){
        this.userType = userType;
        this.email = email;
        this.name = name;
        this.password = password;
        this.bio = bio;
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

	public String getBio() {
		return bio;
	}

	public void setBio(String bio) {
		this.bio = bio;
	}
}