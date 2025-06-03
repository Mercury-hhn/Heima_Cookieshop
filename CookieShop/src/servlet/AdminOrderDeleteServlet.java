package servlet;

import service.OrderService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * 管理员删除订单的 Servlet，处理订单删除请求
 */
@WebServlet(name = "admin_order_delete", urlPatterns = "/admin/order_delete")
public class AdminOrderDeleteServlet extends HttpServlet {

    // 订单服务层对象，用于处理订单相关的业务逻辑
    private OrderService oService = new OrderService();

    /**
     * 处理 GET 请求：根据订单 ID 删除指定订单
     *
     * @param request  HTTP 请求
     * @param response HTTP 响应
     * @throws ServletException
     * @throws IOException
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        // 获取前端传递的订单 ID 参数
        int id = Integer.parseInt(request.getParameter("id"));

        // 调用订单服务层删除订单
        oService.delete(id);

        // 删除成功后重定向到订单列表页面
        request.getRequestDispatcher("/admin/order_list").forward(request, response);
    }
}
