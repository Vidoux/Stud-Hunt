package persistantdata;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Scanner;

import studhunt.PersistentStudHunt;
import studhunt.StudHunt;

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
		String login = sc.next();
		System.out.print("\nEntrez le mot de passe de votre base de donnée : ");
		String password = sc.next();
		sc.close();
		try {
			this.dataBase = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:XE", login, password);
			System.out.println("Connexion to the database successful");
		} catch (SQLException e) {
			System.err.println("Error in connection to the database");
			e.printStackTrace();
		}
	}
	
	//Fonctions de communication en SQL
}
