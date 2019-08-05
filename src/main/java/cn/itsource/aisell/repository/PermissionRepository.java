package cn.itsource.aisell.repository;
import cn.itsource.aisell.domain.Employee;
import cn.itsource.aisell.domain.Permission;
import org.springframework.data.jpa.repository.Query;

import java.util.Set;

/**
 * JPQL语句连表查询的2大准则：
 *          1.如果要连表是连接的对象属性
 *          2.不需要加on它会自动消除笛卡尔积
 */
public interface PermissionRepository extends BaseRepository<Permission,Long> {


    @Query("select distinct p.sn from Employee o join o.roles r join r.permissions p where o=?1")
    Set<String> findPermissionsByLoginUser(Employee loginUser);
}