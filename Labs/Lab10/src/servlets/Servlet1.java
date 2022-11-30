import java.io.IOException;
import javax.servlet.*;
import javax.servlet.http.*;
import java.io.PrintWriter;
import java.sql.*;
import java.util.LinkedList;

import beans.*;

public class Servlet1 extends HttpServlet implements Servlet {

    public void init() throws ServletException {
        super.init();
    }

    protected void service(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        System.out.println("Servlet1.java:service:" + request.getMethod());
        super.service(request, response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String pulpitDescriptor = request.getParameter("pulpit");
        System.out.println(pulpitDescriptor);

        try {
            ServletContext servletContext = getServletContext();
            String url = servletContext.getInitParameter("Lab10ConnectionString");

            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");

            try (Connection connection = DriverManager.getConnection(url)) {
                LinkedList<Teacher> teachers = this.GetTeachers(connection, pulpitDescriptor);

                request.setAttribute("teachers", teachers);

                RequestDispatcher requestDispatcher = request.getRequestDispatcher("/Teachers.jsp");
                requestDispatcher.forward(request, response);
            }
        }
        catch(Exception ex) {
            System.out.println("Connection failed...");
        }
    }

    private LinkedList<Teacher> GetTeachers(Connection connection, String pulpitDescriptor)
            throws IOException, SQLException {
        String query = "SELECT * FROM TEACHER";

        boolean isPulpitDescriptorSet = pulpitDescriptor != null && !pulpitDescriptor.isEmpty();
        if (isPulpitDescriptorSet) {
            query += " WHERE PULPIT LIKE ?";
        }

        PreparedStatement statement = connection.prepareStatement(query);

        if (isPulpitDescriptorSet) {
            statement.setString(1, "%" + pulpitDescriptor + "%");
        }

        ResultSet resultSet = statement.executeQuery();
        LinkedList<Teacher> teachers = new LinkedList<Teacher>();

        while (resultSet.next()) {
            String id = resultSet.getString("TEACHER");
            String name = resultSet.getString("TEACHER_NAME");
            String gender = resultSet.getString("GENDER");
            String pulpit = resultSet.getString("PULPIT");

            Teacher teacher = new Teacher(id, name, gender, pulpit);
            teachers.add(teacher);
        }

        return teachers;
    }
}