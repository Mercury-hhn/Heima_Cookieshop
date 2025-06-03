package servlet;

import model.Goods;
import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import service.GoodsService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Date;
import java.util.List;

/**
 * 管理员编辑商品的 Servlet，处理商品信息的修改及图片上传
 */
@WebServlet(name = "admin_goods_edit", urlPatterns = "/admin/goods_edit")
public class AdminGoodsEditServlet extends HttpServlet {

    // 商品服务层对象，用于处理商品编辑逻辑
    private GoodsService gService = new GoodsService();

    /**
     * 处理 GET 请求：用于解析商品编辑表单数据，更新商品信息
     *
     * @param request  HTTP 请求
     * @param response HTTP 响应
     * @throws ServletException
     * @throws IOException
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        // 创建文件上传工厂并构建上传解析器
        DiskFileItemFactory factory = new DiskFileItemFactory();
        ServletFileUpload upload = new ServletFileUpload(factory);

        try {
            // 解析请求中的文件和表单数据
            List<FileItem> list = upload.parseRequest(request);
            Goods g = new Goods();
            int pageNumber = 1;
            int type = 0;

            // 遍历解析出的表单项
            for (FileItem item : list) {
                if (item.isFormField()) {
                    // 处理普通表单字段
                    switch (item.getFieldName()) {
                        case "id":
                            g.setId(Integer.parseInt(item.getString("utf-8")));
                            break;
                        case "name":
                            g.setName(item.getString("utf-8"));
                            break;
                        case "price":
                            g.setPrice(Float.parseFloat(item.getString("utf-8")));
                            break;
                        case "intro":
                            g.setIntro(item.getString("utf-8"));
                            break;
                        case "cover":
                            g.setCover(item.getString("utf-8"));
                            break;
                        case "image1":
                            g.setImage1(item.getString("utf-8"));
                            break;
                        case "image2":
                            g.setImage2(item.getString("utf-8"));
                            break;
                        case "stock":
                            g.setStock(Integer.parseInt(item.getString("utf-8")));
                            break;
                        case "typeid":
                            g.setTypeid(Integer.parseInt(item.getString("utf-8")));
                            break;
                        case "pageNumber":
                            pageNumber = Integer.parseInt(item.getString("utf-8"));
                            break;
                        case "type":
                            type = Integer.parseInt(item.getString("utf-8"));
                            break;
                    }
                } else {
                    // 处理文件上传字段（如 cover, image1, image2）
                    if (item.getInputStream().available() <= 0) continue;

                    // 获取上传的文件名并提取文件扩展名
                    String fileName = item.getName();
                    fileName = fileName.substring(fileName.lastIndexOf("."));

                    // 创建唯一文件名，并生成完整路径
                    fileName = "/" + new Date().getTime() + fileName;
                    String path = this.getServletContext().getRealPath("/picture") + fileName;

                    // 将文件保存到指定路径
                    InputStream in = item.getInputStream();
                    FileOutputStream out = new FileOutputStream(path);
                    byte[] buffer = new byte[1024];
                    int len = 0;
                    while ((len = in.read(buffer)) > 0) {
                        out.write(buffer);
                    }
                    in.close();
                    out.close();
                    item.delete(); // 删除临时文件

                    // 根据字段名更新商品图片路径
                    switch (item.getFieldName()) {
                        case "cover":
                            g.setCover("/picture" + fileName);
                            break;
                        case "image1":
                            g.setImage1("/picture" + fileName);
                            break;
                        case "image2":
                            g.setImage2("/picture" + fileName);
                            break;
                    }
                }
            }

            // 调用服务层更新商品信息
            gService.update(g);

            // 重定向到商品列表页面，传递分页信息
            request.getRequestDispatcher("/admin/goods_list?pageNumber=" + pageNumber + "&type=" + type).forward(request, response);
        } catch (FileUploadException e) {
            e.printStackTrace(); // 文件上传异常
        }
    }

    /**
     * 处理 POST 请求：转发到 doGet() 方法进行处理
     *
     * @param request  HTTP 请求
     * @param response HTTP 响应
     * @throws ServletException
     * @throws IOException
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        doGet(request, response);
    }
}
