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
 * 用户登录的 Servlet，处理用户登录功能
 * 支持根据用户名或邮箱和密码进行登录验证
 */
@WebServlet(name = "user_login", urlPatterns = "/user_login")
public class UserLoginServlet extends HttpServlet {

    // 用户服务层对象，用于处理用户登录逻辑
    private UserService uService = new UserService();

    /**
     * 处理 POST 请求：接收用户的用户名或邮箱和密码进行登录验证
     *
     * @param request  HTTP 请求
     * @param response HTTP 响应
     * @throws ServletException
     * @throws IOException
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        // 获取前端提交的用户名或邮箱和密码
        String ue = request.getParameter("ue");
        String password = request.getParameter("password");

        // 调用服务层验证用户的用户名/邮箱和密码
        User user = uService.login(ue, password);

        // 如果用户不存在或密码错误，设置错误信息并转发回登录页面
        if (user == null) {
            request.setAttribute("failMsg", "用户名、邮箱或者密码错误，请重新登录！");
            request.getRequestDispatcher("/user_login.jsp").forward(request, response);
        } else {
            // 如果登录成功，将用户信息存入会话并转发到用户中心页面
            request.getSession().setAttribute("user", user);
            request.getRequestDispatcher("/user_center.jsp").forward(request, response);
        }
    }

    /**
     * 处理 GET 请求：该方法暂时没有使用
     *
     * @param request  HTTP 请求
     * @param response HTTP 响应
     * @throws ServletException
     * @throws IOException
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        // 当前方法未实现逻辑
    }
}
