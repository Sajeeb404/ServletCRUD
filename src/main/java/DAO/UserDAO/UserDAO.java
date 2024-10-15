package DAO.UserDAO;

import Entity.UserEntity.User;

import javax.servlet.ServletContext;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class UserDAO {
   private static ServletContext servletContext;

    public static void init(ServletContext context) {
        servletContext = context;
    }

    public static int save(User user){

        int status =0;
        Connection connection;
        try {
            connection = (Connection) servletContext.getAttribute("connection");
            if (connection == null || connection.isClosed()) {
                throw new IllegalStateException("Connection is closed or null.");
            }

            String inserQuery = "insert into users (name, email, password, country) values (?, ?, ?, ?)";
        try (PreparedStatement preparedStatement = connection.prepareStatement(inserQuery)) {
            preparedStatement.setString(1,user.getName());
            preparedStatement.setString(2,user.getEmail());
            preparedStatement.setString(3,user.getPassword());
            preparedStatement.setString(4,user.getCountry());
            status = preparedStatement.executeUpdate();
        }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return status;

    }




    public static List<User> getUsers(){

        List<User> usersList = new ArrayList<>();
        Connection connection;
        String sql = "select * from users";
        try {
            connection = (Connection) servletContext.getAttribute("connection");
            if (connection == null || connection.isClosed()) {
                throw new IllegalStateException("Connection is closed or null.");
            }

            try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {

                ResultSet resultSet = preparedStatement.executeQuery();
                while (resultSet.next()) {
                    User user = new User();
                    user.setId(resultSet.getInt(1));
                    user.setName(resultSet.getString(2));
                    user.setPassword(resultSet.getString(3));
                    user.setEmail(resultSet.getString(4));
                    user.setCountry(resultSet.getString(5));
                    usersList.add(user);

                }

            }


        } catch (Exception e) {
            e.printStackTrace();

        }


        return usersList;

    }




    public static int delete(int id){

        int status =0;

        Connection connection;
        String sql ="delete from users where id = ?";

        try {
            connection = (Connection) servletContext.getAttribute("connection");
            if (connection==null || connection.isClosed()) {
                throw new IllegalStateException("Connection is closed or null.");
            }

            try(PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
                preparedStatement.setInt(1, id);
                status = preparedStatement.executeUpdate();
            }


        } catch (Exception e) {
            e.printStackTrace();

        }


        return status;
    }


    public static User getUserById(int id) {

        User user = new User();

        Connection connection;
        String sql = "SELECT * FROM `users` WHERE id = ?";

        try {
            connection = (Connection) servletContext.getAttribute("connection");
            if (connection==null|| connection.isClosed()) {
                throw new IllegalStateException("Connection is null or is closed.");

            }

            try (PreparedStatement preparedStatement =connection.prepareStatement(sql)){
                preparedStatement.setInt(1, id);
                ResultSet resultSet = preparedStatement.executeQuery();

                while (resultSet.next()) {
                    user.setId(resultSet.getInt(1));
                    user.setName(resultSet.getString(2));
                    user.setPassword(resultSet.getString(3));
                    user.setEmail(resultSet.getString(4));
                    user.setCountry(resultSet.getString(5));
                }



            }
        } catch (Exception e) {
            e.printStackTrace();
        }


        return user;
    }


    public static int update(User user) {
        int status = 0;
        Connection connection;
        String sql ="update users set name=?, password=?, email=?, country=? where id = ?";

        try {
            connection = (Connection) servletContext.getAttribute("connection");
            if (connection==null|| connection.isClosed()) {
                throw new IllegalStateException("Connection is null or is closed.");

            }

            try (PreparedStatement preparedStatement = connection.prepareStatement(sql)){

                preparedStatement.setString(1, user.getName());
                preparedStatement.setString(2,user.getPassword());
                preparedStatement.setString(3, user.getEmail());
                preparedStatement.setString(4, user.getCountry());
                preparedStatement.setInt(5, user.getId());
                status = preparedStatement.executeUpdate();
            }
        } catch (Exception e) {
            e.printStackTrace();

        }


        return status;


    }


}
