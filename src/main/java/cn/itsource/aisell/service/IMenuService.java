package cn.itsource.aisell.service;
import cn.itsource.aisell.domain.Employee;
import cn.itsource.aisell.domain.Menu;

import java.util.List;

public interface IMenuService extends IBaseService<Menu, Long> {


    /**
     * 获取菜单列表，根据登录用户
     * @return
     */
    List<Menu> findMenuByLoginUser(Employee loginUser);
  
}