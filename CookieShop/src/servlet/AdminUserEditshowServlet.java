package servlet;

import model.User;
import service.UserService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * 管理员展示编辑用户信息页面的 Servlet，处理获取指定用户信息并转发到编辑页面
 * 支持根据用户 ID 获取用户数据并展示在编辑页面上
 */
@WebServlet(name = "admin_user_editshow", urlPatterns = "/admin/user_editshow")
public class AdminUserEditshowServlet extends HttpServlet {

    // 用户服务层对象，用于处理用户数据相关的逻辑
    private UserService uService = new UserService();

    /**
     * 处理 GET 请求：根据用户 ID 获取用户信息并转发到编辑页面
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

        // 使用服务层根据用户 ID 获取用户详细信息
        User user = uService.selectById(id);

        // 将获取到的用户信息存入 request 中，供 JSP 页面展示
        request.setAttribute("u", user);

        // 转发请求至用户编辑页面（user_edit.jsp）
        request.getRequestDispatcher("/admin/user_edit.jsp").forward(request, response);
    }
}
