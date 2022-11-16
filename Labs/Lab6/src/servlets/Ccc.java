import java.io.IOException; // исключения ввода/вывода
import javax.servlet.*; // интерфейсы и классы общего типа
import javax.servlet.http.*; // расширение javax.servlet для http
import java.io.PrintWriter;

import beans.*;

public class Ccc extends HttpServlet implements Servlet {

    public void init() throws ServletException {
        super.init();
        ServletContext servletContext = getServletContext();
        servletContext.setAttribute("atrCBean", new CBean());
    }

    protected void service(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        System.out.println("Ccc.java:service:" + request.getMethod());

        super.service(request, response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        RequestDispatcher requestDispatcher = request.getRequestDispatcher("/Ccc.jsp");
        requestDispatcher.forward(request, response);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String paramValue1 = request.getParameter("value1");
        String paramValue2 = request.getParameter("value2");
        String paramValue3 = request.getParameter("value3");
        String paramCbean = request.getParameter("cbean");

        ServletContext servletContext = getServletContext();
        CBean cBean = (CBean)servletContext.getAttribute("atrCBean");

        if (paramCbean != null && paramCbean.equals("new")) {
            servletContext.setAttribute("atrCBean", new CBean());
        }

        System.out.println(paramValue1 != "");

        if (paramValue1 != "") {
            cBean.setValue1(paramValue1);
        }

        if (paramValue2 != "") {
            cBean.setValue2(paramValue2);
        }

        if (paramValue3 != "") {
            cBean.setValue3(paramValue3);
        }

        RequestDispatcher requestDispatcher = request.getRequestDispatcher("/Ccc.jsp");
        requestDispatcher.forward(request, response);
    }
}