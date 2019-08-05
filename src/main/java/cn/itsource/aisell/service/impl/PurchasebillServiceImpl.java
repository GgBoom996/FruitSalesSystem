package cn.itsource.aisell.service.impl;
import cn.itsource.aisell.domain.Purchasebill;
import cn.itsource.aisell.repository.PurchasebillRepository;
import cn.itsource.aisell.service.IPurchasebillService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PurchasebillServiceImpl extends BaseServiceImpl<Purchasebill,Long> implements IPurchasebillService {
    @Autowired
    private PurchasebillRepository purchasebillRepository;
}