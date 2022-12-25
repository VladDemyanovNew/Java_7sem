<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ page import="javax.mail.*" %>
<%@ page import="javax.mail.internet.InternetAddress" %>
<%@ page import="javax.mail.internet.MimeMessage" %>
<%@ page import="java.util.*" %>
<%@ page import="dtos.*" %>
<!doctype html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport"
          content="width=device-width, user-scalable=no, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="ie=edge">
    <title>Mails</title>
    <style type="text/css">
        table, th, td {
            border: 1px solid black;
        }
    </style>
</head>
<body>
    <h1>All system account' mails</h1>

    <%
        LinkedList<MessageDto> messages = (LinkedList<MessageDto>)request.getAttribute("messages");
    %>

    <table>
        <tr>
            <th>From</th>
            <th>Subject</th>
            <th>Sent Date</th>
            <th>Message Number</th>
        </tr>
        <%
            for (MessageDto message: messages) {
                int messageNumber = message.getMessageNumber();
                String messageLink = "<a href=\"MailServlet?messageNumber=" + messageNumber + "\">" + messageNumber + "</a>";
                out.println("<tr>");
                out.println("<td>" + message.getFrom() + "</td><td>" + message.getSubject() + "</td><td>" + message.getSentDate() + "</td><td>" + messageLink + "</td>");
                out.println("<tr>");
            }
        %>
    </table>
</body>
</html>