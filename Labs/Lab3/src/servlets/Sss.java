import java.io.IOException; // исключения ввода/вывода
import javax.servlet.*; // интерфейсы и классы общего типа
import javax.servlet.http.*; // расширение javax.servlet для http
import java.io.PrintWriter;
import org.apache.commons.httpclient.*;
import org.apache.commons.httpclient.methods.*;

import helpers.*;

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

        String servletPath = request.getServletPath();
        if (request.getMethod().equalsIgnoreCase("POST") && servletPath == "/Sss/forward") {
            this.doPostForward(request, response);
            return;
        }
        if (request.getMethod().equalsIgnoreCase("POST") && servletPath == "/Sss/redirect") {
            this.doPostRedirect(request, response);
            return;
        }

        super.service(request, response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String servletPath = request.getServletPath();
        switch (servletPath) {
            case "/Sss/htmlForward":
                this.doGetHtmlForward(request, response);
                break;
            case "/Sss/doubleForward":
                this.doGetDoubleForward(request, response);
                break;
            case "/Sss/servletForward":
                this.doGetServletForward(request, response);
                break;
            case "/Sss/servletRedirect":
                this.doGetServletRedirect(request, response);
                break;
            case "/Sss/htmlRedirect":
                this.doGetHtmlRedirect(request, response);
                break;
            case "/Sss/doubleRedirect":
                this.doGetDoubleRedirect(request, response);
                break;
            case "/Sss/httpClient":
                this.doGetHttpClient(request, response);
                break;
        }
    }

    protected void doPostForward(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        System.out.println("Sss:doPost:forward:Ggg");

        RequestDispatcher requestDispatcher = request.getRequestDispatcher("/Ggg");
        requestDispatcher.forward(request, response);
    }

    protected void doPostRedirect(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        System.out.println("Sss:doPost:redirect:Ggg");

        String path = request.getContextPath() + "/Ggg";
        response.setStatus(307);
        response.setHeader("Location", path); // The sendRedirect is by default a HTTP 302 redirect
    }

    private void doGetServletForward(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        System.out.println("Sss:doGet:forward:Ggg");

        String userName = request.getParameter("userName");
        String userId = request.getParameter("userId");
        String params = "userName=" + userName + "&" + "userId=" + userId;

        RequestDispatcher requestDispatcher = request.getRequestDispatcher("/Ggg?" + params);
        requestDispatcher.forward(request, response);
    }

    private void doGetHtmlForward(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        System.out.println("Sss:doGet:forward:html");

        RequestDispatcher requestDispatcher = request.getRequestDispatcher("/test.html");
        requestDispatcher.forward(request, response);
    }

    private void doGetDoubleForward(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        System.out.println("Sss:doGet:doubleForward");

        RequestDispatcher requestDispatcher = request.getRequestDispatcher("/Ggg/doubleForward");
        requestDispatcher.forward(request, response);
    }

    private void doGetServletRedirect(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        System.out.println("Sss:doGet:redirect:Ggg");

        String userName = request.getParameter("userName");
        String userId = request.getParameter("userId");
        String params = "userName=" + userName + "&" + "userId=" + userId;

        String path = request.getContextPath() + "/Ggg?" + params;
        response.sendRedirect(path);
    }

    private void doGetHtmlRedirect(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        System.out.println("Sss:doGet:redirect:html");

        String path = request.getContextPath() + "/test.html";
        response.sendRedirect(path);
    }

    private void doGetDoubleRedirect(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        System.out.println("Sss:doGet:doubleRedirect");

        String path = request.getContextPath() + "/Ggg/doubleRedirect";
        response.sendRedirect(path);
    }

    protected void doGetHttpClient(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        System.out.println("Sss:doGet:HttpClient:Ggg");

        String path = request.getContextPath() + "/Ggg/httpClientResponse?message=hello";

        HttpClient httpClient = new HttpClient();
        GetMethod getMethod = new GetMethod("http://localhost:8080" + path);
        httpClient.executeMethod(getMethod);

        if (getMethod.getStatusCode() == HttpStatus.SC_OK) {
            InOutServletHelper.perform(response.getOutputStream(), getMethod.getResponseBodyAsStream());
        } else {
            System.out.println("Sss:doGet:HttpClient:Error:statuscode: " + getMethod.getStatusCode());
        }
    }
}