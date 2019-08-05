package cn.itsource.aisell.service;
import cn.itsource.aisell.domain.Purchasebill;
import cn.itsource.aisell.BaseTest;
import cn.itsource.aisell.service.IPurchasebillService;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public class PurchasebillServiceTest extends BaseTest {
    @Autowired
    private IPurchasebillService purchasebillService;


    @Test
    public void test(){
        List<Purchasebill> purchasebills = purchasebillService.findAll();
        for (Purchasebill purchasebill : purchasebills) {
            System.out.println(purchasebill);
        }
    }


}