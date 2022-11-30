<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ page import="beans.Teacher" %>
<%@ page import="java.util.LinkedList" %>
<!doctype html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport"
          content="width=device-width, user-scalable=no, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="ie=edge">
    <title>Teachers</title>
    <style type="text/css">
        table, th, td {
            border: 1px solid black;
        }
    </style>
</head>
<body>
<%
    LinkedList<Teacher> teachers = (LinkedList<Teacher>) request.getAttribute("teachers");
%>
<h1>Преподаватели</h1>

<form action="Servlet1" method="get">
    Кафедра: <input type="text" name="pulpit">
    <button type="submit">Применить</button>
    <br><br>
</form>

<table>
    <tr>
        <th>Идентификатор</th>
        <th>ФИО</th>
        <th>Пол</th>
        <th>Кафедра</th>
    </tr>
<%
    for (Teacher teacher : teachers) {
        out.println("<tr>");
        out.println("<td>" + teacher.getId() + "</td><td>" + teacher.getName() + "</td><td>" + teacher.getGender() + "</td><td>" + teacher.getPulpit() + "</td>");
        out.println("<tr>");
    }
%>
</table>
</body>
</html>