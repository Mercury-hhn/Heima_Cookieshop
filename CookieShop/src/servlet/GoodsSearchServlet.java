package servlet;

import service.GoodsService;
import model.*;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.net.URLEncoder;

/**
 * 商品搜索的 Servlet，处理商品搜索功能
 * 支持根据搜索关键词进行商品的分页展示
 */
@WebServlet(name = "goods_search", urlPatterns = "/goods_search")
public class GoodsSearchServlet extends HttpServlet {

    // 商品服务层对象，用于处理商品搜索的业务逻辑
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
        // 当前方法未实现逻辑
    }

    /**
     * 处理 GET 请求：根据搜索关键词和页码获取商品搜索结果，并转发到商品搜索页面
     *
     * @param request  HTTP 请求
     * @param response HTTP 响应
     * @throws ServletException
     * @throws IOException
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        // 获取前端传递的搜索关键词
        String keyword = request.getParameter("keyword");

        // 获取当前页码，默认为第 1 页
        int pageNumber = 1;
        if (request.getParameter("pageNumber") != null) {
            try {
                pageNumber = Integer.parseInt(request.getParameter("pageNumber"));
            } catch (Exception e) {
                // 捕获异常时保持默认页码为 1
            }
        }

        // 如果页码小于等于 0，强制设为第 1 页
        if (pageNumber <= 0) {
            pageNumber = 1;
        }

        // 调用商品服务层获取商品搜索的分页数据
        Page p = gService.getSearchGoodsPage(keyword, pageNumber);

        // 如果查询结果总页数为 0，设置默认为 1 页
        if (p.getTotalPage() == 0) {
            p.setTotalPage(1);
            p.setPageNumber(1);
        } else {
            // 如果页码超过总页数，则调整为最大页数
            if (pageNumber >= p.getTotalPage() + 1) {
                p = gService.getSearchGoodsPage(keyword, p.getTotalPage());
            }
        }

        // 将分页结果和搜索关键词传递到前端页面
        request.setAttribute("p", p);
        request.setAttribute("keyword", URLEncoder.encode(keyword, "utf-8"));

        // 转发到商品搜索页面（goods_search.jsp）
        request.getRequestDispatcher("/goods_search.jsp").forward(request, response);
    }
}
