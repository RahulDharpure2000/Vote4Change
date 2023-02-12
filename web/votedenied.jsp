<%-- 
    Document   : votedenied
    Created on : 2 Jun, 2021, 2:27:06 PM
    Author     : ASUS
--%>

<%@page import="evoting.dto.CandidateInfo"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <script src="https://unpkg.com/sweetalert/dist/sweetalert.min.js"></script>
        <link href="stylesheet/backgroundimage.css" rel="stylesheet">
        <link href="stylesheet/pageheader.css" rel="stylesheet">
        <link href="stylesheet/showcandidate.css" rel="stylesheet">
        <title>Vote Denied</title>
    </head>
    <body>
        <%
            String userid = (String) session.getAttribute("adhar_no");
            if (userid == null) {
                response.sendRedirect("accessdenied.html");
                return;
            }
             CandidateInfo ci = (CandidateInfo) session.getAttribute("candidate");
            StringBuffer displayBlock = new StringBuffer("");
            displayBlock.append("<div class='sticky'> <div class='candidate'>VOTE FOR CHANGE</div><br>"
                    + "<div class='subcandidate' >Sorry You have allready casted Vote! </div>"
                    + "<div class='logout'><a href='LoginControllerServlet?logout=logout'>logout</a></div>");
                displayBlock.append("<br><div class='candidateprofile'><p><br>");
                displayBlock.append("CandidateID:" + ci.getCandidateId() + "<br>");
                displayBlock.append("<strong>You Voted for</strong><br><img src='data:image/jpg;base64," + ci.getSymbol() + "' style='width:300px;height:200px;'/><br>"
                        + "Candidate Name:" + ci.getCname() + "<br>"
                        + "Party: " + ci.getParty() + "</p><br></div>");
            out.print(displayBlock.toString());

        %>
    </body>
</html>
