/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package servlets;

import dao.FriendRequestDao;
import dao.PostDao;
import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.EJB;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileItemFactory;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

/**
 *
 * @author milandobrota
 */
@WebServlet(name = "CreatePostServlet", urlPatterns = {"/create_post"})
public class CreatePostServlet extends HttpServlet {
    
    @EJB
    private PostDao postDao;

    @EJB
    private FriendRequestDao friendRequestDao;

    /** 
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code> methods.
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        HttpSession session = request.getSession(true);
        Integer personId = (Integer)session.getAttribute("personId");
        ServletContext ctx = getServletConfig().getServletContext();
        if(personId == null) {
            response.sendRedirect("login");
            return;
        }
        
        
        PrintWriter out = response.getWriter();
        boolean isMultipart = ServletFileUpload.isMultipartContent(request);
       
        try {
                    
            //if (isMultipart) {
                // Create a factory for disk-based file items
                FileItemFactory factory = new DiskFileItemFactory();
                ServletFileUpload upload = new ServletFileUpload(factory);
                
                List<FileItem> items = upload.parseRequest(request);

                String title = items.get(0).getString();
                String text = items.get(1).getString();
                
                FileItem picture = items.get(2);
                
                String pictureFilename = null;
                if(!picture.getName().equals("")) {
                    pictureFilename = "/a" + (new Random()).nextLong() + picture.getName();
                }
                
                File uploadedFile = new File("docroot/" + pictureFilename);
                picture.write(uploadedFile);
                
                String youtubeVideoId = items.get(3).getString();
	
                String link = items.get(4).getString();
                
                Integer ownerId = null;
                if (items.get(5).getString() != null && !items.get(5).getString().equals("null")) {
                    ownerId = Integer.parseInt(items.get(5).getString());
                } else {
                    ownerId = personId;
                }
                
            //}
		if(!personId.equals(ownerId) && !friendRequestDao.areFriends(personId, ownerId)) {
		    out.println("You do not have permission to post here");
		    return;
		}
            

            if(postDao.createPost(title, text, personId, ownerId, pictureFilename, youtubeVideoId, link)) {
                response.sendRedirect("wall?ownerId=" + ownerId);
                return;
            } else {
                out.println("failure");
            }
        } catch (Exception e) {
            //handle the exception here
            System.out.println(e);
        }
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /** 
     * Handles the HTTP <code>GET</code> method.
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /** 
     * Handles the HTTP <code>POST</code> method.
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /** 
     * Returns a short description of the servlet.
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>
}
