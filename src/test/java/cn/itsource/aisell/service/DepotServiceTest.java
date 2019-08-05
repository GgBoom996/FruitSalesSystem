package cn.itsource.aisell.service;
import cn.itsource.aisell.domain.Depot;
import cn.itsource.aisell.BaseTest;
import cn.itsource.aisell.service.IDepotService;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public class DepotServiceTest extends BaseTest {
    @Autowired
    private IDepotService depotService;


    @Test
    public void test(){
        List<Depot> depots = depotService.findAll();
        for (Depot depot : depots) {
            System.out.println(depot);
        }
    }


}