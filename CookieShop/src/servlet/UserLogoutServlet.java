package servlet;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * 用户注销的 Servlet，处理用户注销登录操作
 * 清除当前会话中的用户信息，并重定向到首页
 */
@WebServlet(name = "user_logout", urlPatterns = "/user_logout")
public class UserLogoutServlet extends HttpServlet {

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
     * 处理 GET 请求：清除会话中的用户信息并重定向到首页
     *
     * @param request  HTTP 请求
     * @param response HTTP 响应
     * @throws ServletException
     * @throws IOException
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        // 从会话中移除当前用户信息
        request.getSession().removeAttribute("user");

        // 重定向到首页
        response.sendRedirect("/index");
    }
}
