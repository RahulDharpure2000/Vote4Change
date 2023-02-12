
package evoting.controller;

import evoting.dao.CandidateDao;
import evoting.dao.VoterDao;
import evoting.dto.CandidateDetails;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.Map;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;


public class ResultControllerServlet extends HttpServlet {

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
         RequestDispatcher rd = null;
        String check = request.getParameter("check");
        HttpSession sess=request.getSession();
        try {
            String userid=(String)sess.getAttribute("adhar_no");
           
           if(userid==null)
           {
               System.out.println(userid);
               sess.invalidate();
               response.sendRedirect("accessdenied.html");
               return ;
           }
            if (check.equals("city")) {
                LinkedHashMap<String, Integer> result = VoterDao.getResult();
                Iterator it = result.entrySet().iterator();
                LinkedHashMap<CandidateDetails, Integer> candidateResult = new LinkedHashMap<>();
                int totalVote = 0;
                while (it.hasNext()) {
                    Map.Entry<String, Integer> en = (Map.Entry<String, Integer>) it.next();
                    candidateResult.put(CandidateDao.getDetailsById(en.getKey()), en.getValue());
                    totalVote += en.getValue();
                }

               
                rd = request.getRequestDispatcher("electionresultresponce.jsp");
                 request.setAttribute("result", candidateResult);
                request.setAttribute("totalVote", totalVote);
            } else if(check.equals("party")) {
                LinkedHashMap<String, Integer> result = VoterDao.getResultByParty();
                System.out.print(result);
                rd = request.getRequestDispatcher("electionresultbyparty.jsp");
                 request.setAttribute("result", result);
            }

        } catch (Exception ex) {
            ex.printStackTrace();
            System.out.println("ElectionresultControllerservlet");
            rd = request.getRequestDispatcher("showexception.jsp");
            request.setAttribute("Excpetion", ex);
        } finally {
            if (rd != null) {
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
