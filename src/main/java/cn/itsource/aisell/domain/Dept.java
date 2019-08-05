package cn.itsource.aisell.domain;


import javax.persistence.Entity;
import javax.persistence.Table;


/**
 * (Dept)实体类
 *
 * @author jackdlnar
 * @since 2019-06-09 11:57:48
 */
@Entity
@Table(name = "dept")
public class Dept extends BaseDomain{
                                            
                

    

    private String name;

    
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

}