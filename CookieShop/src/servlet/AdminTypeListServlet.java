package servlet;

import model.Type;
import service.TypeService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

/**
 * 管理员商品分类列表的 Servlet，展示所有商品分类
 * 支持获取所有分类数据，并转发到分类列表页面
 */
@WebServlet(name = "admin_type_list", urlPatterns = "/admin/type_list")
public class AdminTypeListServlet extends HttpServlet {

    // 分类服务层对象，用于处理分类相关的业务逻辑
    private TypeService tService = new TypeService();

    /**
     * 处理 GET 请求：获取所有商品分类并转发到分类列表页面
     *
     * @param request  HTTP 请求
     * @param response HTTP 响应
     * @throws ServletException
     * @throws IOException
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        // 获取所有商品分类列表
        List<Type> list = tService.GetAllType();

        // 将分类列表存入 request 中，以供 JSP 页面展示
        request.setAttribute("list", list);

        // 移除并重新设置应用级属性 typeList
        this.getServletContext().removeAttribute("typeList");
        this.getServletContext().setAttribute("typeList", list);

        // 转发到分类列表页面（type_list.jsp）
        request.getRequestDispatcher("/admin/type_list.jsp").forward(request, response);
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
