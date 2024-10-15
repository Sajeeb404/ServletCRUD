package Controller.UserController;

import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.FileInputStream;
import java.io.IOException;

@WebServlet("/image1")
public class DisplayImage extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        resp.setContentType("image/jpeg");
        ServletOutputStream servletOutputStream;
        servletOutputStream = resp.getOutputStream();

        FileInputStream fileInputStream = new FileInputStream("C:\\Users\\Admin\\Desktop\\ss\\Photo.jpg");

        BufferedInputStream bufferedInputStream = new BufferedInputStream(fileInputStream);
        BufferedOutputStream bufferedOutputStream = new BufferedOutputStream(servletOutputStream);
        int ch = 0;

        while ((ch=bufferedInputStream.read())!=-1) {

            bufferedOutputStream.write(ch);

        }
        bufferedInputStream.close();
        fileInputStream.close();
        bufferedOutputStream.close();
        servletOutputStream.close();




    }
}
