package cn.itsource.aisell.query;

import cn.itsource.aisell.domain.Depot;
import com.github.wenhao.jpa.Specifications;
import org.apache.commons.lang3.StringUtils;
import org.springframework.data.jpa.domain.Specification;

/**
 * 主要接收前台传递的数据
 */
public class DepotQuery extends BaseQuery {
    private String name;

    /**
     * 动态添加条件
     * @return
     */
    public Specification createSpec(){
        Specification<Depot> specification = Specifications.<Depot>and().
                like(StringUtils.isNotBlank(name),"name", "%"+name+"%").build();
        return specification;
    }

  public String getName(){
       return name;
  }
  public void setName(String name){
      this.name = name;
  }
}