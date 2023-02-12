<%-- 
    Document   : manageuser
    Created on : 17 Jun, 2021, 2:56:53 PM
    Author     : ASUS
--%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <script src="jsscript/manageuser.js"></script>
        <script src="jsscript/jquery.js"></script>
        <script src="https://unpkg.com/sweetalert/dist/sweetalert.min.js"></script>
        <link href="stylesheet/backgroundimage.css" rel="stylesheet">
        <link href="stylesheet/pageheader.css" rel="stylesheet">
        <link href="stylesheet/admin.css" rel="stylesheet">
        <link href="stylesheet/result.css" rel="stylesheet">
        <title>Admin Actions Page</title>
    </head>
    <body>
        <%
            String userid = (String) session.getAttribute("adhar_no");
            if (userid == null) {
                response.sendRedirect("accessdenied.html");
                return;
            }
            out.println("<div class='sticky'><div class='candidate'>VOTE FOR CHANGE</div><br>"
                    + "<div class='subcandidate'>Admin Actions Page</div><br><br>"
                    + "<div class='logout'><a href='LoginControllerServlet?logout=logout'>logout</a></div></div>"
                    + "<div class='container'>"
                    + "<div id='dv1' onclick='registeruser()'><img src='images/register.png' height='250px' width='225px'><br><h3>Register User</h3></div>"
                    + "<div id='dv2' onclick='percentege()'><img src='images/male&female.jpg' height='250px' width='225px'><br><h3>Show Male & Female %</h3></div>"
                    + "<div id='dv3' onclick='showusers()'><img src='images/users.jpg' height='250px' width='225px'><br><h3>Show users</h3></div>"
                    + "<br><br><div align='center' id='result'>"
                    + "<script type='text/javascript' src='https://canvasjs.com/assets/script/jquery-1.11.1.min.js'></script>"
                    + "<script type='text/javascript' src='https://canvasjs.com/assets/script/jquery.canvasjs.min.js'></script></div>"
                    + "</div>");
        %>
    </body>
</html>