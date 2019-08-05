package cn.itsource.aisell.service;
import cn.itsource.aisell.domain.Systemdictionarydetail;
import cn.itsource.aisell.BaseTest;
import cn.itsource.aisell.service.ISystemdictionarydetailService;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public class SystemdictionarydetailServiceTest extends BaseTest {
    @Autowired
    private ISystemdictionarydetailService systemdictionarydetailService;


    @Test
    public void test(){
        List<Systemdictionarydetail> systemdictionarydetails = systemdictionarydetailService.findAll();
        for (Systemdictionarydetail systemdictionarydetail : systemdictionarydetails) {
            System.out.println(systemdictionarydetail);
        }
    }


}