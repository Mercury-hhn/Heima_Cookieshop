package servlet;

import model.Type;
import org.apache.commons.beanutils.BeanUtils;
import service.TypeService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * 管理员编辑商品分类的 Servlet，处理分类信息修改
 * 支持通过表单提交更新商品分类的名称
 */
@WebServlet(name = "admin_type_edit", urlPatterns = "/admin/type_edit")
public class AdminTypeEditServlet extends HttpServlet {

    // 分类服务层对象，用于处理分类信息更新
    private TypeService tService = new TypeService();

    /**
     * 处理 GET 请求：获取前端提交的分类数据，更新分类信息
     *
     * @param request  HTTP 请求
     * @param response HTTP 响应
     * @throws ServletException
     * @throws IOException
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        // 创建分类对象，用于接收前端传递的分类信息
        Type t = new Type();

        try {
            // 使用 BeanUtils 工具类将请求中的参数映射到 Type 对象
            BeanUtils.copyProperties(t, request.getParameterMap());
        } catch (Exception e) {
            e.printStackTrace(); // 捕获异常并打印
        }

        // 调用服务层更新分类信息
        tService.update(t);

        // 更新成功后，重定向到分类列表页面
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
