package studhunt;

import java.io.File;
import java.sql.Blob;
import java.util.List;

public class StudHunt {
	
	private static StudHunt instance;
	private PersistentStudHunt data;
	
	static {
		instance = new StudHunt();
	}
	
	public static StudHunt getInstance() {
		return instance;
	}
	
	public void setData(PersistentStudHunt data) {
		if (this.data == null) this.data = data;
	}
	
	public ConnexionInfos getUserConnection(String login, String password) {
		return data.getUserConnection(login, password);
	}
	
	public boolean createUser(String email, String name, String password, UserTypes userType, List<Object> infos) {
		return data.createUser(email, name, password, userType, infos);
	}
	
	public boolean setProfilePicture(String email, File file) {
		return data.setProfilePicture(email, file);
	}
	
	public boolean setCV(String email, File file) {
		return data.setCV(email, file);
	}
	
	public Blob getCV(String email) {
		return data.getCV(email);
	}
	
	public Blob getProfilePicture(String email) {
		return data.getProfilePicture(email);
	}
}
