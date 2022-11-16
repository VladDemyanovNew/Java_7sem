package listeners;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

public class L1 implements ServletContextListener {

    public void contextInitialized(ServletContextEvent servletContextEvent) {
        System.out.println("Web-App Context initialized");
    }

    public void contextDestroyed(ServletContextEvent servletContextEvent) {
        System.out.println("Web-App Context destroyed");
    }
}