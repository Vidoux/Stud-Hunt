package services;


import studhunt.StudHunt;
import util.User;
import util.usercontent.Project;
import util.userrefernces.School;
import util.usertypes.Student;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.sql.Date;
import java.util.List;


/**
 * Servlet de gestion de l'enregistrement et de la modifications des informations
 * concernants les étudiants
 */
@WebServlet("/student_info")
public class StudentInfoServlet extends HttpServlet {


    /**
     * Traitement de l'envoie de données pour la mise à jour des informations de l'étudiant
     * @param request
     * @param response
     * @throws ServletException
     * @throws IOException
     */
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        if((session.getAttribute("user") == null)){
            sendErrorPage(request, response, "Vous devez vous authentifier");
        }



    }

    /**
     * Retourne la servlet de gestion des informations de l'étudiant
     * @param request
     * @param response
     * @throws ServletException
     * @throws IOException
     */
    public void doGet( HttpServletRequest request, HttpServletResponse response )	throws ServletException, IOException {
        HttpSession session = request.getSession();
        if(session.getAttribute("user") == null){
            this.getServletContext().getRequestDispatcher("/WEB-INF/login.jsp").forward(request, response);
        }
        String tanguy = "Wesh";
        Student user = (Student) session.getAttribute("user");

        List<Project> projects = user.getProjects();
        Project proj =null;
        if(projects.size()>0){
            proj = projects.get(0);
        }
        session.setAttribute("proj", proj );

        List<School> schools = user.getSchools();
        School school1 = null;
        String schoolname ="";
        if(schools.size()>0){
            school1 = schools.get(0);
            schoolname = school1.getSchoolName();
        }
        session.setAttribute("school", schoolname);


        Date startingDate = user.getStartingdate();
        String bio = user.getBio();
        String name = user.getName();
        this.getServletContext().getRequestDispatcher( "/WEB-INF/student_info.jsp").forward( request, response );
    }

    /**
     * Afficher une page d'erreur avec un message personnalisé
     * @param request
     * @param response
     * @param message message à afficher sur la mage d'erreur
     * @throws ServletException
     * @throws IOException
     */
    private void sendErrorPage(HttpServletRequest request, HttpServletResponse response, String message) throws ServletException, IOException {
        request.setAttribute("backDestination", "./student_info");
        request.setAttribute("errorMessage", "Echec de l'enregistrement des données, "+message);
        this.getServletContext().getRequestDispatcher("/WEB-INF/error.jsp").forward(request, response);
    }

    public void destroy() {
        super.destroy();
    }
}
