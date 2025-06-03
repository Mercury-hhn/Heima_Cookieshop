package servlet;

import model.User;
import org.apache.commons.beanutils.BeanUtils;
import service.UserService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;

/**
 * 管理员添加用户的 Servlet，处理添加新用户请求
 * 支持通过表单提交用户信息，并在后台进行注册验证
 */
@WebServlet(name = "admin_user_add", urlPatterns = "/admin/user_add")
public class AdminUserAddServlet extends HttpServlet {

    // 用户服务层对象，用于处理用户注册逻辑
    private UserService uService = new UserService();

    /**
     * 处理 POST 请求：接收前端提交的用户信息，进行注册操作并返回结果
     *
     * @param request  HTTP 请求
     * @param response HTTP 响应
     * @throws ServletException
     * @throws IOException
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        // 创建一个新的 User 对象，用于接收表单数据
        User user = new User();

        try {
            // 将前端提交的表单参数映射到 User 对象中
            BeanUtils.copyProperties(user, request.getParameterMap());
        } catch (IllegalAccessException | InvocationTargetException e) {
            e.printStackTrace(); // 处理异常
        }

        // 调用服务层进行用户注册
        if (uService.register(user)) {
            // 注册成功，设置成功消息并重定向到用户列表页面
            request.setAttribute("msg", "客户添加成功！");
            request.getRequestDispatcher("/admin/user_list").forward(request, response);
        } else {
            // 注册失败，用户名或邮箱重复，设置失败消息并返回注册页面
            request.setAttribute("failMsg", "用户名或邮箱重复，请重新填写！");
            request.setAttribute("u", user); // 将用户信息返回到前端表单中
            request.getRequestDispatcher("/admin/user_add.jsp").forward(request, response);
        }
    }
}
