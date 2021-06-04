package studhunt;

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
	
	public ConnexionInfos getUser(String login, String password) {
		return data.getUser(login, password);
	}
}
