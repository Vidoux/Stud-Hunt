package init;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;

import studhunt.ConnexionInfos;
import studhunt.StudHunt;
import studhunt.UserTypes;

public class app {


	public static void main(String[] args) {
		try {
			new InjectionServlet().init();
		} catch (ServletException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		//Creating company
		StudHunt.getInstance().createUser("support@quadrica.fr", "support", "quadrica", "0QuadriSUP!!!", UserTypes.COMPANY, null);
		//Creating student
		List<Object> infos = new ArrayList<>();
		infos.add(0);
		infos.add(0);
		StudHunt.getInstance().createUser("madrichanderson@gmail.com", "anderson", "madrich", "madrich", UserTypes.STUDENT, infos);
		//Testing if student exist
		ConnexionInfos ci = StudHunt.getInstance().getUserConnection("madrichanderson@gmail.com", "madrich");
		System.out.println(ci.getUser());
		//Adding user profile picture
		StudHunt.getInstance().setProfilePicture("madrichanderson@gmail.com", new File("D:\\Users\\berna\\Google Drive\\Identités\\Anderson Madrich\\Photo Anderson 1.jpg"));
		//Adding user CV
		StudHunt.getInstance().setCV("madrichanderson@gmail.com", new File("D:\\Projets\\Stud-Hunt\\CV.pdf"));
		//Getting user PP
		StudHunt.getInstance().getProfilePicture("madrichanderson@gmail.com");
	}
}
