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
	
	public void createUser(String email, String name, String forname, String password, UserTypes userType, List<Object> infos) {
		data.createUser(email, name, forname, password, userType, infos);
	}
	
	public void addOrEditProfilePicture(String email, File file) {
		data.addOrEditProfilePicture(email, file);
	}
	
	public void addOrEditCV(String email, File file) {
		data.addOrEditCV(email, file);
	}
	
	public Blob getCV(String email) {
		return data.getCV(email);
	}
	
	public Blob getProfilePicture(String email) {
		return data.getProfilePicture(email);
	}
}
