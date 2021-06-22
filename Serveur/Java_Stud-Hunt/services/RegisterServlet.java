package services;

import persistantdata.User;
import studhunt.ConnexionInfos;
import studhunt.StudHunt;
import studhunt.UserTypes;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

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
                    List<Object> infos = new ArrayList<>();
                    infos.add(prenom);
                    infos.add(0);
                    infos.add(0);
                    StudHunt.getInstance().createUser(email, nom, password, UserTypes.STUDENT, infos);
                }else{
                    StudHunt.getInstance().createUser(email, nom, password, UserTypes.COMPANY, null);
                }
            }catch(Exception e) {
                sendErrorPage(request, response, "error while creating the user in the database, please retry");
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
