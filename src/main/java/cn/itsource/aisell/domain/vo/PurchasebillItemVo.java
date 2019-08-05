package cn.itsource.aisell.domain.vo;

import cn.itsource.aisell.domain.Purchasebillitem;
import com.fasterxml.jackson.annotation.JsonFormat;
import org.apache.commons.lang3.time.DateUtils;

import java.math.BigDecimal;
import java.util.Calendar;
import java.util.Date;

/**
 * 采购订单报表所需要的字段
 */
public class PurchasebillItemVo {
    private Long id;
    private String supplierName;
    private String buyerName;
    private String productName;
    private String productTypeName;
    private Date vdate;
    private BigDecimal num;
    private BigDecimal price;
    private BigDecimal amount;
    private Integer status;
    //动态分组
    private String groupName;
    public PurchasebillItemVo(Purchasebillitem item, String groupType){
        this.id = item.getId();
        this.supplierName = item.getBill().getSupplier().getName();
        this.buyerName = item.getBill().getBuyer().getUsername();
        this.productName = item.getProduct().getName();
        this.productTypeName = item.getProduct().getTypes().getName();
        this.vdate = item.getBill().getVdate();
        this.num = item.getNum();
        this.price = item.getPrice();
        this.amount = item.getAmount();
        this.status = item.getBill().getStatus();

        switch (groupType){
            case "o.bill.supplier.name":
                this.groupName = this.supplierName;
                break;
            case "o.bill.buyer.username":
                this.groupName = this.buyerName;
                break;
            case "month(o.bill.vdate)":
                this.groupName = (DateUtils.toCalendar(this.vdate).get(Calendar.MONTH)+1)+"月";
                break;

        }
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getSupplierName() {
        return supplierName;
    }

    public void setSupplierName(String supplierName) {
        this.supplierName = supplierName;
    }

    public String getBuyerName() {
        return buyerName;
    }

    public void setBuyerName(String buyerName) {
        this.buyerName = buyerName;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public String getProductTypeName() {
        return productTypeName;
    }

    public void setProductTypeName(String productTypeName) {
        this.productTypeName = productTypeName;
    }

    @JsonFormat(pattern = "yyyy-MM-dd",timezone = "GMT+8")
    public Date getVdate() {
        return vdate;
    }

    public void setVdate(Date vdate) {
        this.vdate = vdate;
    }

    public BigDecimal getNum() {
        return num;
    }

    public void setNum(BigDecimal num) {
        this.num = num;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public String getGroupName() {
        return groupName;
    }

    public void setGroupName(String groupName) {
        this.groupName = groupName;
    }
}
