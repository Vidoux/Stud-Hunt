package init;

import javax.servlet.ServletException;

import studhunt.ConnexionInfos;
import studhunt.StudHunt;

public class app {


	public static void main(String[] args) {
		try {
			new InjectionServlet().init();
		} catch (ServletException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		ConnexionInfos ci = StudHunt.getInstance().getUserConnection("anderson", "madrich");
		System.out.println(ci.getUser());
	}
}
