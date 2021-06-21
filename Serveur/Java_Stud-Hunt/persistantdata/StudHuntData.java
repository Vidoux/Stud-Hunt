package persistantdata;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Scanner;

import studhunt.ConnexionInfos;
import studhunt.PersistentStudHunt;
import studhunt.StudHunt;
import studhunt.UserTypes;

public class StudHuntData implements PersistentStudHunt {
	Connection dataBase;
	
	static {
		StudHunt.getInstance().setData(new StudHuntData());
	}
	
	private StudHuntData() {
		try {
			Class.forName("oracle.jdbc.OracleDriver");
			System.out.println("OracleDriver class loaded successfully");
		} catch (ClassNotFoundException e) {
			System.err.println("The class was not found");
			e.printStackTrace();
		}
		Scanner sc = new Scanner(System.in);
		System.out.print("Entrez l'identifiant de votre base de donnée : ");
		String login = "SYSTEM";
		System.out.print("\nEntrez le mot de passe de votre base de donnée : ");
		String password = "YES";
		sc.close();
		try {
			this.dataBase = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:XE", login, password);
			System.out.println("Connexion to the database successful");
		} catch (SQLException e) {
			System.err.println("Error in connection to the database");
			e.printStackTrace();
		}
	}
	
	@Override
	public void createUser(String email, String name, String forname, String password, UserTypes userType, List<Object> infos) {
		String sqlStatement = "INSERT INTO APP_USER VALUES ('" + email + "', '" + name + "', '" + forname + "', '" + password + "')";
		PreparedStatement query;
		try {
			query = dataBase.prepareStatement(sqlStatement);
			query.executeQuery();
			switch (userType) {
			case STUDENT :
				query = dataBase.prepareStatement("INSERT INTO STUDENT VALUES ('" + email + "', " + infos.get(0) + ", " + infos.get(1) + ")");
				query.executeQuery();
				break;
			case COMPANY :
				query = dataBase.prepareStatement("INSERT INTO COMPANY VALUES ('" + email + "')");
				query.executeQuery();
				break;	
			}
		} catch (SQLException e) {
			e.printStackTrace();
			System.err.println("Error while creating user");
		}
		System.out.println("User created");
	}
	
	@Override
	public ConnexionInfos getUserConnection(String email, String password) {
		System.out.println("Finding user " + email + " in the database");
		String sqlStatement = "SELECT * FROM APP_USER WHERE email = '" + email + "' AND password = '" + password + "'";
		PreparedStatement query;
		ResultSet response;
		ConnexionInfos connectInfos = new ConnexionInfos(false, null, null, null);
		try {
			synchronized (dataBase) {
				query = dataBase.prepareStatement(sqlStatement);
				response = query.executeQuery();
			}
			System.out.println("Statement passed successfully");
			if (response.next()) {
				System.out.println("Found user " + email + " (Name : " + response.getString("name") + ")");
				connectInfos = new ConnexionInfos(true, getUserType(email), response.getString("name"), response.getString("forname"));
			}
			query.close();
			response.close();
		} catch (SQLException e) {
			System.err.println("An error occured in the following SQL Statement : " + sqlStatement);
		}
		return connectInfos;
	}

	private UserTypes getUserType(String email) {
		for (UserTypes user : UserTypes.values()) {
			String sqlStatement = "SELECT * FROM " + user + " WHERE email = '" + email + "'";
			PreparedStatement query;
			ResultSet response;
			try {
				synchronized (dataBase) {
					query = dataBase.prepareStatement(sqlStatement);
					response = query.executeQuery();
				}
				System.out.println("Statement passed successfully");
				if (response.next()) {
					System.out.println("The user is a " + user);
					return user;
				}
			} catch (SQLException e) {
				continue;
			}
		}
		System.err.println("Could not find the user type");
		return null;
	}
}
