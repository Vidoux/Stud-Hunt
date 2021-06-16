package persistantdata;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
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
	public ConnexionInfos getUserConnection(String login, String password) {
		System.out.println("Finding user " + login + " in the database");
		String sqlStatement = "SELECT * FROM APP_USER WHERE name = '" + login + "' AND password = '" + password + "'";
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
				System.out.println("Found user " + login + " (Name : " + response.getString("name") + ")");
				connectInfos = new ConnexionInfos(true, getUserType(login), response.getString("name"), response.getString("forname"));
			}
			query.close();
			response.close();
		} catch (SQLException e) {
			System.err.println("An error occured in the following SQL Statement : " + sqlStatement);
		}
		return connectInfos;
	}

	private UserTypes getUserType(String login) {
		for (UserTypes user : UserTypes.values()) {
			String sqlStatement = "SELECT * FROM " + user + " WHERE id_User = '" + login + "'";
			PreparedStatement query;
			ResultSet response;
			try {
				synchronized (dataBase) {
					query = dataBase.prepareStatement(sqlStatement);
					response = query.executeQuery();
				}
				System.out.println("Statement passed successfully");
				if (response.next()) {
					System.out.println("The student is a " + user);
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
