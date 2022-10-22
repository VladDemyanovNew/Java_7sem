import java.io.IOException; // исключения ввода/вывода
import javax.servlet.*; // интерфейсы и классы общего типа
import javax.servlet.http.*; // расширение javax.servlet для http
import java.io.PrintWriter;
import org.apache.commons.httpclient.*;
import org.apache.commons.httpclient.methods.*;

import enums.*;
import helpers.*;

public class Jjj extends HttpServlet implements Servlet {

    public Jjj() {
        super();
        System.out.println("Jjj:constructor");
    }

    public void init(ServletConfig servletConfig) throws ServletException {
        super.init();
        System.out.println("Jjj:init");
    }

    public void destroy() {
        super.destroy();
        System.out.println("Jjj:destroy");
    }

    protected void service(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        System.out.println("Jjj:service:" + request.getMethod());

        super.service(request, response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        System.out.println("Jjj:doGet:forward:jsp");

        // this.sendDayPart(request, response);
        this.sendDayPartHttpClient(request, response);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        System.out.println("Jjj:doPost:forward:jsp");

        // this.sendDayPart(request, response);
        this.sendDayPartHttpClient(request, response);
    }

    private void sendDayPart(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        RequestDispatcher requestDispatcher = null;

        DayPart dayPart = DateHelper.getDayPart();
        switch (dayPart) {
            case MORNING:
                requestDispatcher = request.getRequestDispatcher("morning.jsp");
                break;
            case AFTERNOON:
                requestDispatcher = request.getRequestDispatcher("afternoon.jsp");
                break;
            case EVENING:
                requestDispatcher = request.getRequestDispatcher("evening.jsp");
                break;
            default:
                requestDispatcher = request.getRequestDispatcher("night.jsp");
        }
        requestDispatcher.forward(request, response);
    }

    private void sendDayPartHttpClient(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String path = request.getContextPath() + "/";

        DayPart dayPart = DateHelper.getDayPart();
        switch (dayPart) {
            case MORNING:
                path += "morning.jsp";
                break;
            case AFTERNOON:
                path += "afternoon.jsp";
                break;
            case EVENING:
                path += "evening.jsp";
                break;
            default:
                path += "night.jsp";
        }

        HttpClient httpClient = new HttpClient();
        GetMethod getMethod = new GetMethod("http://localhost:8080" + path);
        httpClient.executeMethod(getMethod);

        if (getMethod.getStatusCode() == HttpStatus.SC_OK) {
            InOutServletHelper.perform(response.getOutputStream(), getMethod.getResponseBodyAsStream());
        } else {
            System.out.println("Jjj:doGet:HttpClient:Error:statuscode: " + getMethod.getStatusCode());
        }
    }
}