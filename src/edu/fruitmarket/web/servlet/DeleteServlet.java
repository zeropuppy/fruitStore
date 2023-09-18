package edu.fruitmarket.web.servlet;

import edu.fruitmarket.web.service.FruitService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "DeleteServlet",urlPatterns = "/DeleteServlet")
public class DeleteServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        boolean succ=false;
        FruitService service=new FruitService();
        String number=request.getParameter("number");
        String [] numbers= number.split(",");
        for(String delNum:numbers)
        {
            succ= service.delFruit(delNum);
        }
        if(succ) {
            request.setAttribute("msg", "删除成功！");
            request.getRequestDispatcher("msg.jsp").forward(request, response);
        }else
        {
            request.setAttribute("msg","水果信息不存在，无法删除！");
            request.getRequestDispatcher("fail.jsp").forward(request,response);
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request, response);
    }
}
