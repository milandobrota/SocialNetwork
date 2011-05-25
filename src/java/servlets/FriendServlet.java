/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package servlets;

import dao.PersonDao;
import entity.Person;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import javax.ejb.EJB;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author milandobrota
 */
@WebServlet(name = "FriendServlet", urlPatterns = {"/friends"})
public class FriendServlet extends HttpServlet {
    
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
        Integer personId = (Integer)session.getAttribute("personId");
	if(request.getParameter("personId") != null) {
		personId = Integer.parseInt(request.getParameter("personId"));
	}
        PrintWriter out = response.getWriter();
        try {    



	    List<Person> friends = null;
	    String orderParam = request.getParameter("order");
	    boolean order = (orderParam == null || !orderParam.equals("DESC"));
	    String orderBy = request.getParameter("orderBy");
	    if(orderBy != null && orderBy.equals("place")) {
		friends = personDao.friendsSortedByPlace(personId, order);
	    } else if(orderBy != null && orderBy.equals("dateOfBirth")) {
		friends = personDao.friendsSortedByDateOfBirth(personId, order);
	    } else {
		friends = personDao.friendsSortedByName(personId, order);
	    }

            List<Person> nonFriends = personDao.nonFriendsFor(personId);
	    //request.setAttribute("friends", friends);
	    //request.setAttribute("nonFriends", nonFriends);
            // ctx.getRequestDispatcher("/friends.jsp").forward(request, response);
	    request.setAttribute("people", friends);
            ctx.getRequestDispatcher("/friends.jsp").forward(request, response);
            /* TODO output your page here
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet FriendServlet</title>");  
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet FriendServlet at " + request.getContextPath () + "</h1>");
            out.println("</body>");
            out.println("</html>");
             */
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
