import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;

public class Sss extends HttpServlet implements Servlet {

    public void init() throws ServletException {
        System.out.println("Servlet:Sss");
        super.init();
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        System.out.println("Servlet:Sss:doGet");
        PrintWriter out = response.getWriter();
        out.println("Servlet Sss works!");
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        PrintWriter out = response.getWriter();
        out.println("Servlet Sss works!");
    }
}