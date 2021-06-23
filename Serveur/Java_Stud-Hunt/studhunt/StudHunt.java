package studhunt;

import java.util.HashMap;
import java.util.List;

import util.Pair;
import util.References;
import util.UserTypes;

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
	
	public boolean createUser(String email, String name, String password, UserTypes userType, HashMap<References, String> refernces, List<Pair> infos) {
		return data.createUser(email, name, password, userType, refernces, infos);
	}
	
	public void setProfilePicture(String email, byte[] file) {
		data.setProfilePicture(email, file);
	}
	
	public void setCV(String email, byte[] file) {
		data.setCV(email, file);
	}
	
	public byte[] getCV(String email) {
		return data.getCV(email);
	}
	
	public byte[] getProfilePicture(String email) {
		return data.getProfilePicture(email);
	}
}
