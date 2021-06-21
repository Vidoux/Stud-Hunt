package studhunt;

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
}
