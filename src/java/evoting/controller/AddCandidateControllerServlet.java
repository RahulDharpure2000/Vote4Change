
package evoting.controller;

import evoting.dao.CandidateDao;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.ArrayList;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.json.JSONObject;


public class AddCandidateControllerServlet extends HttpServlet {

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
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        HttpSession session = request.getSession();
        String userid = (String) session.getAttribute("userid");
        String candidate = (String) request.getParameter("id");
        String usid = (String) request.getParameter("uid");
        if (userid == null) {
            session.invalidate();
            response.sendRedirect("accessdenied.html");
            return;
        } else if (candidate != null && candidate.equals("getid")) {
            try {
                String cid = CandidateDao.getNewCandidateId();
                out.println(cid);
                return;
            } catch (SQLException e) {
                e.printStackTrace();
            }
        } else if (usid != null) {
            try {
                String username = CandidateDao.getUsernameById(usid);
                ArrayList<String> city = CandidateDao.getCity();
                JSONObject json = new JSONObject();
                StringBuffer displayBlock = new StringBuffer("");
                for (String c : city) {
                    displayBlock.append("<option value='" + c + "'>" + c + "</option>");
                }
                System.out.println(displayBlock);
                System.out.println(username);
                if (username == null) {
                    username = "wrong";
                }
                json.put("username", username);
                json.put("city", displayBlock.toString());
                out.println(json);
            } catch (Exception ex) {
                ex.printStackTrace();
                RequestDispatcher rd=null;
           rd=request.getRequestDispatcher("showexception.jsp");
           request.setAttribute("Exception", ex);
            }
        }

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
