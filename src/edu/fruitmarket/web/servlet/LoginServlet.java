package edu.fruitmarket.web.servlet;

import edu.fruitmarket.web.service.UserService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "LoginServlet",urlPatterns = "/LoginServlet")
public class LoginServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");//访问用户的输入数据
        String name=request.getParameter("name").trim();
        String password=request.getParameter("password");
        //对验证码进行验证对比处理
        String code=request.getParameter("code").trim();
        //session.setAttribute("check_code", new String(rands));
        String saveCode=(String)request.getSession().getAttribute("check_code");//获得session对象，将setAttribute里存的check_code获取到

        //调用UserService里的验证方法
        UserService service=new UserService();
        if(saveCode.equals(code))
        {
            //对UserService的login方法返回值进行处理
            if(service.login(name,password).equals("1"))
            {
                //对登录成功进行处理，调用succpage.html
                response.sendRedirect("succpage.jsp");


            }else if(service.login(name,password).equals("2"))
            {
                request.setAttribute("msg","用户名或密码错误，请验证后重新输入！");
                request.getRequestDispatcher("errpage.jsp").forward(request,response);//将上面的错误信息发到这个页面

            }else if(service.login(name,password).equals("3"))
            {
                request.setAttribute("msg","数据库连接错误，请联系管理员！");
                request.getRequestDispatcher("errpage.jsp").forward(request,response);//将上面的错误信息发到这个页面

            }else if(service.login(name,password).equals("4"))
            {
                request.setAttribute("msg","数据库访问错误，请联系管理员！");
                request.getRequestDispatcher("errpage.jsp").forward(request,response);//将上面的错误信息发到这个页面

            }else if(service.login(name,password).equals("5"))
            {
                request.setAttribute("msg","用户名不能为空，请重新输入！");
                request.getRequestDispatcher("errpage.jsp").forward(request,response);//将上面的错误信息发到这个页面

            }
        }else
        {
            request.setAttribute("msg","验证码错误，请检查后重新输入！");
            request.getRequestDispatcher("errpage.jsp").forward(request,response);//将上面的错误信息发到这个页面


        }
        service.login(name,password);



    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request,response);
    }
}
