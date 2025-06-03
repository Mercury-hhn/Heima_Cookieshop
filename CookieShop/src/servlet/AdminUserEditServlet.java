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
 * 管理员编辑用户信息的 Servlet，处理用户地址等信息的修改
 * 支持通过表单提交用户信息并进行更新
 */
@WebServlet(name = "admin_user_edit", urlPatterns = "/admin/user_edit")
public class AdminUserEditServlet extends HttpServlet {

    // 用户服务层对象，用于处理用户信息更新逻辑
    private UserService uService = new UserService();

    /**
     * 处理 POST 请求：接收前端提交的用户信息，更新用户地址等信息
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
        User u = new User();

        try {
            // 使用 BeanUtils 工具类将前端提交的表单参数映射到 User 对象中
            BeanUtils.copyProperties(u, request.getParameterMap());
        } catch (Exception e) {
            e.printStackTrace(); // 处理异常
        }

        // 调用服务层更新用户的地址信息
        uService.updateUserAddress(u);

        // 更新成功后，转发到用户列表页面
        request.getRequestDispatcher("/admin/user_list").forward(request, response);
    }
}
