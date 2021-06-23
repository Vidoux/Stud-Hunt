package services;


import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import persistantdata.User;
import studhunt.ConnexionInfos;
import studhunt.StudHunt;


import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.*;
import java.nio.file.Files;
import java.sql.Blob;
import java.sql.SQLException;
import java.util.Iterator;
import java.util.List;
import javax.imageio.ImageIO;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.*;
import javax.servlet.annotation.*;


/**
 * Servlet de gestion de l'Authentification par login mot de passe
 */
@WebServlet("/image_action")
public class ImageActionServlet extends HttpServlet {

    private boolean isMultipart;
    private String filePath;
    private final int maxFileSize = 50 * 102400;
    private final int maxMemSize = 4 * 102400;
    private File file ;

    public void init( ){
        // Get the file location where it would be stored.
        filePath = getServletContext().getInitParameter("file-upload");
    }




    public void doGet( HttpServletRequest request, HttpServletResponse response )	throws ServletException, IOException {
        HttpSession session = request.getSession();
        if(session.getAttribute("user") == null){
            this.getServletContext().getRequestDispatcher("/WEB-INF/login.jsp").forward(request, response);
        }
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
        HttpSession session = request.getSession();
        if((session.getAttribute("user") == null)){
            sendErrorPage(request, response, "Vous devez vous authentifier");
        }
//       System.out.println("Tanguy");
//       HttpSession session = request.getSession();
//        System.out.println(request.getParameterValues("image"));
      // StudHunt.getInstance().setProfilePicture("mail.text@mail.com",request.getParameterValues("image"));

        // Check that we have a file upload request
//        isMultipart = ServletFileUpload.isMultipartContent(request);
//        response.setContentType("text/html");
//        java.io.PrintWriter out = response.getWriter( );
//
//        if( !isMultipart ) {
//            sendErrorPage(request,response, "error while trying to upload the image");
//            return;
//        }
//
//        DiskFileItemFactory factory = new DiskFileItemFactory();
//
//        // maximum size that will be stored in memory
//        factory.setSizeThreshold(maxMemSize);
//
//        // Location to save data that is larger than maxMemSize.
//        factory.setRepository(new File("D:\\tempImages"));
//
//        // Create a new file upload handler
//        ServletFileUpload upload = new ServletFileUpload(factory);
//
//        // maximum file size to be uploaded.
//        upload.setSizeMax( maxFileSize );
//
//        try {
//            // Parse the request to get file items.
//            List<FileItem> fileItems = upload.parseRequest(request);
//
//            // Process the uploaded file items
//            Iterator i = fileItems.iterator();
//
//            out.println("<html>");
//            out.println("<head>");
//            out.println("<title>Servlet upload</title>");
//            out.println("</head>");
//            out.println("<body>");
//
//            while ( i.hasNext () ) {
//                FileItem fi = (FileItem)i.next();
//                if ( !fi.isFormField () ) {
//                    // Get the uploaded file parameters
//                    String fieldName = fi.getFieldName();
//                    String fileName = fi.getName();
//                    String contentType = fi.getContentType();
//                    Boolean isInMemory = fi.isInMemory();
//                    Long sizeInBytes = fi.getSize();
//
//                    // Write the file
//                    if( fileName.lastIndexOf("\\") >= 0 ) {
//                        file = new File( filePath + fileName.substring( fileName.lastIndexOf("\\"))) ;
//                    } else {
//                        file = new File( filePath + fileName.substring(fileName.lastIndexOf("\\")+1)) ;
//                    }
//                    fi.write( file ) ;
//                    out.println("Uploaded Filename: " + fileName + "<br>");
//                }
//            }
//            out.println("</body>");
//            out.println("</html>");
//        } catch(Exception ex) {
//            System.out.println(ex);
//        }

        // Check that we have a file upload request
        isMultipart = ServletFileUpload.isMultipartContent(request);

        if( !isMultipart ) {
            sendErrorPage(request,response, "error while trying to upload the image");
//            return;
        }

        DiskFileItemFactory factory = new DiskFileItemFactory();

        // maximum size that will be stored in memory
        factory.setSizeThreshold(maxMemSize);

        // Location to save data that is larger than maxMemSize.
        factory.setRepository(new File("c:\\temp"));

        // Create a new file upload handler
        ServletFileUpload upload = new ServletFileUpload(factory);

        // maximum file size to be uploaded.
        upload.setSizeMax( maxFileSize );

        try {
            // Parse the request to get file items.
            List fileItems = upload.parseRequest(request);

            // Process the uploaded file items
            Iterator i = fileItems.iterator();


            while ( i.hasNext () ) {
                FileItem fi = (FileItem)i.next();
                if ( !fi.isFormField () ) {
                    // Get the uploaded file parameters
                    String fieldName = fi.getFieldName();
                    String fileName = fi.getName();
                    String contentType = fi.getContentType();
                    boolean isInMemory = fi.isInMemory();
                    long sizeInBytes = fi.getSize();

                    byte [] byteImage = fi.get();

                    System.out.println(byteImage.toString());


                }
            }
        } catch(Exception ex) {
            sendErrorPage(request,response, "error while trying to upload the image" + ex);
//            return;
        }
    }


    private void sendErrorPage(HttpServletRequest request, HttpServletResponse response, String message) throws ServletException, IOException {
        request.setAttribute("backDestination", "./login");
        request.setAttribute("errorMessage", "Echec du traitement de l'image, "+message);
        this.getServletContext().getRequestDispatcher("/WEB-INF/main_page.jsp").forward(request, response);
    }

    public void destroy() {
        super.destroy();
    }
}
