package services;


import studhunt.ConnexionInfos;
import studhunt.StudHunt;
import studhunt.UserTypes;

import java.io.*;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.*;
import javax.servlet.annotation.*;


/**
 * Servlet de gestion de l'Authentification par login mot de passe
 */
@WebServlet("/login_frm")
public class LoginServlet extends HttpServlet {



    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String password = request.getParameter("password");
        String login = request.getParameter("login");
        ConnexionInfos loginStatus = StudHunt.getInstance().getUser(login, password);

        String message;

        if(loginStatus.isValidPassword()){
            UserTypes user = loginStatus.getUser();
        }else{
            message = "Utilisateur non reconnu...";
            sendErrorPage(request,response, message);
        }

        if()


        // On vérifie d'abord l'existence de l'utilisateur puis qu'il est bibliothécaire
        if(user != null){
            Object userType = user.data()[3];
            System.out.println(userType.toString());

            if(userType.toString().equals("LIBRARIAN")){
                //Connexion autorisée
                System.out.println("Logged in");
                RequestDispatcher disp = request.getRequestDispatcher("/doc_list");
                disp.forward(request, response);

            }else{
                message = "Vous n'êtes pas autorisé à accéder à cette ressource";
                sendErrorPage(request, response, message);
            }

        }else{
            message = "Utilisateur non reconnu...";
            sendErrorPage(request,response, message);
        }

    }

    public void doGet( HttpServletRequest request, HttpServletResponse response )	throws ServletException, IOException {
        this.getServletContext().getRequestDispatcher( "/WEB-INF/login.jsp").forward( request, response );
    }

    private void sendErrorPage(HttpServletRequest request, HttpServletResponse response, String message) throws ServletException, IOException {
        request.setAttribute("backDestination", "./login_frm");
        request.setAttribute("errorMessage", "Echec de la connexion, "+message);
        this.getServletContext().getRequestDispatcher("/WEB-INF/error.jsp").forward(request, response);
    }

    public void destroy() {
        super.destroy();
    }
}
