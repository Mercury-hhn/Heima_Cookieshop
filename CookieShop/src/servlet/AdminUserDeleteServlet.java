package servlet;

import service.UserService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * 管理员删除用户的 Servlet，处理删除用户请求
 * 支持根据用户 ID 删除指定用户并返回操作结果
 */
@WebServlet(name = "admin_user_delete", urlPatterns = "/admin/user_delete")
public class AdminUserDeleteServlet extends HttpServlet {

    // 用户服务层对象，用于处理用户相关的删除逻辑
    private UserService uService = new UserService();

    /**
     * 处理 GET 请求：根据用户 ID 删除用户，并展示删除结果
     *
     * @param request  HTTP 请求
     * @param response HTTP 响应
     * @throws ServletException
     * @throws IOException
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        // 获取前端传递的用户 ID 参数
        int id = Integer.parseInt(request.getParameter("id"));

        // 调用服务层删除用户，并判断删除结果
        boolean isSuccess = uService.delete(id);

        // 根据删除结果设置提示信息
        if (isSuccess) {
            request.setAttribute("msg", "客户删除成功");
        } else {
            request.setAttribute("failMsg", "客户有下的订单，请先删除该客户下的订单，再来删除客户！");
        }

        // 删除后重定向到用户列表页面，展示操作结果
        request.getRequestDispatcher("/admin/user_list").forward(request, response);
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
