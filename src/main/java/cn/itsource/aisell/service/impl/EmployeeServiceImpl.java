package cn.itsource.aisell.service.impl;

import cn.itsource.aisell.common.MD5Util;
import cn.itsource.aisell.domain.Employee;
import cn.itsource.aisell.repository.EmployeeRepository;
import cn.itsource.aisell.service.IEmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EmployeeServiceImpl extends BaseServiceImpl<Employee,Long> implements IEmployeeService {
    @Autowired
    private EmployeeRepository employeeRepository;
    @Override
    public Employee findEmployeeByUsername(String username) {
        return employeeRepository.findByUsername(username);
    }

    @Override
    public List<Employee> findBuyers() {
        return employeeRepository.findBuyersByDeptName("采购部");
    }


    @Override
    public void save(Employee employee) {
        //解决没有选中部门的问题
        if(employee.getDepartment().getId() == null){
            employee.setDepartment(null);
        }
        if(employee.getId() == null){
            employee.setPassword(MD5Util.createMd5(employee.getPassword()));
        }
        super.save(employee);
    }
}
