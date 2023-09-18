package edu.fruitmarket.web.servlet;

import edu.fruitmarket.web.beans.User;
import edu.fruitmarket.web.service.UserService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "PassServlet",urlPatterns = "/PassServlet")
public class PassServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        UserService service=new UserService();

        String username = "admin";
        String password=request.getParameter("mpass");
        String newpass=request.getParameter("newpass");
        String renewpass=request.getParameter("renewpass");

        try {
            User user = new User("admin",newpass);
            System.out.println(user.getUsername());

            if(service.updateUser(user)) {
                request.setAttribute("msg","密码修改成功");
                request.getRequestDispatcher("msg.jsp").forward(request,response);
            }
            else{
                request.setAttribute("msg","旧密码错误，无法修改");
                request.getRequestDispatcher("fail.jsp").forward(request,response);
            }

        } catch (NumberFormatException e) {
            request.setAttribute("msg","用户密码格式错误，请重新输入");
            request.getRequestDispatcher("fail.jsp").forward(request,response);
        }










    }
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        this.doPost(request, response);
    }


}
