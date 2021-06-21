package init;

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
		List<Object> infos = new ArrayList<>();
		infos.add(0);
		infos.add(0);
		StudHunt.getInstance().createUser("support@quadrica.fr", "support", "quadrica", "0QuadriSUP!!!", UserTypes.COMPANY, null);
		StudHunt.getInstance().createUser("madrichanderson@gmail.com", "anderson", "madrich", "madrich", UserTypes.STUDENT, infos);
		ConnexionInfos ci = StudHunt.getInstance().getUserConnection("madrichanderson@gmail.com", "madrich");
		System.out.println(ci.getUser());
	}
}
