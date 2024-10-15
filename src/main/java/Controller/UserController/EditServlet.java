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

@WebServlet("/EditServlet")
public class EditServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/html");
        PrintWriter printWriter = resp.getWriter();
        printWriter.println("<h1> Update User</h1>");

        int id = Integer.parseInt(req.getParameter("id"));

        User userbyID = UserDAO.getUserById(id);


        printWriter.println("<form action='EditServlet2' method = 'post'>");
        printWriter.println("<table>");
        printWriter.println("<tr><td></td><td> <input type ='hidden' name ='id' value='"+userbyID.getId()+"'/></td></tr>");
        printWriter.println("<tr><td>Name: </td><td><input type='text' name='name' value='"+userbyID.getName()+"'/></td></tr>");
        printWriter.println("<tr><td>Password: </td><td> <input type='password' name ='password' value = '"+userbyID.getPassword()+"'/> </td></tr>");
        printWriter.println("<tr><td>Email: </td><td><input type='email' name='email' value ='" +userbyID.getEmail()+"'/></td></tr>");
        printWriter.println("<tr><td> Country: </td> <td>");
        printWriter.println("<select name ='country' style = 'width:150px'>");
        printWriter.println("<option>India</option>");
        printWriter.println("<option>USA</option>");
        printWriter.println("<option>UK</option>");
        printWriter.println("<option>Other</option>");
        printWriter.println("</select>");
        printWriter.println("</td></tr>");
        printWriter.println("<tr><td colspan='2'> <input type='submit' value='Edit & Save'/></td></tr>");
        printWriter.println("</table> </form>");
        printWriter.close();





    }
}
