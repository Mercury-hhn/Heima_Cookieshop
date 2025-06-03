package servlet;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * 订单提交的 Servlet，处理订单提交页面的显示
 * 如果用户已登录，转发到订单提交页面；如果未登录，转发到登录页面并提示登录
 */
@WebServlet(name = "order_submit", urlPatterns = "/order_submit")
public class OrderSubmitServlet extends HttpServlet {

    /**
     * 处理 POST 请求：当前方法未使用
     *
     * @param request  HTTP 请求
     * @param response HTTP 响应
     * @throws ServletException
     * @throws IOException
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        // 当前方法未实现逻辑
    }

    /**
     * 处理 GET 请求：检查用户是否登录，已登录则显示订单提交页面，未登录则提示登录
     *
     * @param request  HTTP 请求
     * @param response HTTP 响应
     * @throws ServletException
     * @throws IOException
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        // 如果用户已登录，转发请求至订单提交页面（order_submit.jsp）
        if (request.getSession().getAttribute("user") != null) {
            request.getRequestDispatcher("/order_submit.jsp").forward(request, response);
        } else {
            // 如果用户未登录，设置提示消息并转发至登录页面（user_login.jsp）
            request.setAttribute("failMsg", "请登录后，再提交订单！");
            request.getRequestDispatcher("/user_login.jsp").forward(request, response);
        }
    }
}
