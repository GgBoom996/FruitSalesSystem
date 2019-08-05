package cn.itsource.aisell.repository;

import cn.itsource.aisell.domain.Employee;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface EmployeeRepository extends BaseRepository<Employee,Long> {

    //条件查询，根据用户名查询对象
    Employee findByUsername(String username);

    //查询员工，根据部门来查询
    @Query("select o from Employee o where o.department.name=?1")
    List<Employee> findBuyersByDeptName(String deptName);
}
