package servlet;

import model.Page;
import service.GoodsService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * 管理员商品列表的 Servlet，处理商品列表分页显示
 * 支持根据推荐类型（如轮播、热销等）进行商品过滤和分页展示
 */
@WebServlet(name = "admin_goods_list", urlPatterns = "/admin/goods_list")
public class AdminGoodsListServlet extends HttpServlet {

    // 商品服务层对象，用于处理商品相关的业务逻辑
    private GoodsService gService = new GoodsService();

    /**
     * 处理 GET 请求：获取推荐类型和页码参数，查询商品分页数据，并转发到商品列表页面
     *
     * @param request  HTTP 请求
     * @param response HTTP 响应
     * @throws ServletException
     * @throws IOException
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        // 获取推荐类型参数（默认为 0，即显示所有商品）
        int type = 0;
        if (request.getParameter("type") != null) {
            type = Integer.parseInt(request.getParameter("type"));
        }

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

        // 调用服务层获取分页数据
        Page p = gService.getGoodsRecommendPage(type, pageNumber);

        // 如果查询结果总页数为 0，设置默认为 1 页
        if (p.getTotalPage() == 0) {
            p.setTotalPage(1);
            p.setPageNumber(1);
        } else {
            // 如果页码超过总页数，则调整为最大页数
            if (pageNumber >= p.getTotalPage() + 1) {
                p = gService.getGoodsRecommendPage(type, pageNumber);
            }
        }

        // 将分页结果和推荐类型传递到前端页面
        request.setAttribute("p", p);
        request.setAttribute("type", type);

        // 转发到商品列表页面进行展示
        request.getRequestDispatcher("/admin/goods_list.jsp").forward(request, response);
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
