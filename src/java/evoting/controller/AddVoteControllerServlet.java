
package evoting.controller;

import evoting.dao.VoterDao;
import evoting.dto.CandidateInfo;
import evoting.dto.VoteDTO;
import java.io.IOException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;


public class AddVoteControllerServlet extends HttpServlet {

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
        RequestDispatcher rd = null;
        HttpSession sess = request.getSession();
        String userid = (String) sess.getAttribute("adhar_no");
        System.out.println(userid+" From AddVoteControllerServlet");
        if (userid == null) {
            sess.invalidate();
            response.sendRedirect("accessdenied.html");
            return;
        }
        try {
            String candidateid = request.getParameter("candidateid");
            System.out.println(candidateid);
            VoteDTO vote = new VoteDTO();
            vote.setCandidateId(candidateid);
            vote.setVoterId(userid);
            boolean result = VoterDao.addVote(vote);
            CandidateInfo candidate = VoterDao.getVote(userid);
            if (result == true) {
                sess.setAttribute("candidate", candidate);
            }
            request.setAttribute("result", result);
            rd=request.getRequestDispatcher("verifyvote.jsp");
        } catch (Exception ex) {
            ex.printStackTrace();
            rd = request.getRequestDispatcher("showexception.jsp");
            request.setAttribute("Exception", ex);
        }
        finally{
            if(rd!=null)
            {
                rd.forward(request, response);
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
