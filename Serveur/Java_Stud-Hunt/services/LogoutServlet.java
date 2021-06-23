package services;


import persistantdata.User;
import studhunt.ConnexionInfos;
import studhunt.StudHunt;
import util.UserTypes;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;


/**
 * Servlet de gestion de l'Authentification par login mot de passe
 */
@WebServlet("/logout")
public class LogoutServlet extends HttpServlet {

    public void doGet( HttpServletRequest request, HttpServletResponse response )	throws ServletException, IOException {
        HttpSession session = request.getSession();
        session.removeAttribute("user");
        this.getServletContext().getRequestDispatcher( "/WEB-INF/main_page.jsp").forward( request, response );
    }

    private void sendErrorPage(HttpServletRequest request, HttpServletResponse response, String message) throws ServletException, IOException {
        request.setAttribute("backDestination", "./login");
        request.setAttribute("errorMessage", "Echec de la connexion, "+message);
        this.getServletContext().getRequestDispatcher("/WEB-INF/error.jsp").forward(request, response);
    }

    private void sendStudentMainPage(){

    }

    private void sendEnterpriseMainPage(){

    }

    public void destroy() {
        super.destroy();
    }
}
