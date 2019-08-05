package cn.itsource.aisell.domain;

import javax.persistence.*;
import java.math.BigDecimal;

@Entity
@Table(name="product")
public class Product extends BaseDomain {

    private String name;
        
    private String color;
        
    private String pic;
        
    private String smallPic;
        
    private BigDecimal costPrice;
        
    private BigDecimal salePrice;

    @ManyToOne(fetch = FetchType.LAZY,optional = false)
    @JoinColumn(name = "types_id")
    private Producttype types;

    @ManyToOne(fetch = FetchType.LAZY,optional = false)
    @JoinColumn(name = "unit_id")
    private Systemdictionarydetail unit;

    @ManyToOne(fetch = FetchType.LAZY,optional = false)
    @JoinColumn(name = "brand_id")
    private Systemdictionarydetail brand;

    public Producttype getTypes() {
        return types;
    }

    public void setTypes(Producttype types) {
        this.types = types;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
    
    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }
    
    public String getPic() {
        return pic;
    }

    public void setPic(String pic) {
        this.pic = pic;
    }

    public String getSmallPic() {
        return smallPic;
    }

    public void setSmallPic(String smallPic) {
        this.smallPic = smallPic;
    }

    public BigDecimal getCostPrice() {
        return costPrice;
    }

    public void setCostPrice(BigDecimal costPrice) {
        this.costPrice = costPrice;
    }

    public BigDecimal getSalePrice() {
        return salePrice;
    }

    public void setSalePrice(BigDecimal salePrice) {
        this.salePrice = salePrice;
    }

/*   public Producttype getTypes() {
        return types;
    }

    public void setTypes(Producttype types) {
        this.types = types;
    }*/

    public Systemdictionarydetail getUnit() {
        return unit;
    }

    public void setUnit(Systemdictionarydetail unit) {
        this.unit = unit;
    }

    public Systemdictionarydetail getBrand() {
        return brand;
    }

    public void setBrand(Systemdictionarydetail brand) {
        this.brand = brand;
    }
}