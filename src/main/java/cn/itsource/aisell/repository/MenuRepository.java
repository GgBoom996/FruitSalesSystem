package cn.itsource.aisell.repository;
import cn.itsource.aisell.domain.Employee;
import cn.itsource.aisell.domain.Menu;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface MenuRepository extends BaseRepository<Menu,Long> {

    @Query("select distinct m from Employee o join o.roles r join r.permissions p join p.menu m where o=?1")
    List<Menu> findMenuByLoginUser(Employee loginUser);
}