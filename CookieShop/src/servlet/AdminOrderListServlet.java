package servlet;

import model.Page;
import service.OrderService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * 管理员订单列表的 Servlet，处理订单列表分页展示和筛选
 * 支持根据订单状态过滤订单，并进行分页展示
 */
@WebServlet(name = "admin_order_list", urlPatterns = "/admin/order_list")
public class AdminOrderListServlet extends HttpServlet {

    // 订单服务层对象，用于处理订单相关的业务逻辑
    private OrderService oService = new OrderService();

    /**
     * 处理 GET 请求：获取订单状态和页码，查询订单分页数据，并转发到订单列表页面
     *
     * @param request  HTTP 请求
     * @param response HTTP 响应
     * @throws ServletException
     * @throws IOException
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        // 获取订单状态参数（默认为 0，表示查询所有状态的订单）
        int status = 0;
        if (request.getParameter("status") != null) {
            status = Integer.parseInt(request.getParameter("status"));
        }
        // 将状态信息传递到前端页面
        request.setAttribute("status", status);

        // 获取当前页码，默认为第 1 页
        int pageNumber = 1;
        if (request.getParameter("pageNumber") != null) {
            try {
                pageNumber = Integer.parseInt(request.getParameter("pageNumber"));
            } catch (Exception e) {
                // 捕获异常时保持默认页码为 1
            }
        }

        // 如果 pageNumber 小于等于 0，强制设为第 1 页
        if (pageNumber <= 0) pageNumber = 1;

        // 调用订单服务层获取分页数据
        Page p = oService.getOrderPage(status, pageNumber);

        // 如果查询结果总页数为 0，设置默认为 1 页
        if (p.getTotalPage() == 0) {
            p.setTotalPage(1);
            p.setPageNumber(1);
        } else {
            // 如果页码超过总页数，则调整为最大页数
            if (pageNumber >= p.getTotalPage() + 1) {
                p = oService.getOrderPage(status, pageNumber);
            }
        }

        // 将分页结果传递到前端页面
        request.setAttribute("p", p);

        // 转发到订单列表页面（order_list.jsp）进行展示
        request.getRequestDispatcher("/admin/order_list.jsp").forward(request, response);
    }
}
