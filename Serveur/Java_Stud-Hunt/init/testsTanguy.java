package init;

import java.io.File;


import javax.servlet.ServletException;


import studhunt.StudHunt;


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

       // studHuntClass.setProfilePicture("mail.text@mail.com", pic);

        }

    }

