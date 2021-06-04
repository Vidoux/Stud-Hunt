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
	
	//Mêmes fonctions que celles de StudHuntData
}
