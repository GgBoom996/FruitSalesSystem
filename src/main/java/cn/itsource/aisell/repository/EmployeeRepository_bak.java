package cn.itsource.aisell.repository;

import cn.itsource.aisell.domain.Employee;
import cn.itsource.aisell.query.BaseQuery;
import org.springframework.data.domain.Page;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

/**
 * 要传递的泛型
 * T：操作的目标实体
 * ID：该实体对象对应的主键类型
 */
public interface EmployeeRepository_bak extends JpaRepository<Employee,Long>,JpaSpecificationExecutor<Employee>{
    //   不写任何方法，则直接调用JPARepository的方法进行CRUD
    /**
     * springDataJpa它支持条件进行查询，使用条件查询必须遵循以下规则：
     *          查询的名字是不能随便乱写的，是有一定规则的
     * 好处：使用简单
     * 缺陷：
     *      1.记的东西太多
     *      2.只支持单表查询
     */
    //根据条件进行查询
    //模糊查询 根据用户名查询
    List<Employee> findByUsernameLike(String username);
    //查询年龄比传入的参数的对象  Ge大于等于 Gt大于 Le小于等于 Lt小于
    List<Employee> findByAgeGreaterThan(Integer age);

    //Query注解的查询 设置参数方式1
    /*
    * jpql语句  直接参与对象的查询,此时有提示是因为前面在xml中配置了扫面domain层所有的属性
    * 1与2为占位符  格式要求这样写
    * 用户使用模糊查询  年龄使用要求查询 然后要求的打印
    * */
    @Query("select e from Employee e where e.username like ?1 and e.age>?2")
    List<Employee> findEmployeeByLikeUsername(String name,Integer age);
    //Query注解的查询 设置参数方式2
    /*
    * username 模糊查询 条件后跟的结果值为":+@Param括号中的值"，表示别名
    *
    * */
    @Query("select o from Employee o where o.username like :username and o.age >:age")
    List<Employee> findEmployeeByLikeUsername2(@Param("username")String username,@Param("age")Integer age);

    //查询总条数  通过原生SQL语句查询
    @Query(value = "select count(*) from employee",nativeQuery = true)
    Long getCount();

    Page<Employee> findPageByQuery(BaseQuery baseQuery);
}
