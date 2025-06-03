package servlet;

import service.GoodsService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * 管理员删除商品的 Servlet，处理商品删除请求
 */
@WebServlet(name = "admin_goods_delete", urlPatterns = "/admin/goods_delete")
public class AdminGoodsDeleteServlet extends HttpServlet {

    // 商品服务层对象，用于处理商品相关的业务逻辑
    private GoodsService gService = new GoodsService();

    /**
     * 处理 GET 请求：获取商品 ID 并删除商品
     *
     * @param request  HTTP 请求
     * @param response HTTP 响应
     * @throws ServletException
     * @throws IOException
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        // 获取前端传递的商品 ID
        int id = Integer.parseInt(request.getParameter("id"));

        // 调用业务层删除指定商品
        gService.delete(id);

        // 删除商品后，重定向到商品列表页面，展示最新商品信息
        request.getRequestDispatcher("/admin/goods_list").forward(request, response);
    }

    /**
     * 处理 POST 请求：转发到 doGet() 方法进行处理
     *
     * @param request  HTTP 请求
     * @param response HTTP 响应
     * @throws ServletException
     * @throws IOException
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        doGet(request, response);
    }
}
