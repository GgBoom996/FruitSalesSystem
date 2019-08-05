package cn.itsource.aisell.query;

import org.apache.commons.lang3.StringUtils;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;

/**
 * 每个查询都有的公共属性
 */
public abstract class BaseQuery {
    //当前页
    private Integer currentPage = 1;
    //每页条数
    private Integer pageSize = 10;
    //默认排序是升序
    private String orderByType = "asc";
    //根据条件进行动态排序
    private String orderByName;


    //对结构进行抽取，约束着子类必须覆盖此抽象方法
    public abstract Specification createSpec();

    /**
     * 抽取公共的动态排序
     * @return
     */
    public Sort createSort(){
        Sort sort = null;
        if(StringUtils.isNotBlank(orderByName)){
            sort = new Sort(orderByType.toLowerCase().equals("asc")? Sort.Direction.ASC: Sort.Direction.DESC, orderByName);
        }
        return sort;
    }

    //专门提供springDataJpa分页查询的
    public Integer getJpaPage(){
        return currentPage - 1;
    }

    public Integer getCurrentPage() {
        return currentPage;
    }

    public void setCurrentPage(Integer currentPage) {
        this.currentPage = currentPage;
    }

    public Integer getPageSize() {
        return pageSize;
    }

    public void setPageSize(Integer pageSize) {
        this.pageSize = pageSize;
    }
    public String getOrderByType() {
        return orderByType;
    }

    public void setOrderByType(String orderByType) {
        this.orderByType = orderByType;
    }

    public String getOrderByName() {
        return orderByName;
    }

    public void setOrderByName(String orderByName) {
        this.orderByName = orderByName;
    }

    public void setPage(Integer page){
        this.currentPage = page;
    }
    public void setRows(Integer rows){
        this.pageSize = rows;
    }

}
