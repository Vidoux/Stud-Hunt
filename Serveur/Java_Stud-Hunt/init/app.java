package init;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.servlet.ServletException;

import studhunt.ConnexionInfos;
import studhunt.StudHunt;
import util.Pair;
import util.References;
import util.StudentInfos;
import util.UserTypes;

public class app {
	@SuppressWarnings("unused")
	public static void main(String[] args) {
		//Init
		try {
			new InjectionServlet().init();
		} catch (ServletException e) {
			System.err.println("Error in the servlet injection");
			e.printStackTrace();
		}
		StudHunt studHuntClass = StudHunt.getInstance();
		String email;
		String name;
		String forname;
		String password;
		File cv;
		File pic;
		STUDENT : {
			//Infos
			email = "bernard.hugo.thomas@gmail.com";
			name = "Bernard";
			forname = "Hugo";
			password = "0combsHB";
			cv = new File("D:\\Projets\\Stud-Hunt\\CV.pdf");
			pic = new File("D:\\Projets\\Stud-Hunt\\Photo Hugo Pro.jpg");
			HashMap<References, String> references = new HashMap<>();
			references.put(References.INDUSTRY, "Nucléaire");
			references.put(References.SCHOOL, "Paris V");
			List<Pair> infos = new ArrayList<>();
			infos.add(new Pair(StudentInfos.FORNAME, forname));
			infos.add(new Pair(StudentInfos.APPRENTICESHIP, 0));
			infos.add(new Pair(StudentInfos.INTERNSHIP, 0));
			//Creating student
			studHuntClass.createUser(email, name, password, UserTypes.STUDENT, references, infos);
			//Adding student CV
			studHuntClass.setCV(email, cv);
			//Adding student profile picture
			studHuntClass.setProfilePicture(email, pic);
			//Testing student connection
			ConnexionInfos ci = studHuntClass.getUserConnection(email, password);
			System.out.println(ci.getUser());
			//Getting student CV
			studHuntClass.getCV(email);
			//Getting student PP
			studHuntClass.getProfilePicture(email);
		}
		COMPANY : {
			//Infos
			email = "RandomCompanyName@mail.com";
			name = "RandomCompanyName";
			password = "RandomCompanyPassword";
			pic = new File("D:\\Projets\\Stud-Hunt\\Photo Anderson 2.jpg");
			HashMap<References, String> references = new HashMap<>();
			references.put(References.INDUSTRY, "Nucléaire");
			//Creating company
			studHuntClass.createUser(email, name, password, UserTypes.COMPANY, references, null);
			//Adding company profile picture
			studHuntClass.setProfilePicture(email, pic);
			//Testing company connection
			ConnexionInfos ci = studHuntClass.getUserConnection(email, password);
			System.out.println(ci.getUser());
			//Getting company picture
			studHuntClass.getProfilePicture(email);
		}
	}
}
