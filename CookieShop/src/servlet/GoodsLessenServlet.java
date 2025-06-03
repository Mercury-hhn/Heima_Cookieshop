package servlet;

import model.Order;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * 商品减少数量的 Servlet，处理购物车中商品数量减少的操作
 * 支持根据商品 ID 将商品数量减少 1
 */
@WebServlet(name = "goods_lessen", urlPatterns = "/goods_lessen")
public class GoodsLessenServlet extends HttpServlet {

    /**
     * 处理 POST 请求：根据商品 ID 从购物车中减少商品数量
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

        // 从订单中减少指定商品的数量
        o.lessen(goodsid);

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
