package studhunt;

import java.io.File;
import java.sql.Blob;
import java.util.List;

public interface PersistentStudHunt {

	ConnexionInfos getUserConnection(String login, String password);

	boolean createUser(String email, String name, String password, UserTypes userType, List<Object> infos);

	boolean setProfilePicture(String email, File file);

	boolean setCV(String email, File file);

	Blob getCV(String email);

	Blob getProfilePicture(String email);
}
