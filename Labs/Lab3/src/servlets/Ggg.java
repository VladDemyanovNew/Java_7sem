import java.io.IOException; // исключения ввода/вывода
import javax.servlet.*; // интерфейсы и классы общего типа
import javax.servlet.http.*; // расширение javax.servlet для http
import java.io.PrintWriter;

import helpers.*;

public class Ggg extends HttpServlet implements Servlet {

    public Ggg() {
        super();
        System.out.println("Ggg:constructor");
    }

    public void init(ServletConfig servletConfig) throws ServletException {
        super.init();
        System.out.println("Ggg:init");
    }

    public void destroy() {
        super.destroy();
        System.out.println("Ggg:destroy");
    }

    protected void service(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        System.out.println("Ggg:service:" + request.getMethod());
        super.service(request, response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String servletPath = request.getServletPath();
        switch (servletPath) {
            case "/Ggg/doubleForward":
                this.handleGetDoubleForward(request, response);
                break;
            case "/Ggg/httpClientResponse":
                this.handleHttpClient(request, response);
                break;
            default:
                this.handleGetForward(request, response);
        }
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        System.out.println("Ggg:doPost");

        response.setContentType("text/html");

        String params = request.getParameter("age");

        PrintWriter out = response.getWriter();
        out.println(HtmlHelper.generateHtmlPage("<h1>Ggg:doPost: " + params + "</h1>"));
    }

    private void handleGetDoubleForward(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        System.out.println("Ggg:doGet:forward:html");

        RequestDispatcher requestDispatcher = request.getRequestDispatcher("/test.html");
        requestDispatcher.forward(request, response);
    }

    private void handleGetForward(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        System.out.println("Ggg:doGet");

        String userName = request.getParameter("userName");
        String userId = request.getParameter("userId");
        String params = "userName=" + userName + "&" + "userId=" + userId;

        response.setContentType("text/html");

        PrintWriter out = response.getWriter();
        out.println(HtmlHelper.generateHtmlPage("<h1>Ggg:doGet: " + params + "</h1>"));
    }

    private void handleHttpClient(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        System.out.println("Ggg:doGet:handleHttpClient");

        String message = request.getParameter("message");

        response.setContentType("text/html");

        PrintWriter out = response.getWriter();
        out.println(HtmlHelper.generateHtmlPage("<h1>Ggg:HttpClient:message " + message + "</h1>"));
        out.flush();
    }
}