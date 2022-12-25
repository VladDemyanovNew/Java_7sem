<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ page import="dtos.*" %>
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
        MessageDto message = (MessageDto)request.getAttribute("message");
    %>

    <b>From:</b> <%= message.getFrom()%><br>
    <b>Subject:</b> <%= message.getSubject()%><br>
    <b>Sent Date:</b> <%= message.getSentDate()%><br>
    <b>Message Number:</b> <%= message.getMessageNumber()%><br>
    <b>Content:</b> <%= message.getContent()%><br>
</body>
</html>