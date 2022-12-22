<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<!doctype html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport"
          content="width=device-width, user-scalable=no, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="ie=edge">
    <title>Login</title>
</head>
<body>
    <form
        method="POST"
        action="j_security_check"
    >
        username: <input type="text" name="j_username" /><br>
        password: <input type="password" name="j_password" />
        <button type="submit">Login</button>
    </form>
</body>
</html>