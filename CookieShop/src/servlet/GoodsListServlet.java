package servlet;

import model.Goods;
import model.Page;
import model.Type;
import service.GoodsService;
import service.TypeService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

/**
 * 商品列表的 Servlet，处理商品的分页显示
 * 支持根据分类 ID 和页码显示商品，提供分页功能
 */
@WebServlet(name = "goods_List", urlPatterns = "/goods_list")
public class GoodsListServlet extends HttpServlet {

    // 商品服务层对象，用于处理商品相关的业务逻辑
    private GoodsService gService = new GoodsService();

    // 分类服务层对象，用于处理分类相关的业务逻辑
    private TypeService tService = new TypeService();

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
     * 处理 GET 请求：根据分类 ID 和页码获取商品列表，并转发到商品列表页面
     *
     * @param request  HTTP 请求
     * @param response HTTP 响应
     * @throws ServletException
     * @throws IOException
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        // 获取前端传递的分类 ID 参数，默认为 0
        int id = 0;
        if (request.getParameter("typeid") != null) {
            id = Integer.parseInt(request.getParameter("typeid"));
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

        // 获取分类信息，如果分类 ID 不为 0，则查询分类信息
        Type t = null;
        if (id != 0) {
            t = tService.selectTypeNameByID(id);
        }

        // 将分类信息传递到前端页面
        request.setAttribute("t", t);

        // 如果页码小于等于 0，强制设为第 1 页
        if (pageNumber <= 0) pageNumber = 1;

        // 调用商品服务层获取商品分页数据
        Page p = gService.selectPageByTypeID(id, pageNumber);

        // 如果查询结果总页数为 0，设置默认为 1 页
        if (p.getTotalPage() == 0) {
            p.setTotalPage(1);
            p.setPageNumber(1);
        } else {
            // 如果页码超过总页数，则调整为最大页数
            if (pageNumber >= p.getTotalPage() + 1) {
                p = gService.selectPageByTypeID(id, p.getTotalPage());
            }
        }

        // 将分页结果传递到前端页面
        request.setAttribute("p", p);
        request.setAttribute("id", String.valueOf(id));

        // 转发请求至商品列表页面（goods_list.jsp）
        request.getRequestDispatcher("/goods_list.jsp").forward(request, response);
    }
}
