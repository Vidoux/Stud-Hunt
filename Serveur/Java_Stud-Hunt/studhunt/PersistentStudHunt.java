package studhunt;

import java.util.HashMap;
import java.util.List;

import util.Pair;
import util.References;
import util.UserTypes;

public interface PersistentStudHunt {

	ConnexionInfos getUserConnection(String login, String password);

	boolean createUser(String email, String name, String password, UserTypes userType, HashMap<References, String> references, List<Pair> infos);

	boolean setProfilePicture(String email, byte[] file);

	boolean setCV(String email, byte[] file);

	byte[] getCV(String email);

	byte[] getProfilePicture(String email);

	/**
	 * Get the user type of a given email
	 * 
	 * @param email the email to serch for
	 * 
	 * @return the user type
	 */
	UserTypes getUserType(String email);

	boolean createSchool(String name);

	boolean createIndustry(String name);

	boolean createJobOffer(String email, int offertype);

	boolean createProject(String email, String projectName, int date);
}
