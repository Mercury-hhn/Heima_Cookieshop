package filter;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import java.io.IOException;

/**
 * 编码过滤器：拦截所有请求，为请求设置字符编码，解决 POST 请求中文乱码问题
 */
@WebFilter(filterName = "EncodeFilter", urlPatterns = "/*")
public class EncodeFilter implements Filter {
    /**
     * 销毁方法：Filter 被移除或应用停止时调用，可用于清理资源（此处无需实现）
     */
    @Override
    public void destroy() {
        // 无需清理资源
    }

    /**
     * 过滤方法：在请求到达 Servlet 之前，设置请求字符编码为 UTF-8
     *
     * @param req   ServletRequest 对象，实际为 HttpServletRequest
     * @param resp  ServletResponse 对象，实际为 HttpServletResponse
     * @param chain FilterChain，用于继续执行下一个过滤器或目标资源
     * @throws ServletException
     * @throws IOException
     */
    @Override
    public void doFilter(ServletRequest req, ServletResponse resp, FilterChain chain)
            throws ServletException, IOException {
        // 将请求的字符编码设置为 UTF-8
        req.setCharacterEncoding("utf-8");
        // 放行请求，继续后续过滤器或目标资源
        chain.doFilter(req, resp);
    }

    /**
     * 初始化方法：Filter 初始化时调用，可用于读取配置参数（此处无需实现）
     *
     * @param config FilterConfig，包含 Filter 的配置参数
     * @throws ServletException
     */
    @Override
    public void init(FilterConfig config) throws ServletException {
        // 无需初始化
    }
}
