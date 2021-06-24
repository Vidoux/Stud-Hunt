package studhunt;

import java.sql.Date;

import util.User;
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
		if (this.data == null)
			this.data = data;
	}

	/**
	 * Call user creation in DB
	 * 
	 * @param user the user we'll add to DB
	 * 
	 * @return true if the user has been created well
	 */
	public boolean createUser(User user) {
		return instance.data.createUser(user);
	}

	/**
	 * Call user update in DB
	 * 
	 * @param user the user to update in the DB
	 * 
	 * @return true if the user has been well updated
	 */
	public boolean updateUser(User user) {
		return instance.data.updateUser(user);
	}

	/**
	 * Retrive a user with te combinaison email/password given
	 * 
	 * @param email    the email of the searched user
	 * @param password the password of the searched user
	 * 
	 * @return the user, or null if not found
	 */
	public User getUser(String email, String password) {
		return instance.data.getUser(email, password);
	}

	/**
	 * Get the CV associated with the user if it exist
	 * 
	 * @param email the email of the user
	 * 
	 * @return The CV as a Blob
	 */
	public byte[] getCV(String email) {
		return instance.data.getCV(email);
	}

	/**
	 * Call setFile() for a CV
	 * 
	 * @param email the email of the selected user
	 * @param cv    the PDF that will be linked to this user
	 * 
	 * @return true if the PDF has been added without problem
	 */
	public boolean setCV(String email, byte[] cv) {
		return instance.data.setCV(email, cv);
	}

	/**
	 * Get the picture associated with the user if it exist
	 * 
	 * @param email the email of the user
	 * 
	 * @return The picture as a Blob
	 */
	public byte[] getProfilePicture(String email) {
		return instance.data.getProfilePicture(email);
	}

	/**
	 * Call setFile() for a profile picture
	 * 
	 * @param email          the email of the selected user
	 * @param profilePicture the picture that will be linked to this user
	 * 
	 * @return true if the picture has been added without problem
	 */
	public boolean setProfilePicture(String email, byte[] profilePicture) {
		return instance.data.setProfilePicture(email, profilePicture);
	}

	/**
	 * Get the user type of a given email
	 * 
	 * @param email the email to serch for
	 * 
	 * @return the user type
	 */
	public UserTypes getUserType(String email) {
		return instance.data.getUserType(email);
	}

	/**
	 * Create a project in the DB
	 * 
	 * @param email       the user's email
	 * @param projectName the project name
	 * @param projectBio  the project description
	 * @param date        the project date
	 * 
	 * @return true if the project has been well created
	 */
	public boolean createProject(String email, String projectName, String projectBio, int date) {
		return instance.data.createProject(email, projectName, projectBio, date);
	}

	/**
	 * Create a job offer in the DB
	 * 
	 * @param offertype      the type of offer
	 * @param email          the email of the company
	 * @param apprenticeship if it's in apprenticeship
	 * @param internship     if it's in internship
	 * @param levelstudy     the level of study required
	 * @param industry       the industry domain
	 * @param startingdate   the starting date of the contract
	 * @param contractlen    the durantion of the contract
	 * 
	 * @return true if the job offer has been well created
	 */
	public boolean createJobOffer(int offertype, String email, int apprenticeship, int internship, int levelstudy,
			String industry, Date startingdate, int contractlen) {
		return instance.data.createJobOffer(offertype, email, apprenticeship, internship, levelstudy, industry,
				startingdate, contractlen);
	}

	/**
	 * Create a school in the DB
	 * 
	 * @param name the school name
	 * 
	 * @return true if the school has been well created
	 */
	public boolean createSchool(String name) {
		return instance.data.createSchool(name);
	}
}
