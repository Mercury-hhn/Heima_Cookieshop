package servlet;

import service.OrderService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * 管理员修改订单状态的 Servlet，处理订单状态更新操作
 * 支持修改订单状态并重定向到订单列表页面
 */
@WebServlet(name = "admin_order_status", urlPatterns = "/admin/order_status")
public class AdminOrderStatusServlet extends HttpServlet {

    // 订单服务层对象，用于处理订单状态更新相关的业务逻辑
    private OrderService oService = new OrderService();

    /**
     * 处理 GET 请求：根据订单 ID 和状态参数，更新订单状态并重定向到订单列表页面
     *
     * @param request  HTTP 请求
     * @param response HTTP 响应
     * @throws ServletException
     * @throws IOException
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        // 获取前端传递的订单 ID 和状态值
        int id = Integer.parseInt(request.getParameter("id"));
        int status = Integer.parseInt(request.getParameter("status"));

        // 调用服务层更新订单状态
        oService.updateStatus(id, status);

        // 状态更新后，重定向到订单列表页面并传递状态参数
        response.sendRedirect("/admin/order_list?status=" + status);
    }
}
