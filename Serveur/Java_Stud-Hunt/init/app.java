package init;

import javax.servlet.ServletException;

import studhunt.StudHunt;

public class app {
	@SuppressWarnings("unused")
	public static void main(String[] args) {
		// Init
		try {
			new InjectionServlet().init();
		} catch (ServletException e) {
			System.err.println("Error in the servlet injection");
			e.printStackTrace();
		}
		StudHunt studHuntClass = StudHunt.getInstance();
		String email = null;
		String name = null;
		String forname = null;
		String password = null;
		byte[] cv = null;
		byte[] pic = null;
		STUDENT: {
			// Infos

		}
		COMPANY: {
			// Infos

		}
	}
}
