
package evoting.controller;

import evoting.dao.RegistrationsDAO;
import evoting.dto.UserDetails;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


public class RegistrationControllerServlet extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
       UserDetails user=new UserDetails();
        user.setUserid(request.getParameter("userid"));
        user.setUsername(request.getParameter("username"));
        user.setEmail(request.getParameter("email"));
        user.setCity(request.getParameter("city"));
        user.setAddress(request.getParameter("address"));
        user.setMobile(Long.parseLong(request.getParameter("mobile")));
        user.setPassword(request.getParameter("password"));
        user.setGender(request.getParameter("gender").charAt(0));
        boolean result=false,userfound=false;
        RequestDispatcher rd=null;
        try {
            if(!RegistrationsDAO.searchUser(user.getUserid()))
            {
                result=RegistrationsDAO.registerUser(user);
                System.out.println(result);
            }
            else{
                userfound=true;
            }
            rd=request.getRequestDispatcher("registrationresponse.jsp");
            request.setAttribute("result", result);
            request.setAttribute("userfound", userfound);
        } catch (SQLException ex) {
            ex.printStackTrace();
            System.out.println("Error in RegistrationServlet"+ex);
            request.setAttribute("Exception", ex);
        }
        finally{
            rd.forward(request, response);
        }

//        System.out.println(result);
//        System.out.println(userfound);
        
        
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
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
     *
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
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
