package studhunt;

import java.io.File;
import java.util.List;

public interface PersistentStudHunt {

	ConnexionInfos getUserConnection(String login, String password);

	void createUser(String email, String name, String forname, String password, UserTypes userType, List<Object> infos);

	void addOrEditProfilePicture(String email, File file);

	void addOrEditCV(String email, File file);
}
