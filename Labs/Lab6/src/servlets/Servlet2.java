import java.io.IOException; // исключения ввода/вывода
import javax.servlet.*; // интерфейсы и классы общего типа
import javax.servlet.http.*; // расширение javax.servlet для http
import java.io.PrintWriter;

public class Servlet2 extends HttpServlet implements Servlet {

    public Servlet2() {
        super();
        System.out.println("Servlet2:constructor");
    }

    public void destroy() {
        super.destroy();
        System.out.println("Servlet2:destroy");
    }

    protected void service(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        System.out.println("Servlet2:service:" + request.getMethod());

        super.service(request, response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html");
        PrintWriter out = response.getWriter();
        out.println("<h1>URL2 - Servlet2</h1>");
        out.flush();
    }
}