<?xml version="1.0" encoding="ISO-8859-1" ?>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ page import="javax.servlet.ServletContext" %>
<!doctype html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport"
          content="width=device-width, user-scalable=no, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="ie=edge">
    <title>Lab6</title>
</head>
<body>
    <h1>URL1 = <%=pageContext.getServletContext().getInitParameter("URL1")%><br/></h1>
    <h1>URL2 = <%=pageContext.getServletContext().getInitParameter("URL2")%><br/></h1>

    <a href="Servlet1">Servlet1</a><br>
    <a href="Ccc">Ccc</a>
</body>
</html>