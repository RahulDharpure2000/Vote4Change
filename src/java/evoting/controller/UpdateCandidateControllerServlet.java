
package evoting.controller;

import evoting.dao.CandidateDao;
import evoting.dto.CandidateDetails;
import evoting.dto.CandidateDto;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.tomcat.util.http.fileupload.FileItem;
import org.apache.tomcat.util.http.fileupload.disk.DiskFileItemFactory;
import org.apache.tomcat.util.http.fileupload.servlet.ServletFileUpload;
import org.apache.tomcat.util.http.fileupload.servlet.ServletRequestContext;
import org.json.JSONObject;


public class UpdateCandidateControllerServlet extends HttpServlet {

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
        RequestDispatcher rd = null;
        try {
            String cid = request.getParameter("data");
            if (cid != null) {
                CandidateDetails cd = CandidateDao.getDetailsById(cid);
                JSONObject json = new JSONObject();
                json.put("cid", cd.getCandidateId());
                json.put("party", cd.getParty());
                json.put("city", cd.getCity());
                json.put("uid", cd.getUserId());
                json.put("cname", cd.getCname());
                json.put("symbol", cd.getSymbol());
                out.print(json);
            } else {
                DiskFileItemFactory df = new DiskFileItemFactory();
                ServletFileUpload sfu = new ServletFileUpload(df);
                ServletRequestContext src = new ServletRequestContext(request);
                List<FileItem> multiList = sfu.parseRequest(src);
                ArrayList<String> objValues = new ArrayList<>();
                InputStream ipt = null;
                for (FileItem fit : multiList) {
                    if (fit.isFormField()) {
                        String fname = fit.getFieldName();
                        String value = fit.getString();
                        System.out.println("Inside if");
                        System.out.println(fname + ":" + value);
                        objValues.add(value);
                    } else {
                        ipt = fit.getInputStream();
                        String key = fit.getFieldName();
                        String filename = fit.getName();
                        System.out.println("Inside else block");
                        System.out.println(key + ":" + filename);
                    }
                }
                CandidateDto candidate = new CandidateDto(objValues.get(0), objValues.get(3), objValues.get(4), objValues.get(1), ipt, objValues.get(2));
                boolean result = CandidateDao.updateCandidate(candidate);
                if(result==true)
                {
                    out.print("success");
                }
                else{
                    out.print("error");
                }

            }

        } catch (Exception ex) {
            ex.printStackTrace();
            rd = request.getRequestDispatcher("showexception.jsp");
            request.setAttribute("Excpetion", ex);
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
