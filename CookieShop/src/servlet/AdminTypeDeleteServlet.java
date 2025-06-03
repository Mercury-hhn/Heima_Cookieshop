package servlet;

import service.TypeService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * 管理员删除商品分类的 Servlet，处理分类删除操作
 * 支持通过 GET 请求传递分类 ID，删除分类并展示删除结果
 */
@WebServlet(name = "admin_type_delete", urlPatterns = "/admin/type_delete")
public class AdminTypeDeleteServlet extends HttpServlet {

    // 分类服务层对象，用于处理分类删除的业务逻辑
    private TypeService tService = new TypeService();

    /**
     * 处理 GET 请求：删除指定 ID 的商品分类，若删除失败则返回失败信息
     *
     * @param request  HTTP 请求
     * @param response HTTP 响应
     * @throws ServletException
     * @throws IOException
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        // 获取前端传递的分类 ID 参数
        int id = Integer.parseInt(request.getParameter("id"));

        // 调用服务层删除分类，并判断是否删除成功
        boolean isSuccess = tService.delete(id);

        // 根据删除结果设置提示信息
        if (isSuccess) {
            request.setAttribute("msg", "删除成功");
        } else {
            request.setAttribute("failMsg", "类目下包含商品，无法直接删除类目！");
        }

        // 删除后转发到分类列表页面，显示操作结果
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
