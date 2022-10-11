import java.io.IOException; // исключения ввода/вывода
import javax.servlet.*; // интерфейсы и классы общего типа
import javax.servlet.http.*; // расширение javax.servlet для http
import java.io.PrintWriter;

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
}