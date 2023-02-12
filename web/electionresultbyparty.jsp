<%-- 
    Document   : electionresultTest
    Created on : 12 Jun, 2021, 6:58:56 PM
    Author     : ASUS
--%>

<%@page import="org.json.JSONObject"%>
<%@page import="org.json.JSONArray"%>
<%@page import="java.lang.String"%>
<%@page import="java.util.Map"%>
<%@page import="java.util.Iterator"%>
<%@page import="java.util.LinkedHashMap"%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>

<%
    String userid = (String) session.getAttribute("adhar_no");
    if (userid == null) {
        response.sendRedirect("accessdenied.html");
        return;
    }
    LinkedHashMap<String, Integer> result = (LinkedHashMap<String, Integer>) request.getAttribute("result");
    JSONArray jsonarr = new JSONArray();
    Iterator it = result.entrySet().iterator();
    int totalVote = 0;
    while (it.hasNext()) {
        Map.Entry<String, Integer> en = (Map.Entry<String, Integer>) it.next();
        JSONObject obj = new JSONObject();
        obj.put("label", en.getKey());
        obj.put("y", en.getValue());
        jsonarr.put(obj);
    }
    StringBuilder displayBlock = new StringBuilder("");
    displayBlock.append("<div id='electionresult' style='height: 300px; width: 100%;'></div>");
//            + " <script type='text/javascript' src='https://canvasjs.com/assets/script/jquery-1.11.1.min.js'></script>"
//            + " <script type='text/javascript' src='https://canvasjs.com/assets/script/jquery.canvasjs.min.js'></script>");
    JSONObject obj = new JSONObject();
    obj.put("array", jsonarr);
    obj.put("result", displayBlock.toString());
    out.print(obj);
%>