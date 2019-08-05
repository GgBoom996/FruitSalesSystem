package cn.itsource.aisell.service;
import cn.itsource.aisell.domain.Stockincomebillitem;
import cn.itsource.aisell.BaseTest;
import cn.itsource.aisell.service.IStockincomebillitemService;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public class StockincomebillitemServiceTest extends BaseTest {
    @Autowired
    private IStockincomebillitemService stockincomebillitemService;


    @Test
    public void test(){
        List<Stockincomebillitem> stockincomebillitems = stockincomebillitemService.findAll();
        for (Stockincomebillitem stockincomebillitem : stockincomebillitems) {
            System.out.println(stockincomebillitem);
        }
    }


}