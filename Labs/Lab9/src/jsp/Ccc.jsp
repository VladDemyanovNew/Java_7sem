<?xml version="1.0" encoding="ISO-8859-1" ?>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ page import="javax.servlet.ServletContext" %>
<%@ page import="beans.CBean" %>
<!doctype html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport"
          content="width=device-width, user-scalable=no, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="ie=edge">
    <title>Document</title>
</head>
<body>
<%
    CBean cBeanFromRequest = (CBean) request.getAttribute("atrCBean");

    String sessionId = request
            .getSession()
            .getId();
    CBean cBeanFromSession = (CBean) request
            .getSession()
            .getAttribute(sessionId);
%>

<h1>Request:</h1>
<h1>Value1 = <%= cBeanFromRequest.getValue1()%></h1>
<h1>Value2 = <%= cBeanFromRequest.getValue2()%></h1>
<h1>Value3 = <%= cBeanFromRequest.getValue3()%></h1>

<hr>
<h1>Session:</h1>
<h1>Value1 = <%= cBeanFromSession.getValue1()%></h1>
<h1>Value2 = <%= cBeanFromSession.getValue2()%></h1>
<h1>Value3 = <%= cBeanFromSession.getValue3()%></h1>

<form method="POST" action="Ccc">
    Value1: <input type="text" name="value1"><br>
    Value2: <input type="text" name="value2"><br>
    Value3: <input type="text" name="value3"><br>
    CBean: <input type="text" name="cbean"><br>
    <button type="submit">Set</button>
</form>
</body>
</html>