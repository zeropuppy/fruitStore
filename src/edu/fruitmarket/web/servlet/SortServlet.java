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

@WebServlet(name = "SortServlet",urlPatterns = "/SortServlet")
public class SortServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        FruitService service=new FruitService();
        String sort=request.getParameter("sort");
        ArrayList<Fruit> fruits=service.sortFruits(sort);
        request.setAttribute("fruits", fruits);
        request.getRequestDispatcher("/list.jsp").forward(request, response);//将上面的错误信息发到这个页面
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request, response);
    }
}
