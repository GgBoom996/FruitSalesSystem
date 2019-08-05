package cn.itsource.aisell.query;

import cn.itsource.aisell.domain.Employee;
import com.github.wenhao.jpa.Specifications;
import org.apache.commons.lang3.StringUtils;
import org.springframework.data.jpa.domain.Specification;

/**
 * 主要接收前台传递的数据
 */
public class EmployeeQuery extends BaseQuery{
    private String username;
    private Long deptId;

    /**
     * 动态添加条件
     * @return
     */
    public Specification createSpec(){
        Specification<Employee> specification = Specifications.<Employee>and().
                like(StringUtils.isNotBlank(username),"username", "%"+username+"%").
                eq(deptId!=null,"department.id",deptId).build();
        return specification;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public Long getDeptId() {
        return deptId;
    }

    public void setDeptId(Long deptId) {
        this.deptId = deptId;
    }
}
