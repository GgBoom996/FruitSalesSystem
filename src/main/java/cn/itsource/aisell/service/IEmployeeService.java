package cn.itsource.aisell.service;

import cn.itsource.aisell.domain.Employee;

import java.util.List;

public interface IEmployeeService extends IBaseService<Employee,Long>{
    Employee findEmployeeByUsername(String username);
    /**
     * 查询所有的采购员
     * @return
     */
    List<Employee> findBuyers();
}
