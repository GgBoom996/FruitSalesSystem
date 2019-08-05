package cn.itsource.aisell.service;
import cn.itsource.aisell.domain.Purchasebillitem;
import cn.itsource.aisell.domain.vo.PurchasebillItemVo;
import cn.itsource.aisell.query.PurchasebillitemQuery;

import java.util.List;
import java.util.Map;

public interface IPurchasebillitemService extends IBaseService<Purchasebillitem, Long> {
    /**
     * 查询明细根据查询条件
     * @param query
     * @return
     */
    List<PurchasebillItemVo> findItemsByQuery(PurchasebillitemQuery query);


    /**
     * 查询分组报表 根据条件来做
     * @param query
     * @return
     */
    List<Map<String, Object>> findGroupByQuery(PurchasebillitemQuery query);

}