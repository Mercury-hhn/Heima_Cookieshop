package servlet;

import service.GoodsService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * 管理员商品推荐的 Servlet，处理商品推荐的添加和移除
 * 支持通过 GET 请求传递商品 ID 和推荐类型进行商品推荐的操作
 */
@WebServlet(name = "admin_goods_recommend", urlPatterns = "/admin/goods_recommend")
public class AdminGoodsRecommendServlet extends HttpServlet {

    // 商品服务层对象，用于处理商品推荐相关的业务逻辑
    private GoodsService gService = new GoodsService();

    /**
     * 处理 GET 请求：根据商品 ID 和操作方法（add/remove）修改商品的推荐状态
     *
     * @param request  HTTP 请求
     * @param response HTTP 响应
     * @throws ServletException
     * @throws IOException
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        // 获取商品 ID、操作方法（add 或 remove）和目标推荐类型
        int id = Integer.parseInt(request.getParameter("id"));
        String method = request.getParameter("method");
        int typeTarget = Integer.parseInt(request.getParameter("typeTarget"));

        // 根据操作方法执行相应的商品推荐操作
        if ("add".equals(method)) {
            // 添加商品推荐
            gService.addRecommend(id, typeTarget);
        } else {
            // 移除商品推荐
            gService.removeRecommend(id, typeTarget);
        }

        // 重定向到商品列表页面
        request.getRequestDispatcher("/admin/goods_list").forward(request, response);
    }

    /**
     * 处理 POST 请求：转发到 doGet 方法进行处理
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
