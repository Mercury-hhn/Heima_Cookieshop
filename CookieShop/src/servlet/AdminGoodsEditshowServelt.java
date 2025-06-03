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
 * 管理员展示商品编辑页面的 Servlet，负责获取指定商品信息并转发至编辑页面
 */
@WebServlet(name = "admin_goods_editshow", urlPatterns = "/admin/goods_editshow")
public class AdminGoodsEditshowServelt extends HttpServlet {
    // 商品服务层对象，用于处理商品相关的业务逻辑
    private GoodsService gService = new GoodsService();

    /**
     * 处理 GET 请求：根据商品 ID 获取商品信息并转发至商品编辑页面
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

        // 使用服务层根据商品 ID 获取商品详情
        Goods g = gService.getGoodsById(id);

        // 将商品对象存入 request 中，供 JSP 页面使用
        request.setAttribute("g", g);

        // 转发请求至商品编辑页面（goods_edit.jsp）
        request.getRequestDispatcher("/admin/goods_edit.jsp").forward(request, response);
    }

    /**
     * 处理 POST 请求：直接转发到 doGet 方法进行处理
     *
     * @param request  HTTP 请求
     * @param response HTTP 响应
     * @throws ServletException
     * @throws IOException
     */

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        doGet(request, response);
    }
}
