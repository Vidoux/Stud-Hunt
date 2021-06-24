package persistantdata;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.SQLIntegrityConstraintViolationException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map.Entry;

import studhunt.PersistentStudHunt;
import studhunt.StudHunt;
import util.ConnectionInfos;
import util.Pair;
import util.References;
import util.StudentInfos;
import util.User;
import util.UserTypes;
import util.user.Company;
import util.user.Industry;
import util.user.JobOffer;
import util.user.Project;
import util.user.School;
import util.user.Student;

/**
 * StudHuntData is the interface with the database, the only entity who
 * communicates with it.
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
		String databasePath = "jdbc:oracle:thin:@localhost:1521:XE";
		String login = "SYSTEM";
		String password = "studhunt";
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
	 * @param email    the email of the user (Also it's ID in the mean time)
	 * @param name     the name of the user
	 * @param forname  the forname of the user
	 * @param password the password of the user
	 * @param userType the type of the user (COMPANY or STUDENT
	 * @param infos    the additional informations to create a sub-type (Only for
	 *                 STUDENT)
	 * 
	 * @return true if the user has been created
	 */
	@Override
	public boolean createUser(String email, String name, String password, UserTypes userType,
			HashMap<References, String> references, List<Pair> infos) {
		String sqlStatement = null;
		String forname = null;
		int apprenticeship = 0;
		int internship = 0;

		sqlStatement = "INSERT INTO APP_USER VALUES (?, ?, ?)";
		try {
			executeSQL(sqlStatement, new Object[] { email, name, password});
			try {
				switch (userType) {
				case STUDENT:
					for (Pair info : infos) {
						switch ((StudentInfos) info.getFirst()) {
						case APPRENTICESHIP:
							apprenticeship = (int) info.getSecond();
							break;
						case FORNAME:
							forname = (String) info.getSecond();
							break;
						case INTERNSHIP:
							internship = (int) info.getSecond();
							break;
						}
					}
					sqlStatement = "INSERT INTO STUDENT VALUES (?, ?, ?, ?)";
					executeSQL(sqlStatement, new Object[] { email, forname, apprenticeship, internship });
					break;
				case COMPANY:
					sqlStatement = "INSERT INTO COMPANY VALUES (?)";
					executeSQL(sqlStatement, new Object[] { email });
					break;
				}
			} catch (SQLException subTypeException) {
				System.err.println(formatSQLError("creating the sub-type user", sqlStatement));
				subTypeException.printStackTrace();
				return false;
			}
		} catch (SQLIntegrityConstraintViolationException userException) {
			System.err.println("User already existing");
			return false;
		} catch (SQLException userException) {
			System.err.println(formatSQLError("creating the user", sqlStatement));
			userException.printStackTrace();
			return false;
		}
		return addReferences(references, email, userType);
	}

	public boolean addReferences(HashMap<References, String> references, String email, UserTypes userType) {
		try {
			for (Entry<References, String> reference : references.entrySet()) {
					String sqlStatement = null;
					ResultSet response = null;
					int referenceID = 0;
					String referenceName = null;
					String tableName = null;
		
					sqlStatement = "SELECT * FROM " + reference.getKey().toString() + " WHERE "
							+ reference.getKey().toString().toLowerCase() + "Name = ?";
					try {
						response = executeSQL(sqlStatement, new Object[] { reference.getValue() });
						synchronized (dataBase) {
							switch (reference.getKey()) {
							case SCHOOL:
								if (!response.next()) {
									createSchool(reference.getValue());
								}
								referenceName = "School";
								break;
							case INDUSTRY:
								if (!response.next()) {
									createIndustry(reference.getValue());
								}
								referenceName = "Industry";
								break;
							}
							sqlStatement = "SELECT id_" + referenceName + " FROM " + referenceName.toUpperCase() + " WHERE " + referenceName + "Name = ?";
							response = executeSQL(sqlStatement, new Object[] {reference.getValue()});
							referenceID = response.next() ? response.getInt("id_" + referenceName) : 1;
						}
					} catch (SQLException checkReferencesException) {
						System.err.println(formatSQLError("getting references existing informations", sqlStatement));
						checkReferencesException.printStackTrace();
						return false;
					}
					switch (userType) {
					case STUDENT:
						switch (reference.getKey()) {
						case SCHOOL:
							tableName = "is_part_of";
							break;
						case INDUSTRY:
							tableName = "concern";
							break;
						}
						break;
					case COMPANY:
						tableName = "refer_to";
						break;
					}
					sqlStatement = "INSERT INTO " + tableName + " VALUES(?, ?)";
					try {
						executeSQL(sqlStatement, new Object[] {referenceID, email});
					} catch (SQLException addingReferenceException) {
						System.err.println(formatSQLError("adding association between user and reference", sqlStatement));
						addingReferenceException.printStackTrace();
						return false;
					}
				}
		} catch (NullPointerException e) {
			
		}
		return true;
	}

	/**
	 * Get all the informations about a user if the user email and password are
	 * existing
	 * 
	 * @param email    the email of the user
	 * @param password the password for this user
	 * 
	 * @return connectInfos the informations requierd about this user if the
	 *         combinaison email/password was correct
	 */
	@Override
	public ConnectionInfos getUserConnection(String email, String password) {
		String sqlStatement = null;
		ResultSet response = null;
		UserTypes user = null;
		ConnectionInfos connectInfos = new ConnectionInfos(false, null, null, null);

		sqlStatement = "SELECT * FROM APP_USER WHERE email = ? AND password = ?";
		try {
			response = executeSQL(sqlStatement, new Object[] { email, password });
			if (response.next()) {
				user = getUserType(email);
				if (user.equals(UserTypes.STUDENT)) {
					connectInfos = new ConnectionInfos(true, user, response.getString("name"), getUserForname(email));
				} else {
					connectInfos = new ConnectionInfos(true, user, response.getString("name"));
				}
			}
		} catch (SQLException userNotFoundException) {
			System.err.println("The combinaison email/password was not found");
		}
		return connectInfos;
	}
	
	public User getUser(String email, String password) {
		User user = null;
		UserTypes userType = null;
		String sqlStatement = null;
		ResultSet response = null;
		String name = null;
		String forname = null;
		int apprenticeship = 0;
		int internship = 0;
		List<Project> projects = null;
		List<Industry> industries = null;
		List<School> schools = null;
		List<JobOffer> jobOffers = null;
		
		try {
			//Getting user basic informations
			userType = getUserType(email);
			sqlStatement = "SELECT name FROM APP_USER WHERE email = ?";
				response = executeSQL(sqlStatement, new Object[] {email});
			name = response.next() ? response.getString("name") : null ;
			switch(userType) {
				case STUDENT :
					//Getting student sub-type informations
					sqlStatement = "SELECT forname, apprenticeship, internship FROM STUDENT WHERE email = ?";
					response = executeSQL(sqlStatement, new Object[] {email});
					if (response.next()) {
						forname = response.getString("forname");
						apprenticeship = response.getInt("apprenticeship");
						internship = response.getInt("internship");
					}
					//Getting student projects informations
					projects = new ArrayList<>();
					sqlStatement = "SELECT projectName, realisation_year FROM PROJECT WHERE email = ?";
					response = executeSQL(sqlStatement, new Object[] {email});
					while (response.next()) {
						projects.add(new Project(response.getString("projectName"), response.getInt("realisation_year")));
					}
					//Getting student indstry informations
					industries = new ArrayList<>();
					sqlStatement = "SELECT id_Indsutry FROM concern WHERE email = ?";
					response = executeSQL(sqlStatement, new Object[] {email});
					while (response.next()) {
						sqlStatement = "SELECT industryName FROM INDUSTRY WHERE id_Industry = ?";
						response = executeSQL(sqlStatement, new Object[] {response.getInt("id_Industry")});
						if (response.next()) { industries.add(new Industry(response.getString("industryName"))); }
					}
					//Getting student school informations
					schools = new ArrayList<>();
					sqlStatement = "SELECT id_School FROM is_part_of WHERE email = ?";
					response = executeSQL(sqlStatement, new Object[] {email});
					while (response.next()) {
						sqlStatement = "SELECT schoolName FROM SCHOOL WHERE id_School = ?";
						response = executeSQL(sqlStatement, new Object[] {response.getInt("id_School")});
						if (response.next()) { schools.add(new School(response.getString("schoolName"))); }
					}
					user = new Student(userType, email, name, forname, apprenticeship, internship, password, projects, industries, schools);
					break;
				case COMPANY :
					//Getting company indstry informations
					industries = new ArrayList<>();
					sqlStatement = "SELECT id_Indsutry FROM refer_to WHERE email = ?";
					response = executeSQL(sqlStatement, new Object[] {email});
					while (response.next()) {
						sqlStatement = "SELECT industryName FROM INDUSTRY WHERE id_Industry = ?";
						response = executeSQL(sqlStatement, new Object[] {response.getInt("id_Industry")});
						if (response.next()) { industries.add(new Industry(response.getString("industryName"))); }
					}
					//Getting company job offer informations
					jobOffers = new ArrayList<>();
					sqlStatement = "SELECT offerType FROM JOB_OFFER WHERE email = ?";
					response = executeSQL(sqlStatement, new Object[] {email});
					while (response.next()) {
						jobOffers.add(new JobOffer(response.getInt("offerType")));
					}
					user = new Company(userType, email, name, password, jobOffers, industries);
					break;
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return user;
	}

	/**
	 * Get the CV associated with the user if it exist
	 * 
	 * @param email the email of the user
	 * 
	 * @return The CV as a Blob
	 */
	@Override
	public byte[] getCV(String email) {
		String tableName = null;

		tableName = "CV";
		return getFile(tableName, email);
	}

	/**
	 * Call setFile() for a CV
	 * 
	 * @param email the email of the selected user
	 * @param cv    the PDF that will be linked to this user
	 * 
	 * @return true if the PDF has been added without problem
	 */
	@Override
	public boolean setCV(String email, byte[] cv) {
		String tableName = null;

		tableName = "CV";
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
	public byte[] getProfilePicture(String email) {
		String tableName = null;

		tableName = "PROFILE_PICTURE";
		return getFile(tableName, email);
	}

	/**
	 * Call setFile() for a profile picture
	 * 
	 * @param email          the email of the selected user
	 * @param profilePicture the picture that will be linked to this user
	 * 
	 * @return true if the picture has been added without problem
	 */
	@Override
	public boolean setProfilePicture(String email, byte[] profilePicture) {
		String tableName = null;

		tableName = "PROFILE_PICTURE";
		return setFile(tableName, email, profilePicture);
	}

	/**
	 * Get the file from the table asked linked to the email
	 * 
	 * @param tableName the table to serach on
	 * @param email     the email linked to the file
	 * 
	 * @return the file serached
	 */
	private byte[] getFile(String tableName, String email) {
		String sqlStatement = null;
		ResultSet response = null;
		byte[] file = null;

		sqlStatement = "SELECT linkedFile FROM " + tableName + " WHERE email = ?";
		try {
			response = executeSQL(sqlStatement, new Object[] { email });
			file = response.next() ? response.getBytes("linkedFile") : null;
		} catch (SQLException fileNotFondException) {
			System.err.println(formatSQLError("looking for the file", sqlStatement));
			fileNotFondException.printStackTrace();
		}
		return file;
	}

	/**
	 * Update a file linked to a user of create it
	 * 
	 * @param tableName the table we look to edit
	 * @param email     the email of the selected user
	 * @param file      the file that will be added
	 * 
	 * @return true if the file has been added without problem
	 */
	private boolean setFile(String tableName, String email, byte[] file) {
		String sqlStatement = null;
		ResultSet response = null;

		sqlStatement = "SELECT * FROM " + tableName + " WHERE email = ?";
		try {
			response = executeSQL(sqlStatement, new Object[] { email });
			if (response.next()) {
				sqlStatement = "DELETE FROM " + tableName + " WHERE email = ?";
				try {
					executeSQL(sqlStatement, new Object[] { email });
				} catch (SQLException deleteFileException) {
					System.err.println(formatSQLError("deleting the file", sqlStatement));
					deleteFileException.printStackTrace();
					return false;
				}

			}
		} catch (SQLException fileNotFoundException) {
			System.err.println(formatSQLError("looking for the file", sqlStatement));
			fileNotFoundException.printStackTrace();
			return false;
		}
		sqlStatement = "INSERT INTO " + tableName + " VALUES(?, ?)";
		try {
			executeSQL(sqlStatement, new Object[] { email, file });
		} catch (SQLException addingFileException) {
			System.err.println(formatSQLError("adding a file", sqlStatement));
			addingFileException.printStackTrace();
			return false;
		}
		return true;
	}

	/**
	 * Get the user type of a given email
	 * 
	 * @param email the email to serch for
	 * 
	 * @return the user type
	 */
	@Override
	public UserTypes getUserType(String email) {
		String sqlStatement = null;
		ResultSet response = null;

		for (UserTypes user : UserTypes.values()) {
			sqlStatement = "SELECT * FROM " + user + " WHERE email = ?";
			try {
				response = executeSQL(sqlStatement, new Object[] { email });
				if (response.next()) {
					return user;
				}
			} catch (SQLException userTypeException) {
				System.err.println(formatSQLError("getting the user type", sqlStatement));
				userTypeException.printStackTrace();
			}
		}
		return null;
	}

	@Override
	public boolean createProject(String email, String projectName, int date) {
		String sqlStatement = null;

		sqlStatement = "INSERT INTO PROJECT VALUES(?, ?)";
		try {
			executeSQL(sqlStatement, new Object[] {getSequenceValue("ID_PROJECT_SEQ.NEXTVAL") + 1, projectName, date, email });
		} catch (SQLException addingProjectException) {
			System.err.println(formatSQLError("adding a new project", sqlStatement));
			addingProjectException.printStackTrace();
			return false;
		}
		return true;
	}

	@Override
	public boolean createJobOffer(String email, int offertype) {
		String sqlStatement = null;

		sqlStatement = "INSERT INTO JOB_OFFER VALUES(?, ?, ?)";
		try {
			executeSQL(sqlStatement, new Object[] {getSequenceValue("ID_JOBOFFER_SEQ.NEXTVAL") + 1, offertype, email });
		} catch (SQLException addingJobOfferException) {
			System.err.println(formatSQLError("adding a new job offer", sqlStatement));
			addingJobOfferException.printStackTrace();
			return false;
		}
		return true;
	}

	@Override
	public boolean createIndustry(String name) {
		String sqlStatement = null;

		sqlStatement = "INSERT INTO INDUSTRY VALUES (?, ?)";
		try {
			executeSQL(sqlStatement, new Object[] {getSequenceValue("ID_INDUSTRY_SEQ.NEXTVAL") + 1, name });
		} catch (SQLException addingIndustryException) {
			System.err.println(formatSQLError("adding a new industry", sqlStatement));
			addingIndustryException.printStackTrace();
			return false;
		}
		return true;
	}

	@Override
	public boolean createSchool(String name) {
		String sqlStatement = null;

		sqlStatement = "INSERT INTO SCHOOL VALUES (?, ?)";
		try {
			executeSQL(sqlStatement, new Object[] {getSequenceValue("ID_SCHOOL_SEQ.NEXTVAL") + 1, name });
		} catch (SQLException addingSchoolException) {
			System.err.println(formatSQLError("adding a new school", sqlStatement));
			addingSchoolException.printStackTrace();
			return false;
		}
		return true;
	}

	/**
	 * Get an user forname from the email
	 * 
	 * @param email the email used to find forname
	 * 
	 * @return the forname
	 */
	private String getUserForname(String email) {
		String sqlStatement = null;
		ResultSet response = null;
		String forname = null;

		sqlStatement = "SELECT forname FROM STUDENT WHERE email = ?";
		try {
			response = executeSQL(sqlStatement, new Object[] { email });
			forname = response.next() ? response.getString("forname") : null;
		} catch (SQLException userFornameException) {
			System.err.println(formatSQLError("getting the user forname", sqlStatement));
			userFornameException.printStackTrace();
		}
		return forname;
	}
	
	private int getSequenceValue(String sequenceName) {
		String sqlStatement = null;
		int sequenceValue = 1;
		
		sqlStatement = "SELECT last_number FROM all_sequences WHERE sequence_name = ?";
		try {
			executeSQL(sqlStatement, new Object[] {sequenceName});
		} catch (SQLException sequenceValueException) {
			System.err.println(formatSQLError("getting the sequence value", sqlStatement));
			sequenceValueException.printStackTrace();
		}
		return sequenceValue;
	}

	private ResultSet executeSQL(String sqlStatement, Object[] statementValues) throws SQLException {
		PreparedStatement query = null;
		ResultSet response = null;
		int index = 0;

		synchronized (dataBase) {
			query = dataBase.prepareStatement(sqlStatement);
			for (index = 1; index <= statementValues.length; index++) {
				query.setObject(index, statementValues[index-1]);
			}
			response = query.executeQuery();
		}
		return response;
	}

	private String formatSQLError(String message, String sqlStatement) {
		return "SQL error happened while " + message + "\n" + "SQL statement : " + sqlStatement;
	}
}
