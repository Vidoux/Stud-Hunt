package services;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import studhunt.StudHunt;
import util.Pair;
import util.StudentInfos;
import util.UserTypes;

/**
 * Servlet d'enregistrement d'un nouvel utilisateur
 */
@WebServlet("/register")
public class RegisterServlet extends HttpServlet {


    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String password = request.getParameter("password");
        String email = request.getParameter("email");
        String prenom = request.getParameter("prenom");
        String nom = request.getParameter("nom");
        String type = request.getParameter("type");

        if(!password.equals("") && !email.equals("") && !nom.equals("") && !type.equals("")){
            try {
                if(type.equals("student")){
                    List<Pair> infos = new ArrayList<>();
        			infos.add(new Pair(StudentInfos.FORNAME, prenom));
        			infos.add(new Pair(StudentInfos.APPRENTICESHIP, 0));
        			infos.add(new Pair(StudentInfos.INTERNSHIP, 0));
                    StudHunt.getInstance().createUser(email, nom, password, UserTypes.STUDENT, null, infos);
                }else{
                    StudHunt.getInstance().createUser(email, nom, password, UserTypes.COMPANY, null, null);
                }
            }catch(Exception e) {
                sendErrorPage(request, response, "error while creating the user in the database, please retry" + e);
            }

        }else{
            sendErrorPage(request, response, "missing information to register the user");
        }

        this.getServletContext().getRequestDispatcher( "/WEB-INF/login.jsp").forward( request, response );
    }

    public void doGet( HttpServletRequest request, HttpServletResponse response )	throws ServletException, IOException {
        this.getServletContext().getRequestDispatcher( "/WEB-INF/register.jsp").forward( request, response );
    }

    private void sendErrorPage(HttpServletRequest request, HttpServletResponse response, String message) throws ServletException, IOException {
        request.setAttribute("backDestination", "./register");
        request.setAttribute("errorMessage", "Echec de l'enregistrement: "+message);
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
