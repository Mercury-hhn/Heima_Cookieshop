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
 * 管理员添加商品的 Servlet，处理 multipart/form-data 请求，
 * 支持上传商品图片及设置基本商品属性
 */
@WebServlet(name = "admin_goods_add", urlPatterns = "/admin/goods_add")
public class AdminGoodsAddServlet extends HttpServlet {

    // 商品业务服务类，用于执行插入操作
    private GoodsService gService = new GoodsService();

    /**
     * 处理 GET 请求：实际用于处理添加商品的表单提交（包含文本 + 文件）
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        // 创建文件上传工厂并构建上传解析器
        DiskFileItemFactory factory = new DiskFileItemFactory();
        ServletFileUpload upload = new ServletFileUpload(factory);

        try {
            // 将请求解析为表单字段和文件项列表
            List<FileItem> list = upload.parseRequest(request);
            Goods g = new Goods(); // 用于封装表单数据的商品对象

            for (FileItem item : list) {
                if (item.isFormField()) {
                    // 处理普通表单字段
                    switch (item.getFieldName()) {
                        case "name":
                            g.setName(item.getString("utf-8"));
                            break;
                        case "price":
                            g.setPrice(Integer.parseInt(item.getString("utf-8")));
                            break;
                        case "intro":
                            g.setIntro(item.getString("utf-8"));
                            break;
                        case "stock":
                            g.setStock(Integer.parseInt(item.getString("utf-8")));
                            break;
                        case "typeid":
                            g.setTypeid(Integer.parseInt(item.getString("utf-8")));
                            break;
                    }
                } else {
                    // 处理文件上传字段（如 cover, image1, image2）
                    if (item.getInputStream().available() <= 0) continue;

                    // 获取原始文件名并提取扩展名
                    String fileName = item.getName();
                    fileName = fileName.substring(fileName.lastIndexOf("."));

                    // 生成唯一文件名并构建路径
                    fileName = "/" + new Date().getTime() + fileName;
                    String path = this.getServletContext().getRealPath("/picture") + fileName;

                    // 将上传文件保存到服务器指定路径
                    InputStream in = item.getInputStream();
                    FileOutputStream out = new FileOutputStream(path);
                    byte[] buffer = new byte[1024];
                    int len = 0;
                    while ((len = in.read(buffer)) > 0) {
                        out.write(buffer);
                    }
                    in.close();
                    out.close();
                    item.delete(); // 清理临时文件

                    // 将上传后的文件路径设置到商品对象
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

            // 调用服务层插入商品到数据库
            gService.insert(g);

            // 重定向到商品列表页面
            request.getRequestDispatcher("/admin/goods_list").forward(request, response);
        } catch (FileUploadException e) {
            e.printStackTrace(); // 文件上传解析异常
        }
    }

    /**
     * 处理 POST 请求：实际委托给 doGet() 统一处理上传逻辑
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        doGet(request, response);
    }
}
