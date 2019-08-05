package cn.itsource.aisell.service;

import cn.itsource.aisell.query.BaseQuery;
import org.springframework.data.domain.Page;

import java.io.Serializable;
import java.util.List;

public interface IBaseService<T,ID extends Serializable> {
    //修改和添加都是此方法
    void save(T t);
    //删除
    void delete(ID id);
    //通过id查询对象
    T findOne(ID id);
    //查询所有的列表
    List<T> findAll();
    //根据条件查询列表
    List<T> findAllByQuery(BaseQuery baseQuery);
    //分页查询
    Page<T> findPageByQuery(BaseQuery baseQuery);
}
