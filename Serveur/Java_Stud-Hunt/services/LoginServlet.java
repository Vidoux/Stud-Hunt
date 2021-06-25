package services;


import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import studhunt.StudHunt;
import util.User;
import util.UserTypes;



/**
 * Servlet de gestion de l'Authentification par login mot de passe
 */
@WebServlet("/login")
public class LoginServlet extends HttpServlet {


    /**
     * Traitement du formulaire de connexion, authentification
     * du couple mot de passe/adresse mail
     * @param request
     * @param response
     * @throws ServletException
     * @throws IOException
     */
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String password = request.getParameter("password");
        String login = request.getParameter("login");
        System.out.println(login);
        System.out.println(password);
        try{
            UserTypes userType =  StudHunt.getInstance().getUserType(login);
            String message;
            if(userType == null){
                message = "Utilisateur non reconnu...";
                sendErrorPage(request,response, message);
            }
            User user =null;
            user = StudHunt.getInstance().getUser(login, password);

            if(user == null){
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

    /**
     * Retourne la page de connexion
     * @param request
     * @param response
     * @throws ServletException
     * @throws IOException
     */
    public void doGet( HttpServletRequest request, HttpServletResponse response )	throws ServletException, IOException {
        this.getServletContext().getRequestDispatcher( "/WEB-INF/login.jsp").forward( request, response );
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
        request.setAttribute("backDestination", "./login");
        request.setAttribute("errorMessage", "Echec de la connexion, "+message);
        this.getServletContext().getRequestDispatcher("/WEB-INF/error.jsp").forward(request, response);
    }


    public void destroy() {
        super.destroy();
    }
}
