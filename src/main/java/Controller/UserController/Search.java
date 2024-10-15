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

@WebServlet("/Search1")
public class Search extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        resp.setContentType("text/html");
        PrintWriter printWriter = resp.getWriter();
        int id = Integer.parseInt(req.getParameter("id"));

        User userByID = UserDAO.getUserById(id);
        System.out.println("hello is id ---------"+ userByID.getId());
        System.out.println("hello is id ---------"+ userByID);

        if (userByID.getId() != 0) {

        printWriter.println("<table width=50% border=1>");
        printWriter.println("<caption>Result</caption>");
        printWriter.println("<tr><th>Name</th>");
        printWriter.println("<th>Password</th>");
        printWriter.println("<th>Eamil</th>");
        printWriter.println("<th>Country</th></tr>");

        printWriter.println("<tr><td>"+userByID.getName()+"</td>");
        printWriter.println("<td>"+userByID.getPassword()+"</td>");
        printWriter.println("<td>"+userByID.getEmail()+"</td>");
        printWriter.println("<td>"+userByID.getCountry()+"</td></tr></table>");
        }else {

            printWriter.println("<h2> This id "+ id+" is empty</h2>");

        }





        printWriter.close();

    }
}
