package cn.itsource.aisell.domain;

import cn.afterturn.easypoi.excel.annotation.Excel;

import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name="department")
public class Department extends BaseDomain {
                                            
                
    @Excel(name = "部门")
    private String name;

    
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

}