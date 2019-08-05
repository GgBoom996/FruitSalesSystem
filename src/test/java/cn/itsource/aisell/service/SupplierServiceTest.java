package cn.itsource.aisell.service;
import cn.itsource.aisell.domain.Supplier;
import cn.itsource.aisell.BaseTest;
import cn.itsource.aisell.service.ISupplierService;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public class SupplierServiceTest extends BaseTest {
    @Autowired
    private ISupplierService supplierService;


    @Test
    public void test(){
        List<Supplier> suppliers = supplierService.findAll();
        for (Supplier supplier : suppliers) {
            System.out.println(supplier);
        }
    }


}