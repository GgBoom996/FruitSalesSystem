package cn.itsource.aisell.service;
import cn.itsource.aisell.domain.Productstock;
import cn.itsource.aisell.BaseTest;
import cn.itsource.aisell.service.IProductstockService;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public class ProductstockServiceTest extends BaseTest {
    @Autowired
    private IProductstockService productstockService;


    @Test
    public void test(){
        List<Productstock> productstocks = productstockService.findAll();
        for (Productstock productstock : productstocks) {
            System.out.println(productstock);
        }
    }


}