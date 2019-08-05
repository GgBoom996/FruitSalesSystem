package cn.itsource.aisell.service;
import cn.itsource.aisell.domain.Department;
import cn.itsource.aisell.BaseTest;
import cn.itsource.aisell.service.IDepartmentService;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public class DepartmentServiceTest extends BaseTest {
    @Autowired
    private IDepartmentService departmentService;


    @Test
    public void test(){
        List<Department> departments = departmentService.findAll();
        for (Department department : departments) {
            System.out.println(department);
        }
    }


}