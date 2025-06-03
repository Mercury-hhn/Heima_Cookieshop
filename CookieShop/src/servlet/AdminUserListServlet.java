package servlet;

import model.Page;
import service.UserService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * 管理员用户列表的 Servlet，处理用户列表的分页显示
 * 支持分页展示用户数据
 */
@WebServlet(name = "admin_user_list", urlPatterns = "/admin/user_list")
public class AdminUserListServlet extends HttpServlet {

    // 用户服务层对象，用于处理用户列表的分页查询
    private UserService uService = new UserService();

    /**
     * 处理 GET 请求：获取当前页码并查询用户列表，转发到用户列表页面
     *
     * @param request  HTTP 请求
     * @param response HTTP 响应
     * @throws ServletException
     * @throws IOException
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        // 获取当前页码，默认为第 1 页
        int pageNumber = 1;
        if (request.getParameter("pageNumber") != null) {
            try {
                pageNumber = Integer.parseInt(request.getParameter("pageNumber"));
            }
            catch (Exception e) {
                // 捕获异常时保持默认页码为 1
            }
        }

        // 如果页码小于等于 0，强制设为第 1 页
        if (pageNumber <= 0) pageNumber = 1;

        // 调用服务层获取分页数据
        Page p = uService.getUserPage(pageNumber);

        // 如果查询结果总页数为 0，设置默认为 1 页
        if (p.getTotalPage() == 0) {
            p.setTotalPage(1);
            p.setPageNumber(1);
        } else {
            // 如果页码超过总页数，则调整为最大页数
            if (pageNumber >= p.getTotalPage() + 1) {
                p = uService.getUserPage(pageNumber);
            }
        }

        // 将分页结果传递到前端页面
        request.setAttribute("p", p);

        // 转发到用户列表页面（user_list.jsp）进行展示
        request.getRequestDispatcher("/admin/user_list.jsp").forward(request, response);
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
    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        this.doGet(req, resp);
    }
}
