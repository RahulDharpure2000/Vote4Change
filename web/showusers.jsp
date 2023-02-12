<%-- 
    Document   : showusers
    Created on : 26 Jun, 2021, 10:47:53 AM
    Author     : ASUS
--%>

<%@page import="evoting.dto.UserData"%>
<%@page import="java.util.ArrayList"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%
    ArrayList<UserData> users=(ArrayList<UserData>)request.getAttribute("users");
    StringBuilder sb=new StringBuilder("");
    int i=0;
    sb.append("<div id='chartContainer'><table><tr><th>User no.</th><th>User Name</th><th>City</th><th>Email</th><th>Gender</th><th>Mobile No</th></tr>");
    for(UserData u:users){
        sb.append("<tr><td>"+ ++i +"</td><td>"+u.getUsername()+"</td><td>"+u.getCity()+"</td><td>"+u.getEmail()+"</td><td>"+u.getGen()+"</td><td>"+u.getMobile()+"</td></tr>");
    }
    sb.append("</table></div>");
    out.print(sb.toString());
%>
