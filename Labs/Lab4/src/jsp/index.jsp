<%@ page language="java" contentType="text/html; charset=ISO-8859-1" %>
<%@ page import="java.time.*" %>
<%@ page import="java.time.format.*" %>
<%@ page import="enums.* "%>
<%@ page import="helpers.* "%>

<!DOCTYPE html>
<html lang="ru">
<head>
    <meta charset="UTF-8">
    <title>Lab4</title>
    <style type="text/css">
        table, th, td {
            border: 1px solid;
        }
    </style>
</head>
<body>
    <h1>Lab4</h1>
    <!-- JSP Expression -->
    <h2>Good <%= DateHelper.getDayPart().getString() %></h2>

    <hr>

    <table>
        <tr><th>Date</th><th>Week's number</th></tr>
        <!-- JSP Scriplet -->
        <%
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
            LocalDate currentDate = LocalDate.now();

            for (int i = 1; i < 8; i++) {
                int dayOfWeek = currentDate.getDayOfWeek().getValue();
                out.println(String.format("<tr><td>%s</td><td>%s</td></tr>", formatter.format(currentDate), dayOfWeek));
                currentDate = currentDate.plusDays(1);
            }
        %>
    </table>

    <hr>

    <form action="index.jsp" method="GET">
        <input type="text" name="isPress" hidden>
        <button type="submit">Press</button>
    </form>

    <!-- JSP Declaration -->
    <%! Boolean isPress, isForward; String queryString = null; %>
    <%
        queryString = request.getQueryString();
        isPress = queryString != null && queryString.contains("isPress");
        isForward = queryString != null && queryString.contains("isForward");
    %>

    <% if (isPress && DateHelper.getDayPart() == DayPart.MORNING) { %>
    <jsp:include page="morning.jsp" />
    <% } %>

    <% if (isPress && DateHelper.getDayPart() == DayPart.AFTERNOON) { %>
    <!-- JSP Directives -->
    <%@ include file="afternoon.jsp" %>
    <% } %>

    <% if (isPress && DateHelper.getDayPart() == DayPart.EVENING) { %>
    <%@ include file="evening.jsp" %>
    <% } %>

    <% if (isPress && DateHelper.getDayPart() == DayPart.NIGHT) { %>
    <%@ include file="night.jsp" %>
    <% } %>

    <hr>
    Action:Include:Jsp:
    <jsp:include page="morning.jsp" />

    <hr>
    Action:Include:Servlet:
    <jsp:include page="/Afternoon" />

    <hr>
    Action:Forward:Servlet:
    <form action="index.jsp" method="GET">
        <input type="text" name="isForward" hidden>
        <button type="submit">Activate forward action</button>
    </form>
    <% if (isForward) { %>
    <jsp:forward page="night.jsp" />
    <% } %>

    <hr>
    Forward:Jjj:Get:
    <a href="Jjj">Jjj:get</a><br>
    Forward:Jjj:Post:
    <form action="Jjj" method="POST">
        <button type="submit">Jjj:post</button>
    </form>

</body>
</html>