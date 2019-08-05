package cn.itsource.aisell.service.impl;
import cn.itsource.aisell.domain.Employee;
import cn.itsource.aisell.domain.Menu;
import cn.itsource.aisell.repository.MenuRepository;
import cn.itsource.aisell.service.IMenuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class MenuServiceImpl extends BaseServiceImpl<Menu,Long> implements IMenuService {
    @Autowired
    private MenuRepository menuRepository;


    @Override
    public List<Menu> findMenuByLoginUser(Employee loginUser) {
        //定义一个空集合，该集合专门用来装一级菜单
        List<Menu> parents = new ArrayList<>();
        //查询出来的数据全部都是二级菜单
        List<Menu> menus = menuRepository.findMenuByLoginUser(loginUser); //二级菜单[角色管理,菜单管理,权限管理,导入管理,部门管理，员工管理]
        for (Menu menu : menus) {
            //拿到一级菜单
            Menu parent = menu.getParent();//组织机构
            if(!parents.contains(parent)){//如果不包含一级菜单则添加进去
                parents.add(parent);
            }
            //父菜单添加子菜单
            parent.getChildren().add(menu);
        }
        return parents;
    }
}