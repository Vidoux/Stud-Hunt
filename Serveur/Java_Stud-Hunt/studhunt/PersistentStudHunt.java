package studhunt;

import java.sql.Date;

import util.User;
import util.UserTypes;

public interface PersistentStudHunt {

	/**
	 * Create an user in the DB
	 * 
	 * @param user the user we'll add to DB
	 * 
	 * @return true if the user has been created well
	 */
	boolean createUser(User user);

	/**
	 * Updates a user in the DB
	 * 
	 * @param user the user to update in the DB
	 * 
	 * @return true if the user has been well updated
	 */
	boolean updateUser(User user);

	/**
	 * Retrive a user with te combinaison email/password given
	 * 
	 * @param email    the email of the searched user
	 * @param password the password of the searched user
	 * 
	 * @return the user, or null if not found
	 */
	User getUser(String email, String password);

	/**
	 * Get the CV associated with the user if it exist
	 * 
	 * @param email the email of the user
	 * 
	 * @return The CV as a Blob
	 */
	byte[] getCV(String email);

	/**
	 * Call setFile() for a CV
	 * 
	 * @param email the email of the selected user
	 * @param cv    the PDF that will be linked to this user
	 * 
	 * @return true if the PDF has been added without problem
	 */
	boolean setCV(String email, byte[] cv);

	/**
	 * Get the picture associated with the user if it exist
	 * 
	 * @param email the email of the user
	 * 
	 * @return The picture as a Blob
	 */
	byte[] getProfilePicture(String email);

	/**
	 * Call setFile() for a profile picture
	 * 
	 * @param email          the email of the selected user
	 * @param profilePicture the picture that will be linked to this user
	 * 
	 * @return true if the picture has been added without problem
	 */
	boolean setProfilePicture(String email, byte[] profilePicture);

	/**
	 * Get the user type of a given email
	 * 
	 * @param email the email to serch for
	 * 
	 * @return the user type
	 */
	UserTypes getUserType(String email);

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
	boolean createProject(String email, String projectName, String projectBio, int date);

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
	boolean createJobOffer(int offertype, String email, int apprenticeship, int internship, int levelstudy,
			String industry, Date startingdate, int contractlen);

	/**
	 * Create a school in the DB
	 * 
	 * @param name the school name
	 * 
	 * @return true if the school has been well created
	 */
	boolean createSchool(String name);
}
