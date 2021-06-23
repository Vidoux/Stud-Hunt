package services;


import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import persistantdata.User;
import studhunt.StudHunt;

import javax.imageio.ImageIO;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.sql.Blob;
import java.sql.SQLException;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;


/**
 * Servlet de gestion de l'Authentification par login mot de passe
 */
@WebServlet("/cv_action")
public class CvActionServlet extends HttpServlet {

    private File file ;

    public void init( ){
        // Get the file location where it would be stored.
        String filePath = getServletContext().getInitParameter("file-upload");
    }




    public void doGet( HttpServletRequest request, HttpServletResponse response )	throws ServletException, IOException {
        HttpSession session = request.getSession();
        if(session.getAttribute("user") == null){
            this.getServletContext().getRequestDispatcher("/WEB-INF/login.jsp").forward(request, response);
        }

        User user = (User) session.getAttribute("user");
        byte[] CvByte = StudHunt.getInstance().getCV(user.getEmail());
        response.setContentType("application/pdf");
        response.getOutputStream().write(CvByte);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        if((session.getAttribute("user") == null)){
            sendErrorPage(request, response, "Vous devez vous authentifier");
        }

        // Check that we have a file upload request
        boolean isMultipart = ServletFileUpload.isMultipartContent(request);

        if( !isMultipart) {
            sendErrorPage(request,response, "error while trying to upload the CV");
//            return;
        }

        DiskFileItemFactory factory = new DiskFileItemFactory();

        // maximum size that will be stored in memory
        int maxMemSize = 4 * 102400;
        factory.setSizeThreshold(maxMemSize);

        // Location to save data that is larger than maxMemSize.
        factory.setRepository(new File("c:\\temp"));

        // Create a new file upload handler
        ServletFileUpload upload = new ServletFileUpload(factory);

        // maximum file size to be uploaded.
        int maxFileSize = 50 * 102400;
        upload.setSizeMax(maxFileSize);

        try {
            // Parse the request to get file items.
            List fileItems = upload.parseRequest(request);

            // Process the uploaded file items
            Iterator i = fileItems.iterator();


            while ( i.hasNext () ) {
                FileItem fi = (FileItem)i.next();
                if ( !fi.isFormField () ) {
                    byte [] byteCV = fi.get();

                    User user = (User) session.getAttribute("user");
                    System.out.println("cv: emailID" + user.getEmail());
                    StudHunt.getInstance().setCV(user.getEmail(), byteCV);
                }
            }
        } catch(Exception ex) {
            sendErrorPage(request,response, "error while trying to upload the CV" + ex);
//            return;
        }
        this.getServletContext().getRequestDispatcher("/WEB-INF/student_info.jsp").forward(request, response);
    }


    private void sendErrorPage(HttpServletRequest request, HttpServletResponse response, String message) throws ServletException, IOException {
        request.setAttribute("backDestination", "./login");
        request.setAttribute("errorMessage", "Echec du traitement du CV, "+message);
        this.getServletContext().getRequestDispatcher("/WEB-INF/main_page.jsp").forward(request, response);
    }

    public void destroy() {
        super.destroy();
    }
}
