package servlet;

import model.Type;
import service.TypeService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * 管理员添加商品分类的 Servlet，处理分类添加操作
 * 支持通过表单提交新的分类名称，添加到数据库并重定向到分类列表页面
 */
@WebServlet(name = "admin_type_add", urlPatterns = "/admin/type_add")
public class AdminTypeAddServlet extends HttpServlet {

    // 分类服务层对象，用于处理分类相关的业务逻辑
    private TypeService tService = new TypeService();

    /**
     * 处理 GET 请求：接收新的分类名称并插入到数据库
     *
     * @param request  HTTP 请求
     * @param response HTTP 响应
     * @throws ServletException
     * @throws IOException
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        // 获取前端传递的分类名称
        String name = request.getParameter("name");

        // 调用服务层插入新的分类
        tService.insert(new Type(name));

        // 插入成功后，重定向到分类列表页面
        request.getRequestDispatcher("/admin/type_list").forward(request, response);
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
