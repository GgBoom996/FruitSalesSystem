package cn.itsource.aisell.service;
import cn.itsource.aisell.domain.Department;

public interface IDepartmentService extends IBaseService<Department, Long> {
    /**
     * 根据名称查询部门对象
     * @param name
     * @return
     */
    Department findDepartmentByName(String name);
}