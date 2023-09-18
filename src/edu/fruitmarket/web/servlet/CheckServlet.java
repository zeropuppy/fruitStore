package edu.fruitmarket.web.servlet;

import com.sun.image.codec.jpeg.JPEGCodec;
import com.sun.image.codec.jpeg.JPEGImageEncoder;

import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Random;

@WebServlet(name = "CheckServlet",urlPatterns = "/CheckServlet")
public class CheckServlet extends HttpServlet
{
    private static int WIDTH = 60;
    private static int HEIGHT = 30;

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        response.setContentType("image/jpeg");//响应类型为图片
        //通过流方式实现
        ServletOutputStream sos = response.getOutputStream();
        //设置浏览器不缓存此图片
        response.setHeader("Pragma", "No-cache");
        response.setHeader("Cache-Control", "No-cache");
        response.setDateHeader("Expires", 0);
        //创建内存图像，并获得其图形上下文
        BufferedImage image = new BufferedImage(WIDTH, HEIGHT, BufferedImage.TYPE_INT_RGB);
        Graphics g = image.getGraphics();//通过g给BufferedImage绘图
        //产生随机的认证码
        char[] rands = generateCheckCode();
        //产生图像
        drawBackground(g);
        drawRands(g, rands);
        //结束图像的绘制过程，完成图像
        g.dispose();
        //将图像输出到客户端
        JPEGImageEncoder encoder = JPEGCodec.createJPEGEncoder(response.getOutputStream());
        encoder.encode(image);
        //将当前验证码存入到Session中
        session.setAttribute("check_code", new String(rands));
    }

    //生成一个4字符的验证码
    private char[] generateCheckCode() {
        //定义验证码的字符表
        String chars = "0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz";
        char[] rands = new char[4];
        for (int i = 0; i < 4; i++) {
            int rand = (int) (Math.random() *62);
            rands[i] = chars.charAt(rand);
        }
        return rands;
    }

    private void drawRands(Graphics g, char[] rands)
    {

        int x = (int) (Math.random() * WIDTH);
        int y = (int) (Math.random() * HEIGHT);
        int red = (int) (Math.random() * 255);
        int green = (int) (Math.random() * 255);
        int blue = (int) (Math.random() * 255);
        g.setColor(new Color(red, green, blue));
        g.drawOval(x, y, 1, 0);
        g.setFont(new Font(null, Font.ITALIC | Font.BOLD, 20));
        //在不同的高度上输出验证码的每个字符
        g.drawString("" + rands[0], 1, 17);
        g.drawString("" + rands[1], 16, 15);
        g.drawString("" + rands[2], 31, 18);
        g.drawString("" + rands[3], 46, 16);
        //System.out.println(rands);
    }

    private void drawBackground (Graphics g){
        //画背景
        g.setColor(new Color(0xFAD8D8));
        g.fillRect(0, 0, WIDTH, HEIGHT);
        //随机产生120个干扰点
        for (int i = 0; i < 120; i++) {
            int x = (int) (Math.random() * WIDTH);
            int y = (int) (Math.random() * HEIGHT);
            int red = (int) (Math.random() * 255);
            int green = (int) (Math.random() * 255);
            int blue = (int) (Math.random() * 255);
            g.setColor(new Color(red, green, blue));
            g.drawOval(x, y, 1, 0);
        }
        //		画干扰线
        Random random=new Random();
        int max=random.nextInt(10);
        for(int i=0;i<max;i++)
        {
            g.setColor(new Color(random.nextInt(256),random.nextInt(256),random.nextInt(256)));
            g.drawLine(random.nextInt(100),random.nextInt(50), random.nextInt(100), random.nextInt(50));
        }
    }
}
