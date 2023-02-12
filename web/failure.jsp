<%-- 
    Document   : failure
    Created on : 20 May, 2021, 1:45:35 PM
    Author     : ASUS
--%>
<%
    String userid=(String)session.getAttribute("userid");
    if(userid==null)
    {
        session.invalidate();
        response.sendRedirect("accessdenied.html");
        return;
    }
    String fail=(String)request.getAttribute("fail");
    if(fail.equals("alr")){
        out.print("alr");
    }
    else
    out.print("Failed");
%>