package cn.itsource.aisell.service;
import cn.itsource.aisell.domain.Stockincomebill;
import cn.itsource.aisell.BaseTest;
import cn.itsource.aisell.service.IStockincomebillService;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public class StockincomebillServiceTest extends BaseTest {
    @Autowired
    private IStockincomebillService stockincomebillService;


    @Test
    public void test(){
        List<Stockincomebill> stockincomebills = stockincomebillService.findAll();
        for (Stockincomebill stockincomebill : stockincomebills) {
            System.out.println(stockincomebill);
        }
    }


}