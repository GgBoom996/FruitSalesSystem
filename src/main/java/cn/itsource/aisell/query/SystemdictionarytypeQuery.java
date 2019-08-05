package cn.itsource.aisell.query;
import cn.itsource.aisell.domain.Systemdictionarytype;
import com.github.wenhao.jpa.Specifications;
import org.apache.commons.lang3.StringUtils;
import org.springframework.data.jpa.domain.Specification;

/**
 * 这是一个查询类(对象)
 * 接收前台传过来的查询数据
 */
public class SystemdictionarytypeQuery extends BaseQuery {

    private String name;


    /**
     * 创建相应的查询规则并且返回这个规则
     * @return
     */
    @Override
    public Specification createSpec(){
        Specification<Systemdictionarytype> spec = Specifications.<Systemdictionarytype>and()
                .like(StringUtils.isNotBlank(name),"name", "%"+name+"%")
                .build();
        return spec;
    }
	
	public void setName(String name){
		this.name = name;
	}
	
	public String getName(){
		return name;
	}


   
}