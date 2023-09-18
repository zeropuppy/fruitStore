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

@WebServlet(name = "ListServlet",urlPatterns = "/ListServlet")
public class ListServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        FruitService service=new FruitService();
        //获得需要查询的所有数据
        ArrayList<Fruit> fruits=service.queryAllFruit();
        //获取到的结果传到页面中的控制
        request.setAttribute("fruits",fruits);
        request.getRequestDispatcher("/list.jsp").forward(request,response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request,response);
    }
}