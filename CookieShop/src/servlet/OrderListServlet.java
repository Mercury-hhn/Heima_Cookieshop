package servlet;

import model.Order;
import model.User;
import service.OrderService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

/**
 * 订单列表的 Servlet，处理用户订单列表展示
 * 支持根据用户 ID 查询用户的所有订单，并展示订单列表
 */
@WebServlet(name = "order_list", urlPatterns = "/order_list")
public class OrderListServlet extends HttpServlet {

    // 订单服务层对象，用于处理订单相关的业务逻辑
    private OrderService oService = new OrderService();

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
     * 处理 GET 请求：根据当前用户 ID 获取用户的所有订单并展示
     *
     * @param request  HTTP 请求
     * @param response HTTP 响应
     * @throws ServletException
     * @throws IOException
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        // 获取当前会话中的用户对象
        User u = (User) request.getSession().getAttribute("user");

        // 如果用户未登录，重定向到首页
        if (u == null) {
            response.sendRedirect("/index");
            return;
        }

        // 根据用户 ID 查询用户的所有订单
        List<Order> list = oService.selectAll(u.getId());

        // 将订单列表传递到前端页面
        request.setAttribute("orderList", list);

        // 转发请求至订单列表页面（order_list.jsp）进行展示
        request.getRequestDispatcher("/order_list.jsp").forward(request, response);
    }
}
