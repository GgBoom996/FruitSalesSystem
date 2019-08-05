package cn.itsource.aisell.domain;

import javax.persistence.*;

@Entity
@Table(name="systemdictionarydetail")
public class Systemdictionarydetail extends BaseDomain {
    private String name;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "types_id")
    private Systemdictionarytype types;

    public Systemdictionarytype getTypes() {
        return types;
    }

    public void setTypes(Systemdictionarytype types) {
        this.types = types;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
    

}