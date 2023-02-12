<%-- 
    Document   : verifyvote
    Created on : 2 Jun, 2021, 3:22:52 PM
    Author     : ASUS
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%
    HttpSession sess=request.getSession();
    String userid=(String)sess.getAttribute("adhar_no");
     if (userid == null) {
            sess.invalidate();
            response.sendRedirect("accessdenied.html");
            return;
        }
     boolean result=(Boolean)request.getAttribute("result");
     if(result)
     {
         out.print("success");
     }
     else{
         out.print("failed");
     }
%>
