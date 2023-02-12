<%-- 
    Document   : adminaction
    Created on : 13 May, 2021, 2:20:32 PM
    Author     : ASUS
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <script src="jsscript/result.js"></script>
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
            String userid=(String)session.getAttribute("adhar_no");
            if(userid==null)
            {
                response.sendRedirect("accessdenied.html");
                return;
            }
            out.println("<div class='sticky'><div class='candidate'>VOTE FOR CHANGE</div><br>"+
        "<div class='subcandidate'>Results Page</div><br><br>"+
                    "<div class='logout'><a href='LoginControllerServlet?logout=logout'>logout</a></div></div>"+
        "<div class='container'>"+
            "<div id='dv1' onclick='resultbycity()'><img src='images/result.jpg' height='255px' width='225px'><br><h3>Result of Citys</h3></div>"+
            "<div id='dv2' onclick='resultbyparty()'><img src='images/img_by_party.png' height='250px' width='225px' style='background-color:black;'><br><h3>Result of Party</h3></div>"+
            "<br><br><div align='center' id='result'></div>"+
        "</div>");
        %>
 <script type='text/javascript' src='https://canvasjs.com/assets/script/jquery-1.11.1.min.js'></script>
 <script type='text/javascript' src='https://canvasjs.com/assets/script/jquery.canvasjs.min.js'></script>
    </body>
</html>