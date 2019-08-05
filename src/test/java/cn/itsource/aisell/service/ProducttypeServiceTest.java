package cn.itsource.aisell.service;
import cn.itsource.aisell.domain.Producttype;
import cn.itsource.aisell.BaseTest;
import cn.itsource.aisell.service.IProducttypeService;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public class ProducttypeServiceTest extends BaseTest {
    @Autowired
    private IProducttypeService producttypeService;


    @Test
    public void test(){
        List<Producttype> producttypes = producttypeService.findAll();
        for (Producttype producttype : producttypes) {
            System.out.println(producttype);
        }
    }


}