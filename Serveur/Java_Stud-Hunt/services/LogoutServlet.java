package services;


import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;


/**
 * Servlet de gestion de la déconnexion des utilisateurs
 */
@WebServlet("/logout")
public class LogoutServlet extends HttpServlet {

    /**
     * Déconnexion puis redirection vers la page d'accueil du site
     * @param request
     * @param response
     * @throws ServletException
     * @throws IOException
     */
    public void doGet( HttpServletRequest request, HttpServletResponse response )	throws ServletException, IOException {
        HttpSession session = request.getSession();
        session.removeAttribute("user");
        this.getServletContext().getRequestDispatcher( "/WEB-INF/main_page.jsp").forward( request, response );
    }


    public void destroy() {
        super.destroy();
    }
}
