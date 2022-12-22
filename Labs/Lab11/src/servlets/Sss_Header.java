import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;

public class Sss_Header extends HttpServlet implements Servlet {

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            System.out.println("Sss_Header:doPost start");

            Float x = new Float(request.getHeader("Value-X"));
            Float y = new Float(request.getHeader("Value-Y"));
            Float z = x + y;

            response.setHeader("Value-Z", z.toString());

            Thread.currentThread().sleep(10000);
            System.out.println("Sss_Header:doPost end");
        } catch (Exception error) {
            response.getWriter().println(error.getMessage());
        }
    }
}