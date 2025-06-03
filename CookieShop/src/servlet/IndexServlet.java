package servlet;

import service.GoodsService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;
import java.util.Map;

/**
 * 首页展示的 Servlet，处理首页的数据加载和展示
 * 包括展示滚动推荐商品、新品、热销商品等内容
 */
@WebServlet(name = "IndexServlet", urlPatterns = "/index")
public class IndexServlet extends HttpServlet {

    // 商品服务层对象，用于处理商品相关的业务逻辑
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
     * 处理 GET 请求：加载首页数据并转发到首页页面
     *
     * @param request  HTTP 请求
     * @param response HTTP 响应
     * @throws ServletException
     * @throws IOException
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        // 获取滚动推荐商品列表
        List<Map<String, Object>> ScrollGood = gService.getScrollGood();
        request.setAttribute("scroll", ScrollGood);

        // 获取新品商品列表
        List<Map<String, Object>> newList = gService.getGoodsList(3);
        request.setAttribute("newList", newList);

        // 获取热销商品列表
        List<Map<String, Object>> hotList = gService.getGoodsList(2);
        request.setAttribute("hotList", hotList);

        // 转发请求至首页页面（index.jsp）进行展示
        request.getRequestDispatcher("index.jsp").forward(request, response);
    }
}
