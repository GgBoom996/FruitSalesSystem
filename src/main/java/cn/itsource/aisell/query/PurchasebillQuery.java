package cn.itsource.aisell.query;

import cn.itsource.aisell.domain.Purchasebill;
import com.github.wenhao.jpa.Specifications;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.time.DateUtils;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

/**
 * 主要接收前台传递的数据
 */
public class PurchasebillQuery extends BaseQuery{
    private Date beginDate;
    private Date endDate;
    private Integer status;

    /**
     * 动态添加条件
     * @return
     */
    public Specification createSpec(){
        if(endDate != null){
//            endDate = new Date(endDate.getTime() + 24 * 60 * 60 * 1000);  方式1
           /*
            方式2也太过于复杂了
             Calendar instance = Calendar.getInstance();
            instance.add(Calendar.DAY_OF_MONTH, 1);
            Date time = instance.getTime();
            System.out.println(time);
           */
            //方式3，apache给我们准备了一个工具类
            endDate = DateUtils.addDays(endDate, 1);
        }
        Specification<Purchasebill> specification = Specifications.<Purchasebill>and().
                ge(beginDate != null,"vdate",beginDate).
                lt(endDate!=null,"vdate",endDate).
                eq(status != null, "status", status).build();
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
}