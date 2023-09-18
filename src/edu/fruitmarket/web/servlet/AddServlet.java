package edu.fruitmarket.web.servlet;

import edu.fruitmarket.web.beans.Fruit;
import edu.fruitmarket.web.service.FruitService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "AddServlet",urlPatterns = "/AddServlet")
public class AddServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        FruitService service=new FruitService();
        String number=request.getParameter("number").trim();
        String name=request.getParameter("name").trim();
        String price=request.getParameter("price").trim();
        String unit=request.getParameter("unit").trim();
        try {
            if (number != null && number.length() > 0) {
                Fruit fruit = new Fruit(number, name, Double.parseDouble(price), unit);
                if( service.addFruit(fruit))
                {
                    request.setAttribute("msg", "添加成功！");
                    request.getRequestDispatcher("msg.jsp").forward(request, response);//将上面的错误信息发到这个页面

                }else
                {
                    request.setAttribute("msg", "水果编号不能重复，请重新输入！");
                    request.getRequestDispatcher("fail.jsp").forward(request, response);//将上面的错误信息发到这个页面

                }
            } else {
                request.setAttribute("msg", "水果编号不能为空，请重新输入！");
                request.getRequestDispatcher("fail.jsp").forward(request, response);//将上面的错误信息发到这个页面

            }
        }catch (NumberFormatException e)

        {
            request.setAttribute("msg", "水果单价格式错误，请重新输入！");
            request.getRequestDispatcher("fail.jsp").forward(request, response);//将上面的错误信息发到这个页面
        }

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request, response);
    }
}

