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
 * 商品推荐列表的 Servlet，处理商品推荐信息的分页展示
 * 支持根据推荐类型和页码参数，查询并展示推荐商品
 */
@WebServlet(name = "goodrecommendList", urlPatterns = "/goodsrecommend_list")
public class GoodRecommendListServlet extends HttpServlet {

    // 商品服务层对象，用于处理商品推荐相关的业务逻辑
    private GoodsService gService = new GoodsService();

    /**
     * 处理 GET 请求：获取推荐类型和页码参数，查询推荐商品并转发到商品推荐列表页面
     *
     * @param request  HTTP 请求
     * @param response HTTP 响应
     * @throws ServletException
     * @throws IOException
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        // 获取推荐类型参数
        int type = Integer.parseInt(request.getParameter("type"));

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
        if (pageNumber <= 0) pageNumber = 1;

        // 调用服务层获取推荐商品分页数据
        Page p = gService.getGoodsRecommendPage(type, pageNumber);

        // 如果查询结果总页数为 0，设置默认为 1 页
        if (p.getTotalPage() == 0) {
            p.setTotalPage(1);
            p.setPageNumber(1);
        } else {
            // 如果页码超过总页数，则调整为最大页数
            if (pageNumber >= p.getTotalPage() + 1) {
                p = gService.getGoodsRecommendPage(type, p.getTotalPage());
            }
        }

        // 将分页结果和推荐类型传递到前端页面
        request.setAttribute("p", p);
        request.setAttribute("t", type);

        // 转发到商品推荐列表页面（goodsrecommend_list.jsp）进行展示
        request.getRequestDispatcher("goodsrecommend_list.jsp").forward(request, response);
    }
}
