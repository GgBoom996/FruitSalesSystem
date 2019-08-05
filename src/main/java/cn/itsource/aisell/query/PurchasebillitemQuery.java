package cn.itsource.aisell.query;

import cn.itsource.aisell.domain.Purchasebill;
import cn.itsource.aisell.domain.Purchasebillitem;
import com.github.wenhao.jpa.Specifications;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.time.DateUtils;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * 主要接收前台传递的数据
 */
public class PurchasebillitemQuery extends BaseQuery{
    private Date beginDate;
    private Date endDate;
    private Integer status;
    //默认是根据供应商分组
    private String groupType = "o.bill.supplier.name";
    //定义一个集合，该集合专门来装？对应的值
    List<Object> params = new ArrayList<>();


    public String getWhereJpql(){
        StringBuilder whereBuilder = new StringBuilder();
        if(beginDate!=null){
            whereBuilder.append(" and  o.bill.vdate>=? ");
            params.add(beginDate);
        }
        if(endDate != null){
            whereBuilder.append(" and o.bill.vdate<? ");
            params.add(DateUtils.addDays(endDate,1));
        }
        if(status != null){
            whereBuilder.append(" and o.bill.status=? ");
            params.add(status);
        }
        return whereBuilder.toString().replaceFirst("and","where");
    }

    public Object[] getParams(){
        return params.toArray();
    }
    /**
     * 动态添加条件
     * @return
     */
    public Specification createSpec(){
        if(endDate != null){
            //方式3，apache给我们准备了一个工具类
            endDate = DateUtils.addDays(endDate, 1);
        }
        Specification<Purchasebill> specification = Specifications.<Purchasebill>and().
                ge(beginDate != null,"bill.vdate",beginDate).
                lt(endDate!=null,"bill.vdate",endDate).
                eq(status != null, "bill.status", status).build();
        return specification;
    }

    public Date getBeginDate() {
        return beginDate;
    }

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    public void setBeginDate(Date beginDate) {
        this.beginDate = beginDate;
    }

    public Date getEndDate() {
        return endDate;
    }

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public String getGroupType() {
        return groupType;
    }

    public void setGroupType(String groupType) {
        this.groupType = groupType;
    }
}