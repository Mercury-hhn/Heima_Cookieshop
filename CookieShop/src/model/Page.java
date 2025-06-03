package model;

import java.util.List;

/**
 * 分页实体类，封装分页信息，包括当前页、每页大小、总记录数、总页数及当前页数据列表
 */
public class Page {
    // 当前页码，从1开始
    private int pageNumber;
    // 每页显示记录数
    private int pageSize;
    // 总记录数
    private int totalCount;
    // 总页数，根据 totalCount 与 pageSize 计算得出
    private int totalPage;
    // 分页后的数据列表，泛型使用 Object，可根据实际类型进行转换
    private List<Object> list;

    /**
     * 一次性设置 pageSize 与 totalCount，并计算 totalPage
     *
     * @param pageSize   每页记录数
     * @param totalCount 总记录数
     */
    public void SetPageSizeAndTotalCount(int pageSize, int totalCount) {
        // 设置每页大小
        this.pageSize = pageSize;
        // 设置总记录数
        this.totalCount = totalCount;
        // 计算总页数：向上取整
        this.totalPage = (int) Math.ceil((double) totalCount / pageSize);
    }

    /**
     * 获取当前页码
     *
     * @return 当前页码
     */
    public int getPageNumber() {
        return pageNumber;
    }

    /**
     * 设置当前页码
     *
     * @param pageNumber 当前页码，从1开始
     */
    public void setPageNumber(int pageNumber) {
        this.pageNumber = pageNumber;
    }

    /**
     * 获取每页记录数
     *
     * @return 每页大小
     */
    public int getPageSize() {
        return pageSize;
    }

    /**
     * 设置每页记录数
     *
     * @param pageSize 每页大小
     */
    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }

    /**
     * 获取总记录数
     *
     * @return 总记录数
     */
    public int getTotalCount() {
        return totalCount;
    }

    /**
     * 设置总记录数
     *
     * @param totalCount 总记录数
     */
    public void setTotalCount(int totalCount) {
        this.totalCount = totalCount;
    }

    /**
     * 获取总页数
     *
     * @return 总页数
     */
    public int getTotalPage() {
        return totalPage;
    }

    /**
     * 设置总页数（一般由 SetPageSizeAndTotalCount 计算后使用）
     *
     * @param totalPage 总页数
     */
    public void setTotalPage(int totalPage) {
        this.totalPage = totalPage;
    }

    /**
     * 获取当前页的数据列表
     *
     * @return 分页数据列表
     */
    public List<Object> getList() {
        return list;
    }

    /**
     * 设置当前页的数据列表
     *
     * @param list 分页数据列表
     */
    public void setList(List<Object> list) {
        this.list = list;
    }
}
