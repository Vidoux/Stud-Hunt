package persistantdata;

import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.SQLIntegrityConstraintViolationException;
import java.util.ArrayList;
import java.util.List;

import studhunt.PersistentStudHunt;
import studhunt.StudHunt;
import util.User;
import util.UserTypes;
import util.user.usercontent.JobOffer;
import util.user.usercontent.Project;
import util.user.userrefernces.School;
import util.user.usertypes.Company;
import util.user.usertypes.Student;

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
	 * Create an user in the DB
	 * 
	 * @param user the user we'll add to DB
	 * 
	 * @return true if the user has been created well
	 */
	@Override
	public boolean createUser(User user) {
		String sqlStatement = null;

		sqlStatement = "INSERT INTO APP_USER "
					 + "VALUES (?, ?, ?)";
		try {
			executeSQL(sqlStatement, new Object[] {user.getEmail(), user.getName(), user.getPassword()});
		} catch (SQLIntegrityConstraintViolationException userException) {
			System.err.println("User already existing");
			return false;
		} catch (SQLException e) {
			System.err.println(formatSQLError("creating the user", sqlStatement));
			e.printStackTrace();
			return false;
		}
		switch(user.getUserType()) {
		case STUDENT :
			return createStudent((Student) user);
		case COMPANY :
			return createCompany((Company) user);
		}
		return false;
	}
	
	/**
	 * Create a student in the DB
	 * 
	 * @param student the student to add to DB
	 * 
	 * @return true if the student has been well created
	 */
	private boolean createStudent(Student student) {
		String sqlStatement = null;
		
		sqlStatement = "INSERT INTO STUDENT (email, forname, apprenticeship, internship) "
					 + "VALUES (?, ?, ?, ?)";
		try {
			executeSQL(sqlStatement, new Object[] {student.getEmail(), student.getForname(), student.getApprenticeship(), student.getInternship()});
			return true;
		} catch (SQLIntegrityConstraintViolationException userException) {
			System.err.println("Student already existing");
			return false;
		} catch (SQLException e) {
			System.err.println(formatSQLError("creating the student", sqlStatement));
			e.printStackTrace();
			return false;
		}
	}

	/**
	 * Create a company in the DB
	 * 
	 * @param company the company to add to DB
	 * 
	 * @return true if the company has been well created
	 */
	private boolean createCompany(Company company) {
		String sqlStatement = null;
	
		sqlStatement = "INSERT INTO COMPANY "
					 + "VALUES (?)";
		try {
			executeSQL(sqlStatement, new Object[] {company.getEmail()});
			return true;
		} catch (SQLIntegrityConstraintViolationException userException) {
			System.err.println("Company already existing");
			return false;
		} catch (SQLException e) {
			System.err.println(formatSQLError("creating the company", sqlStatement));
			e.printStackTrace();
			return false;
		}
	}

	/**
	 * Updates a user in the DB
	 * 
	 * @param user the user to update in the DB
	 * 
	 * @return true if the user has been well updated
	 */
	@Override
	public boolean updateUser(User user) {
		String sqlStatement = null;
		UserTypes userType = null;
		
		userType = getUserType(user.getEmail());
		sqlStatement = "UPDATE APP_USER "
					 + "SET name = ?, bio = ?"
					 + "WHERE email = ?";
		try {
			executeSQL(sqlStatement, new Object[] {user.getName(), user.getBio(), user.getEmail()});
			if (userType.equals(UserTypes.STUDENT)) {
				return updateStudent((Student) user);
			}
			return true;
		} catch (SQLException e) {
			System.err.println(formatSQLError("updating the user", sqlStatement));
			e.printStackTrace();
			return false;
		}
	}

	/**
	 * Updates a student in the DB
	 * 
	 * @param student the student to update in the DB
	 * 
	 * @return true if the student has been well updated
	 */
	private boolean updateStudent(Student student) {
		String sqlStatement = null;
		
		sqlStatement = "UPDATE STUDENT "
			 	     + "SET forname = ?, apprenticeship = ?, internship = ?, levelstudy = ?, industry = ?, startingdate = ?, contractlen = ?, diploma = ? "
			 	     + "WHERE email = ?";
		try {
			executeSQL(sqlStatement, new Object[] { student.getEmail(), student.getForname(), student.getApprenticeship(), student.getInternship(), student.getLevelstudy(), student.getIndustry(), student.getStartingdate(), student.getContractlen(), student.getDiploma()});
			return true;
		} catch (SQLException e) {
			System.err.println(formatSQLError("updating the student", sqlStatement));
			e.printStackTrace();
			return false;
		}
	}
	
	/**
	 * Retrive a user with te combinaison email/password given
	 * 
	 * @param email the email of the searched user
	 * @param password the password of the searched user
	 * 
	 * @return the user, or null if not found
	 */
	@Override
	public User getUser(String email, String password) {
		User user = null;
		String sqlStatement = null;
		ResultSet response = null;
		
		UserTypes userType = null;
		String name = null;
		String bio = null;
		String forname = null;
		int apprenticeship = 0;
		int internship = 0;
		String levelstudy = null;
		String industry = null;
		Date startingdate;
		int contractlen = 0;
		String diploma = null;
		List<Project> projects = null;
		List<School> schools = null;
		List<JobOffer> jobOffers = null;
		
		try {
			//Getting user basic informations
			userType = getUserType(email);
			sqlStatement = "SELECT name, bio "
						 + "FROM APP_USER "
						 + "WHERE email = ?";
			response = executeSQL(sqlStatement, new Object[] {email});
			if(response.next()) {
				name = response.getString("name");
				bio = response.getString("bio");
			}
			switch(userType) {
				case STUDENT :
					//Getting student sub-type informations
					sqlStatement = "SELECT forname, apprenticeship, internship, levelstudy, industry, startingdate, contractlen, diploma "
								 + "FROM STUDENT "
								 + "WHERE email = ?";
					response = executeSQL(sqlStatement, new Object[] {email});
					if (response.next()) {
						forname = response.getString("forname");
						apprenticeship = response.getInt("apprenticeship");
						internship = response.getInt("internship");
						levelstudy = response.getString("levelstudy");
						industry = response.getString("industry");
						startingdate = response.getDate("startingdate");
						contractlen = response.getInt("contractlen");
						diploma = response.getString("diploma");
					}
					//Getting student projects informations
					projects = new ArrayList<>();
					sqlStatement = "SELECT projectName, projectBio, realisation_year FROM PROJECT WHERE email = ?";
					response = executeSQL(sqlStatement, new Object[] {email});
					while (response.next()) {
						projects.add(new Project(response.getString("projectName"), response.getString("projectBio"), response.getInt("realisation_year")));
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
					user = new Student(email, name, forname, apprenticeship, internship, password, bio, industry, projects, schools);
					break;
				case COMPANY :
					//Getting company job offer informations
					jobOffers = new ArrayList<>();
					sqlStatement = "SELECT offerType FROM JOB_OFFER WHERE email = ?";
					response = executeSQL(sqlStatement, new Object[] {email});
					while (response.next()) {
						jobOffers.add(new JobOffer(response.getInt("offerType")));
					}
					user = new Company(email, name, password, bio, jobOffers);
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

	/**
	 * Create a project in the DB
	 * 
	 * @param email the user's email
	 * @param projectName the project name
	 * @param projectBio the project description
	 * @param date the project date
	 * 
	 * @return true if the project has been well created
	 */
	@Override
	public boolean createProject(String email, String projectName, String projectBio, int date) {
		String sqlStatement = null;

		sqlStatement = "INSERT INTO PROJECT VALUES(?, ?, ?, ?, ?)";
		try {
			executeSQL(sqlStatement, new Object[] {getSequenceValue("ID_PROJECT_SEQ") + 1, projectName, projectBio, date, email });
		} catch (SQLException addingProjectException) {
			System.err.println(formatSQLError("adding a new project", sqlStatement));
			addingProjectException.printStackTrace();
			return false;
		}
		return true;
	}

	/**
	 * Create a job offer in the DB
	 * 
	 * @param offertype the type of offer
	 * @param email the email of the company
	 * @param apprenticeship if it's in apprenticeship
	 * @param internship if it's in internship
	 * @param levelstudy the level of study required
	 * @param industry the industry domain
	 * @param startingdate the starting date of the contract
	 * @param contractlen the durantion of the contract
	 * 
	 * @return true if the job offer has been well created
	 */
	@Override
	public boolean createJobOffer(int offertype, String email, int apprenticeship, int internship, String levelstudy, String industry, Date startingdate, int contractlen) {
		String sqlStatement = null;

		sqlStatement = "INSERT INTO JOB_OFFER VALUES(?, ?, ?, ?, ?, ?, ?, ?, ?)";
		try {
			executeSQL(sqlStatement, new Object[] {getSequenceValue("ID_JOBOFFER_SEQ") + 1, offertype, email , apprenticeship, internship, levelstudy, industry, startingdate, contractlen});
		} catch (SQLException addingJobOfferException) {
			System.err.println(formatSQLError("adding a new job offer", sqlStatement));
			addingJobOfferException.printStackTrace();
			return false;
		}
		return true;
	}

	/**
	 * Create a school in the DB
	 * 
	 * @param name the school name
	 * 
	 * @return true if the school has been well created
	 */
	@Override
	public boolean createSchool(String name) {
		String sqlStatement = null;

		sqlStatement = "INSERT INTO SCHOOL VALUES (?, ?)";
		try {
			executeSQL(sqlStatement, new Object[] {getSequenceValue("ID_SCHOOL_SEQ") + 1, name });
		} catch (SQLException addingSchoolException) {
			System.err.println(formatSQLError("adding a new school", sqlStatement));
			addingSchoolException.printStackTrace();
			return false;
		}
		return true;
	}
	
	/**
	 * get the current value of a sequence in the DB
	 * 
	 * @param sequenceName the name of the sequence wanted
	 * 
	 * @return the sequence actual value
	 */
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

	/**
	 * Execute any SQL request
	 * 
	 * @param sqlStatement the request
	 * @param statementValues the list of values for the request
	 * 
	 * @return the response
	 * 
	 * @throws SQLException general SQL exception handled further
	 */
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

	/**
	 * Format SQL error messages with the last command in error
	 * 
	 * @param message the message of the intended operation
	 * @param sqlStatement the statement that failed
	 * 
	 * @return the formated string
	 */
	private String formatSQLError(String message, String sqlStatement) {
		return "SQL error happened while " + message + "\n" + "SQL statement : " + sqlStatement;
	}
}
