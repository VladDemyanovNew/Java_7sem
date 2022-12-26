package factories;

import java.sql.*;
import java.lang.ClassNotFoundException;

import javax.naming.Context;
import javax.naming.InitialContext;

public class SqlConnectionFactory {

    public static Connection createConnection() {
        try {
            Context env = (Context)new InitialContext().lookup("java:comp/env");
            String connectionString = (String)env.lookup("UWSRConnectionString");
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");

            return DriverManager.getConnection(connectionString);
        }
        catch (Exception error) {
            System.out.println("Connection failed...");
            return null;
        }
    }
}