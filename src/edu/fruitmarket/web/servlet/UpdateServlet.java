package edu.fruitmarket.web.servlet;

import edu.fruitmarket.web.beans.Fruit;
import edu.fruitmarket.web.service.FruitService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "UpdateServlet",urlPatterns = "/UpdateServlet")
public class UpdateServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        FruitService service=new FruitService();

        String number=request.getParameter("number").trim();
        String  name=request.getParameter("name").trim();
        String  price=request.getParameter("price").trim();
        String unit=request.getParameter("unit").trim();

        try {
            Fruit fruit = new Fruit(number, name, Double.parseDouble(price), unit);
            if(service.updateFruit(fruit))
            {
                request.setAttribute("msg","修改成功！");
                request.getRequestDispatcher("msg.jsp").forward(request,response);
            }
            else
            {
                request.setAttribute("msg","水果信息不存在，无法修改！");
                request.getRequestDispatcher("fail.jsp").forward(request,response);

            }
        }
        catch (NumberFormatException e)
        {
            request.setAttribute("msg","数据格式错误，重试！");
            request.getRequestDispatcher("fail.jsp").forward(request,response);
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
