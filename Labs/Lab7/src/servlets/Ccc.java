import java.io.IOException; // исключения ввода/вывода
import javax.servlet.*; // интерфейсы и классы общего типа
import javax.servlet.http.*; // расширение javax.servlet для http
import java.io.PrintWriter;

import beans.*;

public class Ccc extends HttpServlet implements Servlet {

    public void init() throws ServletException {
        super.init();
    }

    protected void service(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        System.out.println("Ccc.java:service:" + request.getMethod());

        HttpSession session = request.getSession();
        String sessionId = session.getId();

        CBean cbeanFromSession = (CBean) session.getAttribute(sessionId);
        if (cbeanFromSession == null) {
            session.setAttribute(sessionId, new CBean());
        }

        CBean cbeanFromRequest = (CBean) request.getAttribute("atrCBean");
        if (cbeanFromRequest == null) {
            request.setAttribute("atrCBean", new CBean());
        }

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

        HttpSession session = request.getSession();
        String sessionId = session.getId();

        CBean cbeanFromSession = (CBean) session.getAttribute(sessionId);
        CBean cbeanFromRequest = new CBean();

        if (paramCbean != null && paramCbean.equals("new")) {
            session.removeAttribute(sessionId);
            session.setAttribute(sessionId, new CBean());
            request.setAttribute("atrCBean", new CBean());
        }

        if (paramValue1 != "") {
            cbeanFromSession.setValue1(paramValue1);
            cbeanFromRequest.setValue1(paramValue1);
        }

        if (paramValue2 != "") {
            cbeanFromSession.setValue2(paramValue2);
            cbeanFromRequest.setValue2(paramValue2);
        }

        if (paramValue3 != "") {
            cbeanFromSession.setValue3(paramValue3);
            cbeanFromRequest.setValue3(paramValue3);
        }

        session.setAttribute(sessionId, cbeanFromSession);
        request.setAttribute("atrCBean", cbeanFromRequest);

        RequestDispatcher requestDispatcher = request.getRequestDispatcher("/Ccc.jsp");
        requestDispatcher.forward(request, response);
    }
}