<%-- 
    Document   : vote
    Created on : 29 May, 2021, 3:45:57 PM
    Author     : ASUS
--%>

<%@page import="evoting.dto.CandidateInfo"%>
<%@page import="java.util.ArrayList"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <script src="jsscript/vote.js"></script>
        <script src="jsscript/jquery.js"></script>
        <script src="https://unpkg.com/sweetalert/dist/sweetalert.min.js"></script>
        <link href="stylesheet/backgroundimage.css" rel="stylesheet">
        <link href="stylesheet/pageheader.css" rel="stylesheet">
        <link href="stylesheet/showcandidate.css" rel="stylesheet">
        <title>Polling Booth</title>
    </head>
    <body>

        <%
            String userid = (String) session.getAttribute("adhar_no");
            if (userid == null) {
                response.sendRedirect("accessdenied.html");
                return;
            }
            
            StringBuffer displayBlock=new StringBuffer("");
            displayBlock.append("<div class='sticky'> <div class='candidate'>VOTE FOR CHANGE</div><br>"+
                    "<div class='subcandidate' >WHOM DO YOU WANT TO VOTE ? </div>"+
                    "<div class='logout'><a href='LoginControllerServlet?logout=logout'>logout</a></div>"+
                    "</div><div class='buttons'>");
            ArrayList<CandidateInfo> candidates = (ArrayList<CandidateInfo>) request.getAttribute("candidateList");
                System.out.println(candidates);
                for (CandidateInfo ci : candidates) {
                    displayBlock.append("<input type='radio' name='flat' id='"+ci.getCandidateId()+"' value='"+ci.getCandidateId()+"' onclick='addVote()'>"+
                            "<lable for='"+ci.getCandidateId()+"'><img src='data:image/jpg;base64," + ci.getSymbol() + "' style='width:300px;height:200px;'/></lable>"+
                            "<br><div class='candidateprofile'><p>CandidateID:"+ci.getCandidateId()+"<br>"+
                            "Candidate Name:"+ci.getCname()+"<br>"+
                            "Party: "+ci.getParty()+"<br></div>");
                }
                    displayBlock.append("</div>");
                out.print(displayBlock.toString());
            
        %>
    </body>
</html>
