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


@WebServlet("/SaveServlet")
public class SaveServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {

            resp.setContentType("text/html");
            PrintWriter printWriter = resp.getWriter();

            String name = req.getParameter("name");
            String password = req.getParameter("password");
            String email = req.getParameter("email");
            String country = req.getParameter("country");

            User user = new User();
            user.setName(name);
            user.setPassword(password);
            user.setEmail(email);
            user.setCountry(country);
            int status = UserDAO.save(user);

            if (status>0){
                printWriter.println("<p> Record Saved successfully.</p>");
                req.getRequestDispatcher("index.html").include(req, resp);
            }else {
                printWriter.println("<p> Sorry! unable to saved record.");
                req.getRequestDispatcher("index.html").include(req, resp);
            }

            printWriter.close();



        } catch (Exception e) {
            e.printStackTrace();

        }


    }


}
