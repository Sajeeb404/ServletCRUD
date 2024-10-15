package Controller.UserController;


import DAO.UserDAO.UserDAO;
import Entity.UserEntity.User;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

@WebServlet("/ViewServlet")
public class ViewServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        resp.setContentType("text/html");
        PrintWriter printWriter = resp.getWriter();
        printWriter.println("<a href='index.html'>Add User</a>");
        printWriter.println("<h1> User List </h1>");

        List<User> list = UserDAO.getUsers();

        printWriter.println("<table border='1'> <tr>");
        printWriter.println("<th>ID</th> <th>Name</th> <th>Password</th> <th> Email</th> <th> Country</th> <th> Edit</th> <th>Delete</th> </tr>");

        for (User ulist  : list) {
            printWriter.println("<tr>");
            printWriter.println("<td>");
            printWriter.println(ulist.getId());
            printWriter.println("</td>");

            printWriter.println("<td>");
            printWriter.println(ulist.getName());
            printWriter.println("</td>");

            printWriter.println("<td>");
            printWriter.println(ulist.getPassword());
            printWriter.println("</td>");

            printWriter.println("<td>");
            printWriter.println(ulist.getEmail());
            printWriter.println("</td>");

            printWriter.println("<td>");
            printWriter.println(ulist.getCountry());
            printWriter.println("</td>");

            printWriter.println("<td>");
            printWriter.println("<a href='EditServlet?id="+ ulist.getId() +"'> Edit</a>");
            printWriter.println("</td>");


            // Delete link
            printWriter.println("<td>");
            printWriter.println("<a href='DeleteServlet?id=" + ulist.getId() + "'> Delete</a>");
            printWriter.println("</td>");

            printWriter.println("</tr>");

        }





    }
}
