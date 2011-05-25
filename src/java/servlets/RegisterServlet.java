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
import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileItemFactory;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

/**
 *
 * @author milandobrota
 */
@WebServlet(name = "RegisterServlet", urlPatterns = {"/register"})
public class RegisterServlet extends HttpServlet {
    
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
        PrintWriter out = response.getWriter();
        try {



// Create a factory for disk-based file items
                FileItemFactory factory = new DiskFileItemFactory();
                ServletFileUpload upload = new ServletFileUpload(factory);
                
                List<FileItem> items = upload.parseRequest(request);

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
                



            boolean success = personDao.register(firstName, lastName, Date.valueOf(yearOfBirth + "-" + monthOfBirth + "-" + dayOfBirth), sex, email, place, website, occupation, employment, education, pictureFilename);
	    if(success) {
		out.println("We emailed you a confirmation link");
		return;
	    } else {
		request.setAttribute("error", "There was an error processing your request. Are you already registered?");
		ctx.getRequestDispatcher("/signup.jsp").forward(request, response);
		return;

	    }
            /* TODO output your page here
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet RegisterServlet</title>");  
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet RegisterServlet at " + request.getContextPath () + "</h1>");
            out.println("</body>");
            out.println("</html>");
             */
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
