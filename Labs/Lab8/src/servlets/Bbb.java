import java.io.IOException; // исключения ввода/вывода
import javax.servlet.*; // интерфейсы и классы общего типа
import javax.servlet.http.*; // расширение javax.servlet для http
import java.io.PrintWriter;
import java.util.*;

public class Bbb extends HttpServlet implements Servlet {

    public void init() throws ServletException {
        super.init();
    }

    protected void service(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        System.out.println("Bbb.java:service:" + request.getMethod());

        super.service(request, response);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html");

        StringBuilder responseBody = new StringBuilder();

        Enumeration headers = request.getHeaderNames();
        while (headers.hasMoreElements()) {
            String headerName = (String)headers.nextElement();
            String headerValue = request.getHeader(headerName);

            responseBody.append(headerName);
            responseBody.append("=");
            responseBody.append(headerValue);
            responseBody.append("<br>");
        }

        responseBody.append("<br>");

        Enumeration parameters = request.getParameterNames();
        while (parameters.hasMoreElements()) {
            String parameterName = (String)parameters.nextElement();
            String parameterValue = request.getParameter(parameterName);

            responseBody.append(parameterName);
            responseBody.append("=");
            responseBody.append(parameterValue);
            responseBody.append("<br>");
        }

        response.addHeader("X-BbbHeader1", "BbbHeaderValue1");
        response.addHeader("X-BbbHeader2", "BbbHeaderValue2");

        PrintWriter out = response.getWriter();
        out.println(responseBody.toString());
    }
}