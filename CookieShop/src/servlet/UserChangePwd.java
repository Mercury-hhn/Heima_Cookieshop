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
 * 用户修改密码的 Servlet，处理用户密码修改功能
 * 支持通过提交原密码和新密码来更改密码
 */
@WebServlet(name = "user_changepwd", urlPatterns = "/user_changepwd")
public class UserChangePwd extends HttpServlet {

    // 用户服务层对象，用于处理密码更新的业务逻辑
    private UserService uService = new UserService();

    /**
     * 处理 POST 请求：接收用户的原密码和新密码，并更新用户密码
     *
     * @param request  HTTP 请求
     * @param response HTTP 响应
     * @throws ServletException
     * @throws IOException
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        // 获取前端提交的原密码和新密码
        String password = request.getParameter("password");
        String newPwd = request.getParameter("newPassword");

        // 获取当前会话中的用户对象
        User u = (User) request.getSession().getAttribute("user");

        // 判断原密码是否正确
        if (password.equals(u.getPassword())) {
            // 如果原密码正确，更新用户密码
            u.setPassword(newPwd);
            uService.updatePwd(u);

            // 设置成功消息并转发到用户中心页面
            request.setAttribute("msg", "修改成功！");
            request.getRequestDispatcher("/user_center.jsp").forward(request, response);
        } else {
            // 如果原密码不正确，设置失败消息并转发回用户中心页面
            request.setAttribute("failMsg", "修改失败，原密码不正确，你再想想！");
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
