package cn.itsource.aisell.service.impl;
import cn.itsource.aisell.domain.Department;
import cn.itsource.aisell.repository.DepartmentRepository;
import cn.itsource.aisell.service.IDepartmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class DepartmentServiceImpl extends BaseServiceImpl<Department,Long> implements IDepartmentService {
    @Autowired
    private DepartmentRepository departmentRepository;

    @Override
    public Department findDepartmentByName(String name) {
        return departmentRepository.findByName(name);
    }

}