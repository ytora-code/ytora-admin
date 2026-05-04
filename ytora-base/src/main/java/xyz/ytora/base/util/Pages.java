package xyz.ytora.base.util;

import jakarta.servlet.http.HttpServletRequest;
import xyz.ytora.base.exception.BaseException;
import xyz.ytora.sqlux.orm.Page;
import xyz.ytora.toolkit.convert.Converts;

import java.util.ArrayList;
import java.util.List;

/**
 * 分页工具类
 *
 * <p>提供了常用的分页辅助API</p>
 *
 * @author ytora 
 * @since 1.0
 */
public final class Pages {

    /**
     * 从当前请求对象中产生分页对象
     *
     * @return 分页对象
     * @param <T> 分页记录类型
     */
    public static <T> Page<T> getPage() {
        HttpServletRequest request = HttpUtil.getReq();
        if (request == null) {
            throw new BaseException("当前非web环境，无法从自动获取分页对象");
        }
        Integer pageNo = Converts.convert(request.getParameter("pageNo"), Integer.class, 1);
        Integer pageSize = Converts.convert(request.getParameter("pageSize"), Integer.class, 10);

        return new Page<>(pageNo, pageSize);
    }

    /**
     * 从当前请求对象中产生分页对象
     *
     * @param clazz 分页记录类型
     *
     * @return 分页对象
     * @param <T> 分页记录类型
     */
    public static <T> Page<T> getPage(Class<T> clazz) {
        HttpServletRequest request = HttpUtil.getReq();
        if (request == null) {
            throw new BaseException("当前非web环境，无法从自动获取分页对象");
        }
        Integer pageNo = Converts.convert(request.getParameter("pageNo"), Integer.class, 1);
        Integer pageSize = Converts.convert(request.getParameter("pageSize"), Integer.class, 10);

        return new Page<>(clazz, pageNo, pageSize);
    }

    /**
     * 对list进行分页操作
     *
     * @param pageNo 当前页
     * @param pageSize 每页尺寸
     * @param list 原始集合
     * @return 分页后的数据
     * @param <T> 数据类型
     */
    public static <T> Page<T> toPage(Integer pageNo, Integer pageSize, List<T> list) {
        return toPage(new Page<>(pageNo, pageSize), list);
    }

    /**
     * 对list进行分页操作
     *
     * @param page 分页对象
     * @param list 原始集合
     * @return 分页后的数据
     * @param <T> 数据类型
     */
    public static <T> Page<T> toPage(Page<T> page, List<T> list) {
        int pageNo = page.getPageNo();
        int pageSize = page.getPageSize();

        //计算偏移量
        int startIndex = (pageNo - 1) * pageSize;
        if (list.size() <= pageSize) {
            page.setPages(1L)
                    .setPageSize(pageSize)
                    .setPageNo(1)
                    .setTotal((long) list.size())
                    .setRecords(list);
        }
        //计算分页数据
        long pages;
        if (list.size() % pageSize == 0) {
            pages = list.size() / pageSize;
        } else {
            pages = list.size() / pageSize + 1;
        }
        if (pageNo * pageSize > list.size()) {
            pageNo = (int) pages;
        }

        List<T> records = new ArrayList<>();
        for (long i = startIndex; i < startIndex + pageSize && i < list.size(); i++) {
            records.add(list.get((int) i));
        }
        //组装分页数据
        page.setPages(pages)
                .setPageSize(pageSize)
                .setPageNo(pageNo)
                .setTotal((long) list.size())
                .setRecords(records);
        return page;
    }

}
