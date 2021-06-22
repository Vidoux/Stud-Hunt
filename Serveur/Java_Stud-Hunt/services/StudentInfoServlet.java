package services;


import persistantdata.User;
import studhunt.ConnexionInfos;
import studhunt.StudHunt;

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
@WebServlet("/student_info")
public class StudentInfoServlet extends HttpServlet {



    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {


    }

    public void doGet( HttpServletRequest request, HttpServletResponse response )	throws ServletException, IOException {
        HttpSession session = request.getSession();

//        session.setAttribute("studentInfos", studentInfos);
        this.getServletContext().getRequestDispatcher( "/WEB-INF/student_info.jsp").forward( request, response );
    }

    private void sendErrorPage(HttpServletRequest request, HttpServletResponse response, String message) throws ServletException, IOException {
        request.setAttribute("backDestination", "./student_info");
        request.setAttribute("errorMessage", "Echec de l'enregistrement des données, "+message);
        this.getServletContext().getRequestDispatcher("/WEB-INF/error.jsp").forward(request, response);
    }


    public void destroy() {
        super.destroy();
    }
}
