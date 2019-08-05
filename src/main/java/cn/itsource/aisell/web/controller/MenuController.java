package cn.itsource.aisell.web.controller;

import cn.itsource.aisell.common.AjaxResult;
import cn.itsource.aisell.common.UIPage;
import cn.itsource.aisell.common.UserContext;
import cn.itsource.aisell.domain.Menu;
import cn.itsource.aisell.query.MenuQuery;
import cn.itsource.aisell.service.IMenuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/menu")
public class MenuController {

    @Autowired
    private IMenuService menuService;

    /**
     * 跳转到列表界面
     * @return
     */
    @RequestMapping("/index")
    public String index(){
        return "menu/menu";
    }


    /**
     * 显示员工列表信息
     * @return
     */
    @RequestMapping("/datagrid")
    @ResponseBody
    public UIPage<Menu> datagrid(MenuQuery query){
        Page<Menu> pageByQuery = menuService.findPageByQuery(query);
        return new UIPage<Menu>(pageByQuery);
    }

    /**
     * 根据当前登录人获取动态菜单
     * @return
     */
    @RequestMapping("/findMenuByLoginUser")
    @ResponseBody
    public List<Menu> findMenuByLoginUser(){
        List<Menu> menuByLoginUser = menuService.findMenuByLoginUser(UserContext.getUser());
        for (Menu menu : menuByLoginUser) {
            System.out.println(menu.toString());
        }
        return menuByLoginUser;
    }

    /**
     * 批量删除数据
     * @param ids
     * @return
     */
    @RequestMapping("/remove")
    @ResponseBody
    public AjaxResult remove(Long[] ids){
        Map<String, Object> map = new HashMap<>();
        try {
            for (Long id : ids) {
                menuService.delete(id);
            }
            return new AjaxResult();
        } catch (Exception e) {
            e.printStackTrace();
            return new AjaxResult(false, "删除失败,原因:" + e.getMessage());
        }
    }

    @ModelAttribute("updateMenu")//在执行目标方法之前，它会首先执行当前方法
    public Menu beforeEdit(Long id){
        if(id != null){
            Menu menu = menuService.findOne(id);
            //以后使用jpa凡是看到关联对象都给我清空，保你永生
            return menu;
        }
        return null;
    }



    /**
     * 添加和修改都是此方法
     * @param menu  判断id是否为空，如果id为空，则新增，否则修改
     * @return
     */
    @RequestMapping("/save")
    @ResponseBody
    public AjaxResult save(Menu menu){
        return saveOrUpdate(menu);
    }

    @RequestMapping("/update")
    @ResponseBody
    public AjaxResult update(@ModelAttribute("updateMenu") Menu menu){
        return saveOrUpdate(menu);
    }

    public AjaxResult saveOrUpdate(Menu menu){
        try {
            menuService.save(menu);
            return new AjaxResult();
        } catch (Exception e) {
            e.printStackTrace();
            return new AjaxResult(false, "操作失败!原因:" + e.getMessage());
        }
    }

}