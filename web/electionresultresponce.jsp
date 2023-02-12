<%-- 
    Document   : electionresultresponce
    Created on : 12 Jun, 2021, 3:16:44 PM
    Author     : ASUS
--%>
<%@page import="java.util.Map"%>
<%@page import="java.util.Iterator"%>
<%@page import="java.util.LinkedHashMap"%>
<%@page import="evoting.dto.CandidateDetails"%>
<%@page import="evoting.dto.CandidateInfo"%>
<%@page import="java.util.ArrayList"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!--<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <script src="jsscript/vote.js"></script>
        <script src="jsscript/jquery.js"></script>
        <script src="https://unpkg.com/sweetalert/dist/sweetalert.min.js"></script>
        <link href="stylesheet/backgroundimage.css" rel="stylesheet">
        <link href="stylesheet/pageheader.css" rel="stylesheet">
        <link href="stylesheet/showcandidate.css" rel="stylesheet">
        <title>Result</title>
    </head>
    <body>-->

        <%
            String userid = (String) session.getAttribute("adhar_no");
            if (userid == null) {
                response.sendRedirect("accessdenied.html");
                return;
            }
            
            StringBuffer displayBlock=new StringBuffer("");
            displayBlock.append("<div id='electionresult' >");
            displayBlock.append("<table><tr><th>Candidate ID</th><th>User Name</th><th>City</th><th>Symbol</th><th>Vote %</th></tr>");
                  LinkedHashMap<CandidateDetails,Integer> result=(LinkedHashMap<CandidateDetails,Integer>)request.getAttribute("result");
                  int totalVote=(int)request.getAttribute("totalVote");
                  Iterator it=result.entrySet().iterator();
                  while(it.hasNext())
                  {
                      Map.Entry<CandidateDetails,Integer> en=(Map.Entry<CandidateDetails,Integer>)it.next();
                      CandidateDetails cd=en.getKey();
                      displayBlock.append("<tr><th>"+cd.getCandidateId()+"</th><th>"+cd.getCname()+"</th><th>"+cd.getCity()+"</th><th><img src='data:image/jpg;base64," + cd.getSymbol() + "' style='width:300px;height:200px;'/></th><th>"+((en.getValue()*100.0f)/totalVote)+"%</th></tr>");
                  }
                    displayBlock.append("</table></div>");
                    System.out.println(displayBlock.toString());
                out.print(displayBlock.toString());
            
        %>
<!--    </body>
</html>-->
