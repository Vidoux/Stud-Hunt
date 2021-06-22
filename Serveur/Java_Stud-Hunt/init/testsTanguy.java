package init;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;

import studhunt.ConnexionInfos;
import studhunt.StudHunt;
import studhunt.UserTypes;

public class testsTanguy {
    public static void main(String[] args) {
        //Init
        try {
            new InjectionServlet().init();
        } catch (ServletException e) {
            System.err.println("Error in the servlet injection");
            e.printStackTrace();
        }
        StudHunt studHuntClass = StudHunt.getInstance();

        File pic = new File("D:\\tanguy.jpg");

        studHuntClass.setProfilePicture("test@mail.com", pic);

        }

    }

