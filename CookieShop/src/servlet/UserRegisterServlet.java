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
 * 用户注册的 Servlet，处理用户注册功能
 * 支持通过表单提交注册信息，并进行用户名或邮箱唯一性验证
 */
@WebServlet(name = "user_register", urlPatterns = "/user_rigister")
public class UserRegisterServlet extends HttpServlet {

    // 用户服务层对象，用于处理用户注册的业务逻辑
    private UserService uService = new UserService();

    /**
     * 处理 POST 请求：接收用户注册表单提交的注册信息并进行处理
     *
     * @param request  HTTP 请求
     * @param response HTTP 响应
     * @throws ServletException
     * @throws IOException
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        // 创建一个新的用户对象
        User user = new User();

        // 使用 BeanUtils 工具类将前端提交的表单参数映射到用户对象
        try {
            BeanUtils.copyProperties(user, request.getParameterMap());
        } catch (IllegalAccessException | InvocationTargetException e) {
            e.printStackTrace(); // 处理异常
        }

        // 调用服务层注册用户
        if (uService.register(user)) {
            // 注册成功，设置成功消息并转发到登录页面
            request.setAttribute("msg", "注册成功，请登录！");
            request.getRequestDispatcher("user_login.jsp").forward(request, response);
        } else {
            // 注册失败，用户名或邮箱重复，设置失败消息并转发回注册页面
            request.setAttribute("msg", "用户名或邮箱重复，请重新填写！");
            request.getRequestDispatcher("user_register.jsp").forward(request, response);
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
