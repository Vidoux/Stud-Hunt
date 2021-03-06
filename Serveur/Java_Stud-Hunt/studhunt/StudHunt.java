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
	 * Delete a user and it's dependencies
	 * 
	 * @param email email of the user
	 * 
	 * @return true if deleted
	 */
	boolean deleteUser(String email) {
		return instance.data.deleteUser(email);
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
	 * Delete a CV
	 * 
	 * @param email email of the user
	 * 
	 * @return true if deleted
	 */
	boolean deleteCV(String email) {
		return instance.data.deleteCV(email);
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
	 * Delete a profile picture
	 * 
	 * @param email email of the user
	 * 
	 * @return true if deleted
	 */
	boolean deleteProfilePicture(String email) {
		return instance.data.deleteProfilePicture(email);
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
	 * Uptade project values
	 * 
	 * @param email       the email of the owner
	 * @param projectName the new project name
	 * @param projectBio  the new project bio
	 * @param date        the new realisation year
	 * 
	 * @return true if updated
	 */
	boolean updateProject(String email, String projectName, String projectBio, String date) {
		return instance.data.updateProject(email, projectName, projectBio, date);
	}

	/**
	 * Delete a project
	 * 
	 * @param email email of the user
	 * 
	 * @return true if deleted
	 */
	boolean deleteProject(String email) {
		return instance.data.deleteProject(email);
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
	 * Update a job offer in the DB
	 * 
	 * @param offerType      the type of offer
	 * @param email          the email of the company
	 * @param apprenticeship if it's in apprenticeship
	 * @param internship     if it's in internship
	 * @param levelstudy     the level of study required
	 * @param industry       the industry domain
	 * @param startingdate   the starting date of the contract
	 * @param contractlen    the durantion of the contract
	 * 
	 * @return true if the job offer has been well updated
	 */
	boolean updateJobOffer(int offerType, String email, int apprenticeship, int internship, int levelstudy,
			String industry, Date startingdate, int contractlen) {
		return instance.data.updateJobOffer(offerType, email, apprenticeship, internship, levelstudy, industry,
				startingdate, contractlen);
	}

	/**
	 * Delete a job offer
	 * 
	 * @param email email of the user
	 * 
	 * @return true if deleted
	 */
	boolean deleteJobOffer(String email) {
		return instance.data.deleteJobOffer(email);
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

	/**
	 * Update a school
	 * 
	 * @param id_School  the id of the school
	 * @param schoolName the school new name
	 * 
	 * @return true if updated
	 */
	boolean updateSchool(int id_School, String schoolName) {
		return instance.data.updateSchool(id_School, schoolName);
	}

	/**
	 * Delete a school
	 * 
	 * @param email email of the user
	 * 
	 * @return true if deleted
	 */
	boolean deleteSchool(int id_School) {
		return instance.data.deleteSchool(id_School);
	}

	/**
	 * Delete a school association with student
	 * 
	 * @param id_School the school associated
	 * @param email     the student
	 * 
	 * @return true if deleted
	 */
	boolean deleteSchoolStudentAssociation(int id_School, String email) {
		return instance.data.deleteSchoolStudentAssociation(id_School, email);
	}
}
