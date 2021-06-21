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

/**
 * Servlet d'enregistrement d'un nouvel utilisateur
 */
@WebServlet("/register")
public class RegisterServlet extends HttpServlet {


    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String password = request.getParameter("password");
        String login = request.getParameter("login");
        System.out.println(login);
        System.out.println(password);
        ConnexionInfos loginStatus = StudHunt.getInstance().getUserConnection(login, password);

        System.out.println(loginStatus.toString());

        String message;
        User user = new User(login, password, loginStatus.getUserType());


        if(!loginStatus.isValidPassword()){
            message = "Utilisateur non reconnu...";
            sendErrorPage(request,response, message);
        }



        //Création de la variable session
        HttpSession session = request.getSession();
        session.setAttribute("user", user);

        if(user.getUserType().compareTo(UserTypes.COMPANY) == 0){
            sendEnterpriseMainPage();
        }

        if(user.getUserType().compareTo(UserTypes.STUDENT) == 0){
            sendStudentMainPage();
        }

    }

    public void doGet( HttpServletRequest request, HttpServletResponse response )	throws ServletException, IOException {
        this.getServletContext().getRequestDispatcher( "/WEB-INF/register.jsp").forward( request, response );
    }

    private void sendErrorPage(HttpServletRequest request, HttpServletResponse response, String message) throws ServletException, IOException {
        request.setAttribute("backDestination", "./register");
        request.setAttribute("errorMessage", "Echec de l'enregistrement', "+message);
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
