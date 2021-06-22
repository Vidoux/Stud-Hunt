package services;


import persistantdata.User;
import studhunt.ConnexionInfos;
import studhunt.StudHunt;
import studhunt.UserTypes;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.*;
import java.sql.Blob;
import java.sql.SQLException;
import javax.imageio.ImageIO;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.*;
import javax.servlet.annotation.*;


/**
 * Servlet de gestion de l'Authentification par login mot de passe
 */
@WebServlet("/image_action")
public class ImageActionServlet extends HttpServlet {


    public void doGet( HttpServletRequest request, HttpServletResponse response )	throws ServletException, IOException {
        System.out.println(request.getParameter("id"));
        Blob imageBlob = StudHunt.getInstance().getProfilePicture(request.getParameter("id"));
        InputStream in = null;
        try {
            in = imageBlob.getBinaryStream();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        BufferedImage image = ImageIO.read(in);
        OutputStream out = response.getOutputStream();
        ImageIO.write(image, "jpg", out);
        out.close();
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
       System.out.println("Tanguy");
       HttpSession session = request.getSession();
      // StudHunt.getInstance().setProfilePicture("mail.text@mail.com",request.getParameterValues("image"));


    }


    public void destroy() {
        super.destroy();
    }
}
