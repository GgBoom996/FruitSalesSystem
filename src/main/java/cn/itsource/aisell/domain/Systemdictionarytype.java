package cn.itsource.aisell.domain;

import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * (Systemdictionarytype)实体类
 *
 * @author 老孙
 * @since 2019-05-09 10:08:42
 */
@Entity
@Table(name = "systemdictionarytype")
public class Systemdictionarytype extends BaseDomain {
    //品牌编号
    public static final String BRAND = "productBrand";
    //单位编号
    public static final String UNIT = "productUnit";


    private String sn;
        
    private String name;

    
    public String getSn() {
        return sn;
    }

    public void setSn(String sn) {
        this.sn = sn;
    }
    
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

}