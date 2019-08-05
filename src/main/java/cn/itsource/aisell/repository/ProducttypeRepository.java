package cn.itsource.aisell.repository;
import cn.itsource.aisell.domain.Producttype;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.PrimitiveIterator;

public interface ProducttypeRepository extends BaseRepository<Producttype,Long> {
    @Query("select o from Producttype o where o.parent is not null")
    List<Producttype> findChildTypes();
}