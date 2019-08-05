package cn.itsource.aisell.service;
import cn.itsource.aisell.domain.Purchasebillitem;
import cn.itsource.aisell.BaseTest;
import cn.itsource.aisell.service.IPurchasebillitemService;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public class PurchasebillitemServiceTest extends BaseTest {
    @Autowired
    private IPurchasebillitemService purchasebillitemService;


    @Test
    public void test(){
        List<Purchasebillitem> purchasebillitems = purchasebillitemService.findAll();
        for (Purchasebillitem purchasebillitem : purchasebillitems) {
            System.out.println(purchasebillitem);
        }
    }


}