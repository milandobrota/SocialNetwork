/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package servlets;

import dao.PersonDao;
import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Date;
import java.util.List;
import java.util.Random;
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
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

/**
 *
 * @author milandobrota
 */
@WebServlet(name = "UpdatePersonalInfoServlet", urlPatterns = {"/update_personal_info"})
public class UpdatePersonalInfoServlet extends HttpServlet {

    @EJB
    private PersonDao personDao;
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
	ServletContext ctx = getServletConfig().getServletContext();
	HttpSession session = request.getSession(true);
        PrintWriter out = response.getWriter();
        try {



// Create a factory for disk-based file items
                FileItemFactory factory = new DiskFileItemFactory();
                ServletFileUpload upload = new ServletFileUpload(factory);
                
                List<FileItem> items = upload.parseRequest(request);

		Integer personId = (Integer)session.getAttribute("personId");
                String firstName = items.get(0).getString();
                String lastName = items.get(1).getString();
		String sexString = items.get(2).getString();
		String dayOfBirth = items.get(3).getString();
		String monthOfBirth = items.get(4).getString();
		String yearOfBirth = items.get(5).getString();
		String email = items.get(6).getString();
		String place = items.get(7).getString();
		String website = items.get(8).getString();
		String education = items.get(9).getString();
		String occupation = items.get(10).getString();
		String employment = items.get(11).getString();

		boolean sex = (sexString.equals("male")) ? true : false;

                
                FileItem picture = items.get(12);
                
                String pictureFilename = null;
                if(!picture.getName().equals("")) {
                    pictureFilename = "/a" + (new Random()).nextLong() + picture.getName();
                }
                
                File uploadedFile = new File("docroot/" + pictureFilename);
                picture.write(uploadedFile);
                

		boolean success = personDao.updatePersonalInformation(personId, firstName, lastName, dayOfBirth, monthOfBirth, yearOfBirth, sex, email, place, website, education, occupation, employment, pictureFilename);


            out.println(success);
	    if(success) {
	      response.sendRedirect("profile");
	      return;
	    } else {
		request.setAttribute("error", "Illegal operation");
		response.sendRedirect("edit_profile");
		return;

	    }



	} catch(Exception e) {
	    out.println(e);
        } finally {            
            out.close();
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
