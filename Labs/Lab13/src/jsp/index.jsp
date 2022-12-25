<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ page import="utils.*" %>
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
    <h1>Files</h1>
    <%
        String fileStoragePath = (String)getServletContext().getInitParameter("file-storage-path");
        FileNameList fileNameList = new FileNameList(fileStoragePath, "docx");
        String fileName = null;
        for (int i = 0; i < fileNameList.fileNames.length; i++) {
            fileName = fileNameList.fileNames[i];
    %>
        <br />
        <a href="Sss?fileName=<%=fileName%>">
            <%=fileName%>
        </a>
    <%}%>

</body>
</html>