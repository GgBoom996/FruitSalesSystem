package cn.itsource.aisell.web.controller;

import cn.itsource.aisell.common.AjaxResult;
import cn.itsource.aisell.common.UIPage;
import cn.itsource.aisell.domain.Systemdictionarytype;
import cn.itsource.aisell.query.SystemdictionarytypeQuery;
import cn.itsource.aisell.service.ISystemdictionarytypeService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/systemDictionaryType")
public class SystemdictionarytypeController {

    @Autowired
    private ISystemdictionarytypeService systemdictionarytypeService;

    /**
     * 跳到主页面
     * @return
     */
    @RequestMapping("/index")
    public String index(){
        return "systemdictionarytype/systemdictionarytype";
    }

    /**
     * 获取列表
     * @return
     */
    @RequestMapping("/datagrid")
    @ResponseBody
    public UIPage<Systemdictionarytype> datagrid(SystemdictionarytypeQuery query){
        //把Page对象转为UIPage
        return new UIPage<Systemdictionarytype>(systemdictionarytypeService.findPageByQuery(query));
    }


    //当你在方法上面打上此注解之后，意思就是，在执行任何方法之前，都要先执行@ModelAttribute对应的方法
    @ModelAttribute("updateSystemdictionarytype")
    public Systemdictionarytype beforeEdit(Systemdictionarytype systemdictionarytype, String cmd){
        if(systemdictionarytype.getId()!= null && StringUtils.isNotBlank(cmd)){
            systemdictionarytype = systemdictionarytypeService.findOne(systemdictionarytype.getId());
            //凡是关联对象都必须清空，否则会报n2n问题
        }
        return systemdictionarytype;
    }

    /**
     * 保存数据
     * @param systemdictionarytype
     * @return
     */
    @RequestMapping("/save")
    @ResponseBody
    public AjaxResult save(Systemdictionarytype systemdictionarytype){
        return saveOrUpdate(systemdictionarytype);
    }
    @RequestMapping("/update")
    @ResponseBody
    public AjaxResult update(@ModelAttribute("updateSystemdictionarytype") Systemdictionarytype systemdictionarytype){
        return saveOrUpdate(systemdictionarytype);
    }

    private AjaxResult saveOrUpdate(Systemdictionarytype systemdictionarytype){
        try {
            systemdictionarytypeService.save(systemdictionarytype);
            return new AjaxResult();
        } catch (Exception e) {
            e.printStackTrace();
            return new AjaxResult(false, "操作失败" + e.getMessage());
        }
    }

    /**
     * 删除数据
     * @param ids
     * @return
     */
    @RequestMapping("/delete")
    @ResponseBody
    public AjaxResult delete(Long[] ids){
        try {
            for (Long id : ids) {
                systemdictionarytypeService.delete(id);
            }
            return new AjaxResult();
        } catch (Exception e) {
            e.printStackTrace();
            return  new AjaxResult(false, "删除失败!" + e.getMessage());
        }
    }


}