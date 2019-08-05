package cn.itsource.aisell.service.impl;

import cn.itsource.aisell.domain.Purchasebillitem;
import cn.itsource.aisell.domain.vo.PurchasebillItemVo;
import cn.itsource.aisell.query.PurchasebillitemQuery;
import cn.itsource.aisell.repository.PurchasebillitemRepository;
import cn.itsource.aisell.service.IPurchasebillitemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class PurchasebillitemServiceImpl extends BaseServiceImpl<Purchasebillitem,Long> implements IPurchasebillitemService {
    @Autowired
    private PurchasebillitemRepository purchasebillitemRepository;

    @Override
    public List<PurchasebillItemVo> findItemsByQuery(PurchasebillitemQuery query) {
        //根据条件查询采购订单明细列表
        List<Purchasebillitem> items = super.findAllByQuery(query);
        //定义一个新的集合，专门来装item对象转为vo对象
        List<PurchasebillItemVo> purchasebillItemVos = new ArrayList<>();
        for (Purchasebillitem item : items) {
            //把item对象转为vo对象
            PurchasebillItemVo purchasebillItemVo = new PurchasebillItemVo(item,query.getGroupType());
            //把转换后的对象装进集合中，方便前端取值
            purchasebillItemVos.add(purchasebillItemVo);
        }
        return purchasebillItemVos;
    }

    @Override
    public List<Map<String, Object>> findGroupByQuery(PurchasebillitemQuery query) {

        //使用jpql语句的时候，当查询的列和domain中的字段不能完全对应，返回都是一个object[]
        String jpql = "select "+query.getGroupType()+",sum(o.amount) from Purchasebillitem o "
                +query.getWhereJpql()+" group by "+query.getGroupType();

        List<Object[]> values = purchasebillitemRepository.findByJpql(jpql,query.getParams());
        //定义一个集合，该集合来装循环迭代的map(分组的数据)
        List<Map<String, Object>> list = new ArrayList<>();
        for (Object[] value : values) {
            Map<String, Object> map = new HashMap<>();
            map.put("name", value[0]);
            map.put("y", value[1]);
            list.add(map);
        }
        return list;
    }
}