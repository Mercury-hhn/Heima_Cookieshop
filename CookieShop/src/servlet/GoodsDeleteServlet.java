package servlet;

import model.Order;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * 商品删除的 Servlet，处理从购物车中删除商品的操作
 * 支持根据商品 ID 删除指定商品
 */
@WebServlet(name = "goods_delete", urlPatterns = "/goods_delete")
public class GoodsDeleteServlet extends HttpServlet {

    /**
     * 处理 POST 请求：根据商品 ID 从购物车中删除商品
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

        // 获取前端传递的商品 ID 参数
        int goodsid = Integer.parseInt(request.getParameter("goodsid"));

        // 从订单中删除指定的商品
        o.delete(goodsid);

        // 返回成功信息
        response.getWriter().print("ok");
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
