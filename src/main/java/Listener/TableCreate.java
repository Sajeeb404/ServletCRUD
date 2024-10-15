package Listener;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;
import java.sql.Connection;
import java.sql.PreparedStatement;

@WebListener
public class TableCreate implements ServletContextListener {

    Connection connection;

    @Override
    public void contextInitialized(ServletContextEvent sce) {
        try {
            connection = (Connection) sce.getServletContext().getAttribute("connection");
            String createTableSQL = "CREATE TABLE IF NOT EXISTS users (\n" +
                    "    id INT AUTO_INCREMENT PRIMARY KEY,\n" +
                    "    name VARCHAR(100) NOT NULL,\n" +
                    "    password VARCHAR(255) NOT NULL,\n" +
                    "    email VARCHAR(100) NOT NULL UNIQUE,\n" +
                    "    country VARCHAR(100) NOT NULL\n" +
                    ");";
            try (PreparedStatement preparedStatement = connection.prepareStatement(createTableSQL)){
                int status = preparedStatement.executeUpdate();
            if (status>0){
                System.out.println("Table Created successfully.");
            }else {
                System.out.println("Table does't Created.");

            }

            }


        } catch (Exception e) {
            e.printStackTrace();

        }


    }

    @Override
    public void contextDestroyed(ServletContextEvent sce) {


    }
}
