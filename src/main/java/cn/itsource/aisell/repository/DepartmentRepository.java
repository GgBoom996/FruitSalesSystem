package cn.itsource.aisell.repository;
import cn.itsource.aisell.domain.Department;

public interface DepartmentRepository extends BaseRepository<Department,Long> {
    Department findByName(String name);
}