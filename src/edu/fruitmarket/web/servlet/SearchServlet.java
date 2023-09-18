package edu.fruitmarket.web.servlet;

import edu.fruitmarket.web.beans.Fruit;
import edu.fruitmarket.web.service.FruitService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;

@WebServlet(name = "SearchServlet",urlPatterns = "/SearchServlet")
public class SearchServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        FruitService service=new FruitService();
        String number=null;
        String name=null;
        String  price=null;
        String unit=null;
        Fruit fruit = new Fruit();
        String cond=request.getParameter("cond");
        try {
            if("1".equals(cond))
            {
                number=request.getParameter("keywords");
                fruit.setNumber(number);
            }
            if("2".equals(cond))
            {
                name=request.getParameter("keywords");
                fruit.setName(name);
            } if("3".equals(cond))
            {
                price=request.getParameter("keywords");
                fruit.setPrice(Double.parseDouble(price));
            } if("4".equals(cond))
            {
                unit=request.getParameter("keywords");
                fruit.setUnit(unit);
            }





            ArrayList<Fruit> fruits=service.queryFruitByCond(fruit);
            //获取到的结果传到页面中的控制
            request.setAttribute("fruits",fruits);
            request.getRequestDispatcher("/list.jsp").forward(request,response);

        }catch (NumberFormatException e)
        {
            request.setAttribute("msg", "水果单价查询条件格式错误，请重新输入！");
            request.getRequestDispatcher("fail.jsp").forward(request, response);//将上面的错误信息发到这个页面
        }

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request, response);
    }
}
