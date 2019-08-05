package cn.itsource.aisell.repository;
import cn.itsource.aisell.domain.Systemdictionarydetail;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface SystemdictionarydetailRepository extends BaseRepository<Systemdictionarydetail,Long> {
    @Query("select d from Systemdictionarydetail d join d.types t where t.sn=?1")
    List<Systemdictionarydetail> findDetailsBySn(String sn);

}