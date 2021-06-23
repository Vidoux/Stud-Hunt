package studhunt;

import java.io.File;
import java.sql.Blob;
import java.util.HashMap;
import java.util.List;

import util.Pair;
import util.References;
import util.UserTypes;

public interface PersistentStudHunt {

	ConnexionInfos getUserConnection(String login, String password);

	boolean createUser(String email, String name, String password, UserTypes userType, HashMap<References, String> references, List<Pair> infos);

	boolean setProfilePicture(String email, File file);

	boolean setCV(String email, File file);

	Blob getCV(String email);

	Blob getProfilePicture(String email);
}
