

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%
  String result=(String)request.getAttribute("result");
    String adhar_no=(String)request.getAttribute("userid");
    System.out.println(adhar_no+"  From loginResponse "+ result);
    if (result!=null && adhar_no!=null)
    {
//        HttpSession sess=request.getSession();
//        sess.setAttribute("adhar_no",adhar_no);
        session.setAttribute("adhar_no", adhar_no);
        String url="";
        if(result.equalsIgnoreCase("Admin"))
        {
            url="AdminControllerServlet;jsessionid="+session.getId();
        }
        else
        {
            url="VotingControllerServlet;jsessionid="+session.getId();
        }
        out.println(url);
    }
    else
    {
        out.println("error");
    }
%>

