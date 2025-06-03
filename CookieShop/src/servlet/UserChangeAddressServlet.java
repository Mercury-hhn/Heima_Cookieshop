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
 * 用户更改收货地址的 Servlet，处理用户修改个人收货信息的操作
 * 支持通过表单提交新的收货地址并保存更新
 */
@WebServlet(name = "user_changeaddress", urlPatterns = "/user_changeaddress")
public class UserChangeAddressServlet extends HttpServlet {

    // 用户服务层对象，用于处理用户地址更新逻辑
    private UserService uService = new UserService();

    /**
     * 处理 POST 请求：接收用户提交的新的地址信息，更新用户地址
     *
     * @param request  HTTP 请求
     * @param response HTTP 响应
     * @throws ServletException
     * @throws IOException
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        // 获取当前会话中的用户对象
        User loginUser = (User) request.getSession().getAttribute("user");

        try {
            // 使用 BeanUtils 工具类将前端提交的表单参数映射到用户对象中
            BeanUtils.copyProperties(loginUser, request.getParameterMap());
        } catch (IllegalAccessException | InvocationTargetException e) {
            e.printStackTrace(); // 处理异常
        }

        // 调用服务层更新用户的地址信息
        uService.updateUserAddress(loginUser);

        // 设置成功消息并转发到用户中心页面
        request.setAttribute("msg", "收件信息更新成功！");
        request.getRequestDispatcher("/user_center.jsp").forward(request, response);
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
