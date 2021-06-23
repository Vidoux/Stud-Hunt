package services;


import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import persistantdata.User;
import studhunt.StudHunt;
import util.UserTypes;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.List;


/**
 * Servlet de gestion de l'envoie et de la modification de l'image de
 * profils des utilisateurs
 */
@WebServlet("/image_action")
public class ImageActionServlet extends HttpServlet {

    /**
     * Retourne l'image correspondante au compte connecté
     * @param request
     * @param response
     * @throws ServletException
     * @throws IOException
     */
    public void doGet( HttpServletRequest request, HttpServletResponse response )	throws ServletException, IOException {
        HttpSession session = request.getSession();
        if(session.getAttribute("user") == null){
            this.getServletContext().getRequestDispatcher("/WEB-INF/login.jsp").forward(request, response);
        }
        byte[] imageByte = null;
        User user = (User) session.getAttribute("user");
        if(user != null) {
            imageByte = StudHunt.getInstance().getProfilePicture(user.getEmail());
        }
        if(imageByte == null){
            String contexPath = getServletContext().getRealPath(File.separator);
            File img = new File(contexPath + "/img/avatar.png");
            imageByte = Files.readAllBytes(img.toPath());
        }
        response.setContentType("image/jpg");
        response.getOutputStream().write(imageByte);
    }

    /**
     * Mise à jour de la photo de profil d'un utilisateur
     * @param request
     * @param response
     * @throws ServletException
     * @throws IOException
     */
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        User user = (User) session.getAttribute("user");
        if(user== null){
            sendErrorPage(request, response, "Vous devez vous authentifier");
        }

        // Check that we have a file upload request
        boolean isMultipart = ServletFileUpload.isMultipartContent(request);

        if( !isMultipart) {
            sendErrorPage(request,response, "error while trying to upload the image");
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


            for (Object fileItem : fileItems) {
                FileItem fi = (FileItem) fileItem;
                if (!fi.isFormField()) {
                    byte[] byteImage = fi.get();

                    assert user != null;
                    System.out.println("image: emailID" + user.getEmail());
                    StudHunt.getInstance().setProfilePicture(user.getEmail(), byteImage);


                }
            }
        } catch(Exception ex) {
            sendErrorPage(request,response, "error while trying to upload the image" + ex);
//            return;
        }
        assert user != null;
        if(user.getUserType() == UserTypes.STUDENT){
            this.getServletContext().getRequestDispatcher("/WEB-INF/student_info.jsp").forward(request, response);
        }
        if(user.getUserType() == UserTypes.COMPANY){
            this.getServletContext().getRequestDispatcher("/WEB-INF/company_info.jsp").forward(request, response);
        }
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
        request.setAttribute("errorMessage", "Echec du traitement de l'image, "+message);
        this.getServletContext().getRequestDispatcher("/WEB-INF/main_page.jsp").forward(request, response);
    }

    public void destroy() {
        super.destroy();
    }
}
