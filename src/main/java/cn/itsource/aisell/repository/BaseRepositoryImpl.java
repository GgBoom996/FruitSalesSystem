package cn.itsource.aisell.repository;

import cn.itsource.aisell.query.BaseQuery;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.repository.support.SimpleJpaRepository;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import java.io.Serializable;
import java.util.List;

/**
 * 自定义实现类
 * @param <T>
 * @param <ID>
 */
public class BaseRepositoryImpl<T,ID extends Serializable> extends SimpleJpaRepository<T,ID> implements BaseRepository<T,ID> {
    private EntityManager entityManager;
    public BaseRepositoryImpl(Class<T> domainClass, EntityManager em) {

        /*                           这里这个报红没有影响吧，我们下面自己写也是红色的                                              */




        super(domainClass, em);
        this.entityManager = em;
    }

    @Override
    public Page findPageByQuery(BaseQuery baseQuery) {
        return super.findAll(baseQuery.createSpec(),new PageRequest(baseQuery.getJpaPage(),baseQuery.getPageSize(),baseQuery.createSort()));
    }

    @Override
    public List<T> findByQuery(BaseQuery baseQuery) {
        return super.findAll(baseQuery.createSpec(), baseQuery.createSort());
    }

    @Override
    public List findByJpql(String jpql, Object... values) {
        //from Employee where username like ?  and age>=?
        Query query = entityManager.createQuery(jpql);
        if (values != null && values.length != 0) {
            for (int i = 0; i < values.length; i++) {
                query.setParameter(i + 1, values[i]);
            }
        }
        return query.getResultList();
    }
}
