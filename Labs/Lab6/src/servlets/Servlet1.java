import java.io.IOException; // исключения ввода/вывода
import javax.servlet.*; // интерфейсы и классы общего типа
import javax.servlet.http.*; // расширение javax.servlet для http
import java.io.PrintWriter;
import javax.servlet.ServletContext;
import org.apache.commons.httpclient.*;
import org.apache.commons.httpclient.methods.*;

public class Servlet1 extends HttpServlet implements Servlet {

    public Servlet1() {
        super();
        System.out.println("Servlet1.java:constructor");
    }

    public void destroy() {
        super.destroy();
        System.out.println("Servlet1.java:destroy");
    }

    protected void service(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        System.out.println("Servlet1.java:service:" + request.getMethod());

        super.service(request, response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html");

        PrintWriter out = response.getWriter();

        String urln = "URL" + request.getParameter("urln");
        ServletContext servletContext = getServletContext();

        String initParameter = servletContext.getInitParameter(urln);
        if (initParameter == null) {

            out.println("<h1>Parameter " + urln + " not found</h1>");
            out.flush();
            return;
        }

        String path = request.getContextPath() + "/" + initParameter;

        HttpClient httpClient = new HttpClient();
        GetMethod methodGet = new GetMethod("http://localhost:8080" + path);
        httpClient.executeMethod(methodGet);

        out.println(methodGet.getResponseBodyAsString());
        out.flush();
    }
}