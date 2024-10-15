package Listener;

import DAO.UserDAO.UserDAO;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;
import java.sql.Connection;
import java.sql.DriverManager;

@WebListener
public class DatabaseConnectionListener implements ServletContextListener {

    @Override
    public void contextInitialized(ServletContextEvent sce) {

        ServletContext servletContext = sce.getServletContext();
        String driver= servletContext.getInitParameter("Driver");
        String dbURL = servletContext.getInitParameter("databaseURL");
        String dbUser =servletContext.getInitParameter("dbUserName");
        String dbPass = servletContext.getInitParameter("dbPassword");

        try {
            Class.forName(driver);
            Connection connection = DriverManager.getConnection(dbURL, dbUser, dbPass);
            servletContext.setAttribute("connection", connection);

            UserDAO.init(servletContext);
        } catch (Exception e) {
            e.printStackTrace();

        }


    }

    @Override
    public void contextDestroyed(ServletContextEvent sce) {
        Connection connection = (Connection) sce.getServletContext().getAttribute("connection");

        if (connection != null) {
            try {
                connection.close();
            } catch (Exception e) {
                e.printStackTrace();

            }


        }


    }
}
