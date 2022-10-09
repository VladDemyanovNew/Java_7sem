import java.io.IOException; // исключения ввода/вывода
import javax.servlet.*; // интерфейсы и классы общего типа
import javax.servlet.http.*; // расширение javax.servlet для http
import java.io.PrintWriter;

public class Sss extends HttpServlet implements Servlet {

    public Sss() {
        super();
        System.out.println("Sss:constructor");
    }

    public void init(ServletConfig servletConfig) throws ServletException {
        super.init();
        System.out.println("Sss:init");
    }

    public void destroy() {
        super.destroy();
        System.out.println("Sss:destroy");
    }

    protected void service(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        System.out.println("Sss:service:" + request.getMethod());

        response.setContentType("text/html");
        PrintWriter out = response.getWriter();

        out.println("<html>");
        out.println("<head>");
        out.println("<title>Lab2</title>");
        out.println("</head>");
        out.println("<body>");
        out.println("<h1>Method: " + request.getMethod() + "</h1>");
        out.println("<h1>Server's name: " + request.getServerName() + "</h1>");
        out.println("<h1>IP: " + request.getLocalAddr() + "</h1>");
        out.println("</body>");
        out.println("</html>");

        super.service(request, response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html");
        PrintWriter out = response.getWriter();

        out.println("<html>");
        out.println("<head>");
        out.println("<title>Lab2</title>");
        out.println("</head>");
        out.println("<body>");
        out.println("<h1>firstname: " + request.getParameter("firstname") + "</h1>");
        out.println("<h1>lastname: " + request.getParameter("lastname") + "</h1>");
        out.println("<h1>url: " + request.getQueryString() + "</h1>");
        out.println("</body>");
        out.println("</html>");

        out.close();
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html");
        PrintWriter out = response.getWriter();

        out.println("<html>");
        out.println("<head>");
        out.println("<title>Lab2</title>");
        out.println("</head>");
        out.println("<body>");
        out.println("<h1>firstname: " + request.getParameter("firstname") + "</h1>");
        out.println("<h1>lastname: " + request.getParameter("lastname") + "</h1>");
        out.println("</body>");
        out.println("</html>");

        out.close();
    }
}