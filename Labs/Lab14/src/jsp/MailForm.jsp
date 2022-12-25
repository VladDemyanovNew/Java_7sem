<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<!doctype html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport"
          content="width=device-width, user-scalable=no, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="ie=edge">
    <title>Files</title>
</head>
<body>
    <h1>Mail Form</h1>
    <form action="MailServlet" method="POST">
        <input type="email" name="mail" placeholder="Mail" required><br>
        <textarea type="text" name="message" placeholder="Message" required></textarea><br>
        <button type="submit">Send</button>
    </form>

    <br>
    <a href="MailServlet">All system email' mails</a>
</body>
</html>