import java.io.IOException; // исключения ввода/вывода
import javax.servlet.*; // интерфейсы и классы общего типа
import javax.servlet.http.*; // расширение javax.servlet для http
import java.io.PrintWriter;

public class Ttt extends HttpServlet implements Servlet {

    public Ttt() {
        super();
        System.out.println("Ttt:constructor");
    }

    public void init(ServletConfig servletConfig) throws ServletException {
        super.init();
        System.out.println("Ttt:init");
    }

    public void destroy() {
        super.destroy();
        System.out.println("Ttt:destroy");
    }

    protected void service(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        System.out.println("Ttt:service:" + request.getMethod());

        super.service(request, response);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html");
        PrintWriter out = response.getWriter();

        String surnameStr = "Surname: " + request.getParameter("surname");
        String lastnameStr = "; Lastname: " + request.getParameter("lastname");
        String sexStr = "; Sex: " + request.getParameter("sex");

        out.println(surnameStr + lastnameStr + sexStr);

        out.close();
    }
}