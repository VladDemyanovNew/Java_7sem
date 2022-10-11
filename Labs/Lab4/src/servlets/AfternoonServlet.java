import java.io.IOException; // исключения ввода/вывода
import javax.servlet.*; // интерфейсы и классы общего типа
import javax.servlet.http.*; // расширение javax.servlet для http
import java.io.PrintWriter;

import enums.*;

public class AfternoonServlet extends HttpServlet implements Servlet {

    public AfternoonServlet() {
        super();
        System.out.println("AfternoonServlet:constructor");
    }

    public void init(ServletConfig servletConfig) throws ServletException {
        super.init();
        System.out.println("AfternoonServlet:init");
    }

    public void destroy() {
        super.destroy();
        System.out.println("AfternoonServlet:destroy");
    }

    protected void service(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        System.out.println("AfternoonServlet:service:" + request.getMethod());

        response.setContentType("text/html");
        PrintWriter out = response.getWriter();

        out.println("<h1>Good " + DayPart.AFTERNOON.getString() + "</h1>");
    }
}