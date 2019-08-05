package cn.itsource.aisell.service;

import cn.itsource.aisell.BaseTest;
import cn.itsource.aisell.common.MD5Util;
import cn.itsource.aisell.domain.Employee;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public class EmployeeServiceTest extends BaseTest {
    @Autowired
    private IEmployeeService employeeService;


    @Test
    public void test(){
        Employee one = employeeService.findOne(1L);
        one.setPassword(MD5Util.createMd5(one.getUsername()));
    }
}
