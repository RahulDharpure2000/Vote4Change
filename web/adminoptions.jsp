<%-- 
    Document   : adminoptions
    Created on : 10 May, 2021, 1:22:34 PM
    Author     : ASUS
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <script src="jsscript/adminoptions.js"></script>
        <script src="jsscript/jquery.js"></script>
        <script src="https://unpkg.com/sweetalert/dist/sweetalert.min.js"></script>
        <link href="stylesheet/backgroundimage.css" rel="stylesheet">
        <link href="stylesheet/pageheader.css" rel="stylesheet">
        <link href="stylesheet/admin.css" rel="stylesheet">
        <title>Admin Options</title>
    </head>
    <body>
        <%
            String userid = (String) session.getAttribute("adhar_no");
            if (userid == null) {
                response.sendRedirect("accessdenied.html");
                return;
            }
            StringBuffer displayBlock = new StringBuffer("<div class='sticky'><div class='candidate'>VOTE FOR CHANGE</div><br>"
                    + "<div class='subcandidate'>Admin Actions Page</div><br><br>"
                    + "<div class='logout'><a href='LoginControllerServlet?logout=logout'>logout</a></div></div>");
            displayBlock.append("<div class='container'>");
            displayBlock.append("<div id='dv1' onclick='redirectadministratorpage()'><img src='images/administrator.png' height='300px' width='250px'><br><h3>Admin Options</h3></div>");
            displayBlock.append("<div id='dv2' onclick='redirectvotingpage()'><img src='images/voteadmin.jpg' height='300px' width='250px'><br><h3>Voting Page</h3></div>");
            displayBlock.append("</div>");
            displayBlock.append("<br><br><div align='center' id='result'></div>");
            out.println(displayBlock);
        %>
    </body>
</html>