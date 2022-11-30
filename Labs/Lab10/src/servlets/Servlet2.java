import java.io.IOException;
import javax.servlet.*;
import javax.servlet.http.*;
import java.io.PrintWriter;
import java.sql.*;

public class Servlet2 extends HttpServlet implements Servlet {

    public void init() throws ServletException {
        super.init();
    }

    protected void service(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        System.out.println("Servlet2.java:service:" + request.getMethod());
        super.service(request, response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String id = request.getParameter("id");

        try {
            ServletContext servletContext = getServletContext();
            String url = servletContext.getInitParameter("Lab10ConnectionString");

            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");

            try (Connection connection = DriverManager.getConnection(url)) {
                CallableStatement statement = connection.prepareCall("{call GET_TEACHER_NAME(?,?)}");

                statement.setString(1, id);
                statement.registerOutParameter(2, Types.VARCHAR);

                statement.execute();

                response.setContentType("text/html; charset=UTF-8");
                response.setCharacterEncoding("UTF-8");
                PrintWriter out = response.getWriter();
                out.println("Teacher with id = " + id + " has Name = " + statement.getString(2));
            }
        }
        catch(Exception ex) {
            System.out.println("Connection failed...");
        }
    }
}