package cn.itsource.aisell.domain;

import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name="supplier")
public class Supplier extends BaseDomain {
                                            
                
    //供应商名
    private String name;

    
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

}