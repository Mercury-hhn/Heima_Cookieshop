package servlet;

import model.Goods;
import model.Order;
import service.GoodsService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * 商品购买的 Servlet，处理商品添加到购物车操作
 * 支持判断商品库存，若库存大于 0，则添加到订单中
 */
@WebServlet(name = "goods_buy", urlPatterns = "/goods_buy")
public class GoodsBuyServlet extends HttpServlet {

    // 商品服务层对象，用于处理商品信息相关的业务逻辑
    private GoodsService gService = new GoodsService();

    /**
     * 处理 POST 请求：根据商品 ID 查询商品，若库存足够则将商品添加到购物车
     *
     * @param request  HTTP 请求
     * @param response HTTP 响应
     * @throws ServletException
     * @throws IOException
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        // 获取当前会话中的订单对象（若没有则新建一个）
        Order o = null;
        if (request.getSession().getAttribute("order") != null) {
            o = (Order) request.getSession().getAttribute("order");
        } else {
            o = new Order(); // 如果订单不存在则新建一个
            request.getSession().setAttribute("order", o); // 将订单对象存入会话
        }

        // 获取前端传递的商品 ID 参数
        int goodsid = Integer.parseInt(request.getParameter("goodsid"));

        // 根据商品 ID 获取商品对象
        Goods goods = gService.getGoodsById(goodsid);

        // 判断商品库存是否大于 0
        if (goods.getStock() > 0) {
            // 如果库存足够，添加商品到订单
            o.addGoods(goods);
            response.getWriter().print("ok"); // 返回成功信息
        } else {
            // 如果库存不足，返回失败信息
            response.getWriter().print("fail");
        }
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
