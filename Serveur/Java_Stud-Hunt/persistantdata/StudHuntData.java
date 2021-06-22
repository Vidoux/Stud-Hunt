package persistantdata;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.sql.Blob;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.SQLIntegrityConstraintViolationException;
import java.util.List;

import studhunt.ConnexionInfos;
import studhunt.PersistentStudHunt;
import studhunt.StudHunt;
import studhunt.UserTypes;

/**
 * StudHuntData is the interface with the database, the only entity who communicates with it.
 * 
 * @author Hugo BERNARD
 *
 */
public class StudHuntData implements PersistentStudHunt {
	
	/**
	 * Database link
	 */
	Connection dataBase;
	
	/**
	 * Called at loading to setup the connection with database
	 */
	static {
		StudHunt.getInstance().setData(new StudHuntData());
	}
	
	/**
	 * Load OracleDriver to contrôle the database and connect it
	 */
	private StudHuntData() {
		System.out.println("Connecting to the database");
		try {
			Class.forName("oracle.jdbc.OracleDriver");
			System.out.println("OracleDriver class loaded successfully");
		} catch (ClassNotFoundException e) {
			System.err.println("The class was not found");
			e.printStackTrace();
		}
<<<<<<< Updated upstream
		Scanner sc = new Scanner(System.in);
		String login = "SYSTEM";
		System.out.print("\nEntrez le mot de passe de votre base de donnée : ");
		String password = "studhunt";
		sc.close();
=======
		String databasePath = "jdbc:oracle:thin:@localhost:1521:XE";
		String login = "system";
		String password = "studhunt";
>>>>>>> Stashed changes
		try {
			this.dataBase = DriverManager.getConnection(databasePath, login, password);
			System.out.println("Connection to the database successful");
		} catch (SQLException e) {
			System.err.println("Error in connection to the database");
			e.printStackTrace();
		}
	}
	
	/**
	 * Create a user and it's sub-type (STUDENT or COMPANY)
	 * 
	 * @param email the email of the user (Also it's ID in the mean time)
	 * @param name the name of the user
	 * @param forname the forname of the user
	 * @param password the password of the user
	 * @param userType the type of the user (COMPANY or STUDENT 
	 * @param infos the additional informations to create a sub-type (Only for STUDENT)
	 * 
	 * @return true if the user has been created
	 */
	@Override
	public boolean createUser(String email, String name, String forname, String password, UserTypes userType, List<Object> infos) {
		PreparedStatement query;
		String sqlStatement = "INSERT INTO APP_USER VALUES (?, ?, ?, ?)";
		try {
			synchronized (dataBase) {
				query = dataBase.prepareStatement(sqlStatement);
				query.setString(1, email);
				query.setString(2, name);
				query.setString(3, forname);
				query.setString(4, password);
				query.executeQuery();
			}
			query.close();
			System.out.println("Query '" + sqlStatement + "' worked successfully");
			switch (userType) {
			case STUDENT :
				synchronized (dataBase) {
					query = dataBase.prepareStatement("INSERT INTO STUDENT VALUES (?, ?, ?)");
					query.setString(1, email);
					query.setObject(2, infos.get(0));
					query.setObject(3, infos.get(1));
					query.executeQuery();
				}
				query.close();
			case COMPANY :
				synchronized (dataBase) {
					query = dataBase.prepareStatement("INSERT INTO COMPANY VALUES (?)");
					query.setString(1, email);
					query.executeQuery();
				}
			}
			query.close();
			System.out.println("Query '" + sqlStatement + "' worked successfully");
			System.out.println("User " + name + " created");
			return true;
		} catch (SQLIntegrityConstraintViolationException e) {
			System.err.println("User already exist");
			return false;
		} catch (SQLException e) {
			System.err.println("Error while creating user");
			e.printStackTrace();
			return false;
		} 
	}
	
	/**
	 * Get all the informations about a user if the user email and password are existing
	 * 
	 * @param email the email of the user
	 * @param password the password for this user
	 * 
	 * @return connectInfos the informations requierd about this user if the combinaison email/password was correct
	 */
	@Override
	public ConnexionInfos getUserConnection(String email, String password) {
		System.out.println("Finding user " + email + " in the database");
		String sqlStatement = "SELECT * FROM APP_USER WHERE email = ? AND password = ?";
		PreparedStatement query;
		ResultSet response;
		ConnexionInfos connectInfos = new ConnexionInfos(false, null, null, null);
		try {
			synchronized (dataBase) {
				query = dataBase.prepareStatement(sqlStatement);
				query.setString(1, email);
				query.setString(2, password);
				response = query.executeQuery();
			}
			System.out.println("Query '" + sqlStatement + "' worked successfully");
			if (response.next()) {
				System.out.println("Found user " + email + " (Name : " + response.getString("name") + ")");
				connectInfos = new ConnexionInfos(true, getUserType(email), response.getString("name"), response.getString("forname"));
			}
			query.close();
			response.close();
		} catch (SQLException e) {
			System.err.println("An error occured in the SQL statement : '" + sqlStatement + "'");
		}
		return connectInfos;
	}
	
	/**
	 * Get the CV associated with the user if it exist
	 * 
	 * @param email the email of the user
	 * 
	 * @return The CV as a Blob
	 */
	@Override
	public Blob getCV(String email) {
		String tableName = "CV";
		return getFile(tableName, email);
	}
	
	/**
	 * Call setFile() for a CV
	 * 
	 * @param email the email of the selected user
	 * @param cv the PDF that will be linked to this user
	 * 
	 * @return true if the PDF has been added without problem
	 */
	@Override
	public boolean setCV(String email, File cv) {
		String tableName = "CV";
		return setFile(tableName, email, cv);
	}
	
	/**
	 * Get the picture associated with the user if it exist
	 * 
	 * @param email the email of the user
	 * 
	 * @return The picture as a Blob
	 */
	@Override
	public Blob getProfilePicture(String email) {
		String tableName = "PROFILE_PICTURE";
		return getFile(tableName, email);
	}
	
	/**
	 * Call setFile() for a profile picture
	 * 
	 * @param email the email of the selected user
	 * @param profilePicture the picture that will be linked to this user
	 * 
	 * @return true if the picture has been added without problem
	 */
	@Override
	public boolean setProfilePicture(String email, File profilePicture) {
		String tableName = "PROFILE_PICTURE";
		return setFile(tableName, email, profilePicture);
	}

	/**
	 * Get the file from the table asked linked to the email
	 * 
	 * @param tableName the table to serach on
	 * @param email the email linked to the file
	 * 
	 * @return the file serached
	 */
	private Blob getFile(String tableName, String email) {
		Blob file = null;
		PreparedStatement query;
		ResultSet response;
		String sqlStatement = "SELECT linkedFile FROM " + tableName + " WHERE email = ?";
		try {
			synchronized (dataBase) {
					query = dataBase.prepareStatement(sqlStatement);
					query.setString(1, email);
					response = query.executeQuery();
					if (response.next()) {
						file = response.getBlob("linkedFile");
					}
			}
			System.out.println("Query '" + sqlStatement + "' worked successfully");
			query.close();
			response.close();
		} catch (SQLException e) {
			System.err.println("An error occured in the SQL statement : '" + sqlStatement + "'");
			e.printStackTrace();
		}
		return file;
	}
	
	/**
	 * Update a file linked to a user of create it
	 * 
	 * @param tableName the table we look to edit
	 * @param email the email of the selected user
	 * @param file the file that will be added
	 * 
	 * @return true if the file has been added without problem
	 */
	public boolean setFile(String tableName, String email, File file) {
		PreparedStatement query;
		ResultSet response;
		String sqlStatement = "SELECT * FROM " + tableName + " WHERE email = ?";
		try {
			synchronized (dataBase) {
				query = dataBase.prepareStatement(sqlStatement);
				query.setString(1, email);
				response = query.executeQuery();
			}
			System.out.println("Query '" + sqlStatement + "' worked successfully");
			if (response.next()) {
				sqlStatement = "UPDATE " + tableName + " SET imageBlob = ? WHERE email = ?";
			} else {
				sqlStatement = "INSERT INTO " + tableName + " VALUES(?, ?)";
			}
			byte[] fileInBytes = Files.readAllBytes(file.toPath());
			synchronized (dataBase) {
				query = dataBase.prepareStatement(sqlStatement);
				query.setString(1, email);
				query.setBytes(2, fileInBytes);
				response = query.executeQuery();
			}
			query.close();
			response.close();
			System.out.println("Query '" + sqlStatement + "' worked successfully");
			return true;
		} catch (SQLException e) {
			System.err.println("An error occured in the SQL statement : '" + sqlStatement + "'");
			e.printStackTrace();
			return false;
		} catch (IOException e) {
			System.err.println("An error occured while reading the file");
			e.printStackTrace();
			return false;
		}
	}

	/**
	 * Get the user type of a given email
	 * 
	 * @param email the email to serch for
	 * 
	 * @return the user type
	 */
	private UserTypes getUserType(String email) {
		PreparedStatement query = null;
		ResultSet response = null;
		for (UserTypes user : UserTypes.values()) {
			String sqlStatement = "SELECT * FROM " + user + " WHERE email = '" + email + "'";
			try {
				synchronized (dataBase) {
					query = dataBase.prepareStatement(sqlStatement);
					response = query.executeQuery();
				}
				System.out.println("Query '" + sqlStatement + "' worked successfully");
				if (response.next()) {
					System.out.println("The user is a " + user);
					query.close();
					response.close();
					return user;
				}
			} catch (SQLException e) {
				continue;
			}
		}
		System.err.println("Could not find the user type");
		try {
			query.close();
			response.close();
		} catch (SQLException e) {
			System.err.println("Error while closing SQL communication");
			e.printStackTrace();
		}
		return null;
	}
}
