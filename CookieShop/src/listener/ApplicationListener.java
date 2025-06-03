package listener;

import service.TypeService;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;
import javax.servlet.http.HttpSessionAttributeListener;
import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;
import javax.servlet.http.HttpSessionBindingEvent;

/**
 * 应用级监听器：
 * - 在 Web 应用启动时（ServletContext 初始化）将商品类别列表加载到应用上下文
 * - 监听 HttpSession 的创建、销毁以及属性变更事件，方便后续扩展会话管理逻辑
 */
@WebListener  // 标记为监听器，自动注册到 Servlet 容器
public class ApplicationListener implements ServletContextListener,
        HttpSessionListener, HttpSessionAttributeListener {

    // Service 层对象，用于获取所有商品类型
    private TypeService tsService = new TypeService();

    /**
     * 公共无参构造方法，Servlet 规范要求
     */
    public ApplicationListener() {
        // 可在此进行必要的初始化
    }

    // -------------------------------------------------------
    // ServletContextListener 实现
    // -------------------------------------------------------

    /**
     * 当 Web 应用启动、ServletContext 初始化完成后调用
     * 将从数据库中查询到的所有商品类型存入应用范围 (application) 中，
     * 便于各个 Servlet 或 JSP 页面获取
     *
     * @param sce 容器提供的上下文事件对象，包含 ServletContext 引用
     */
    @Override
    public void contextInitialized(ServletContextEvent sce) {
        // 查询所有类型并设置为属性 "typeList"
        sce.getServletContext().setAttribute("typeList", tsService.GetAllType());
    }

    /**
     * 当 Web 应用被关闭或重新部署时调用
     * 可在此处释放资源、写日志等（目前无特殊需求）
     *
     * @param sce 容器提供的上下文事件对象
     */
    @Override
    public void contextDestroyed(ServletContextEvent sce) {
        // 无需清理操作
    }

    // -------------------------------------------------------
    // HttpSessionListener 实现
    // -------------------------------------------------------

    /**
     * 当新的 HttpSession 被创建时调用
     * 可用于记录会话创建时间、初始化会话属性等
     *
     * @param se 包含新创建的 HttpSession 对象
     */
    @Override
    public void sessionCreated(HttpSessionEvent se) {
        // Session 创建后扩展逻辑（暂未实现）
    }

    /**
     * 当 HttpSession 被销毁（超时或手动失效）时调用
     * 可用于清理会话资源、记录会话结束日志等
     *
     * @param se 包含被销毁的 HttpSession 对象
     */
    @Override
    public void sessionDestroyed(HttpSessionEvent se) {
        // Session 销毁后扩展逻辑（暂未实现）
    }

    // -------------------------------------------------------
    // HttpSessionAttributeListener 实现
    // -------------------------------------------------------

    /**
     * 当向 HttpSession 中添加属性时调用
     *
     * @param sbe 包含属性名称和值的事件对象
     */
    @Override
    public void attributeAdded(HttpSessionBindingEvent sbe) {
        // 属性添加后扩展逻辑（暂未实现）
    }

    /**
     * 当从 HttpSession 中移除属性时调用
     *
     * @param sbe 包含被移除属性名称和值的事件对象
     */
    @Override
    public void attributeRemoved(HttpSessionBindingEvent sbe) {
        // 属性移除后扩展逻辑（暂未实现）
    }

    /**
     * 当替换 HttpSession 中已有属性时调用
     *
     * @param sbe 包含属性名称及新旧值的事件对象
     */
    @Override
    public void attributeReplaced(HttpSessionBindingEvent sbe) {
        // 属性替换后扩展逻辑（暂未实现）
    }
}
