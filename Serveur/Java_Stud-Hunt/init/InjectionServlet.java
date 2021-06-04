package init;

import javax.servlet.ServletException;
import javax.servlet.http.*;
import java.io.IOException;


public class InjectionServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;

	public void init() throws ServletException {

		try {
			//Injection de la dépendance vers StudHuntData
			Class.forName("persistantdata.StudHuntData");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		System.out.println("%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%");
	}

	public void doGet( HttpServletRequest request, HttpServletResponse response )	throws ServletException, IOException {
		this.getServletContext().getRequestDispatcher( "/WEB-INF/login.jsp").forward( request, response );
	}

}

