package services;


import persistantdata.User;
import studhunt.ConnexionInfos;
import studhunt.StudHunt;
import util.UserTypes;

import java.io.*;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.*;
import javax.servlet.annotation.*;


/**
 * Servlet de gestion de l'Authentification par login mot de passe
 */
@WebServlet("/login")
public class LoginServlet extends HttpServlet {



    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String password = request.getParameter("password");
        String login = request.getParameter("login");
        System.out.println(login);
        System.out.println(password);
        try{
            ConnexionInfos loginStatus = StudHunt.getInstance().getUserConnection(login, password);
            System.out.println(loginStatus.toString());

            String message;
            //TODO enlever le password de la variable session
            User user = new User(login, password, loginStatus.getUserType());


            if(!loginStatus.isValidPassword()){
                message = "Utilisateur non reconnu...";
                sendErrorPage(request,response, message);
            }
            //Création de la variable session
            HttpSession session = request.getSession();
            session.setAttribute("user", user);

            this.getServletContext().getRequestDispatcher("/WEB-INF/main_page.jsp").forward(request, response);
        }catch (Exception e){
            sendErrorPage(request,response, "error during authentification, please retry");
        }
    }

    public void doGet( HttpServletRequest request, HttpServletResponse response )	throws ServletException, IOException {
        this.getServletContext().getRequestDispatcher( "/WEB-INF/login.jsp").forward( request, response );
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
