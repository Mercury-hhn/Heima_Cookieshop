package filter;

import model.User;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * 管理员权限过滤器：拦截所有 /admin/* 路径下的请求，检查当前用户是否已登录且具有管理员权限。
 */
@WebFilter(filterName = "AdminFilter", urlPatterns = "/admin/*")
public class AdminFilter implements Filter {
    /**
     * 销毁方法：Filter 被移除或服务器关闭时调用，可用于释放资源（此处无需实现）。
     */
    @Override
    public void destroy() {
        // 无需资源清理
    }

    /**
     * 过滤方法：每次请求到 /admin/* 时执行
     *
     * @param req  ServletRequest，实际为 HttpServletRequest
     * @param resp ServletResponse，实际为 HttpServletResponse
     * @param chain FilterChain，用于继续执行下一个过滤器或目标资源
     * @throws ServletException
     * @throws IOException
     */
    @Override
    public void doFilter(ServletRequest req, ServletResponse resp, FilterChain chain)
            throws ServletException, IOException {
        // 将通用 ServletRequest/Response 转为 HTTP 版本以便使用特定方法
        HttpServletRequest  request  = (HttpServletRequest) req;
        HttpServletResponse response = (HttpServletResponse) resp;

        // 从 session 中获取当前用户对象
        User u = (User) request.getSession().getAttribute("user");

        // 如果用户未登录或不是管理员，则重定向到首页
        if (u == null || !u.isIsadmin()) {
            response.sendRedirect("../index.jsp");
            return;  // 拦截后终止后续处理
        }

        // 用户具有管理员权限，继续处理请求
        chain.doFilter(req, resp);
    }

    /**
     * 初始化方法：Filter 初始化时调用，可用于读取配置信息（此处无需实现）。
     *
     * @param config FilterConfig，包含 Filter 的配置参数
     * @throws ServletException
     */
    @Override
    public void init(FilterConfig config) throws ServletException {
        // 无需初始化参数
    }
}