package servlet;

import model.*;
import org.apache.commons.beanutils.BeanUtils;
import service.OrderService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.Date;

/**
 * 订单确认的 Servlet，处理订单信息的确认操作
 * 支持接收订单的收货信息并保存订单，完成订单提交
 */
@WebServlet(name = "order_confirm", urlPatterns = "/order_confirm")
public class OrderConfirmServlet extends HttpServlet {

    // 订单服务层对象，用于处理订单相关的业务逻辑
    private OrderService oService = new OrderService();

    /**
     * 处理 POST 请求：接收订单信息并保存订单，完成订单提交
     *
     * @param request  HTTP 请求
     * @param response HTTP 响应
     * @throws ServletException
     * @throws IOException
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        // 获取当前会话中的订单对象
        Order o = (Order) request.getSession().getAttribute("order");

        try {
            // 将前端提交的表单数据映射到订单对象中
            BeanUtils.copyProperties(o, request.getParameterMap());
        } catch (IllegalAccessException | InvocationTargetException e) {
            e.printStackTrace(); // 处理异常
        }

        // 设置订单的日期和状态
        o.setDatetime(new Date());
        o.setStatus(2); // 订单状态设为已付款（2）

        // 获取当前会话中的用户对象并关联到订单
        o.setUser((User) request.getSession().getAttribute("user"));

        // 调用订单服务层保存订单
        oService.addOrder(o);

        // 订单提交后，清空会话中的订单对象
        request.getSession().removeAttribute("order");

        // 设置成功消息并转发到订单成功页面
        request.setAttribute("msg", "订单支付成功！");
        request.getRequestDispatcher("/order_success.jsp").forward(request, response);
    }

    /**
     * 处理 GET 请求：该方法暂时没有使用
     *
     * @param request  HTTP 请求
     * @param response HTTP 响应
     * @throws ServletException
     * @throws IOException
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        // 当前方法未实现逻辑
    }
}
