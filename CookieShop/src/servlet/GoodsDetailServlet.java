package servlet;

import model.Goods;
import service.GoodsService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * 商品详情的 Servlet，处理显示商品详细信息的请求
 * 根据商品 ID 获取商品信息，并转发到商品详情页面进行展示
 */
@WebServlet(name = "goods_detail", urlPatterns = "/goods_detail")
public class GoodsDetailServlet extends HttpServlet {

    // 商品服务层对象，用于获取商品信息
    private GoodsService gService = new GoodsService();

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
        // 目前未实现 POST 请求的逻辑
    }

    /**
     * 处理 GET 请求：根据商品 ID 获取商品详细信息并转发到商品详情页面
     *
     * @param request  HTTP 请求
     * @param response HTTP 响应
     * @throws ServletException
     * @throws IOException
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        // 获取前端传递的商品 ID 参数
        int id = Integer.parseInt(request.getParameter("id"));

        // 根据商品 ID 获取商品信息
        Goods g = gService.getGoodsById(id);

        // 将商品对象传递到前端页面进行展示
        request.setAttribute("g", g);

        // 转发请求至商品详情页面（goods_detail.jsp）
        request.getRequestDispatcher("/goods_detail.jsp").forward(request, response);
    }
}
