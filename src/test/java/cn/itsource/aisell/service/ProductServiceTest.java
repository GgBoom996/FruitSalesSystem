package cn.itsource.aisell.service;
import cn.itsource.aisell.domain.Product;
import cn.itsource.aisell.BaseTest;
import cn.itsource.aisell.service.IProductService;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public class ProductServiceTest extends BaseTest {
    @Autowired
    private IProductService productService;


    @Test
    public void test(){
        List<Product> products = productService.findAll();
        for (Product product : products) {
            System.out.println(product);
        }
    }


}