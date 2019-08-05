package cn.itsource.aisell.service;
import cn.itsource.aisell.domain.Producttype;

import java.util.List;

public interface IProducttypeService extends IBaseService<Producttype, Long> {
    /**
     * 查询二级产品类型
     * @return
     */
    List<Producttype> findChildTypes();
}