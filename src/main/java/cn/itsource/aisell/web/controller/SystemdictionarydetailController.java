package cn.itsource.aisell.web.controller;

import cn.itsource.aisell.common.AjaxResult;
import cn.itsource.aisell.common.UIPage;
import cn.itsource.aisell.domain.Systemdictionarydetail;
import cn.itsource.aisell.query.SystemdictionarydetailQuery;
import cn.itsource.aisell.service.ISystemdictionarydetailService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/systemDictionaryDetail")
public class SystemdictionarydetailController {

    @Autowired
    private ISystemdictionarydetailService systemdictionarydetailService;

    /**
     * 跳到主页面
     * @return
     */
    @RequestMapping("/index")
    public String index(){
        return "systemdictionarydetail/systemdictionarydetail";
    }

    /**
     * 获取列表
     * @return
     */
    @RequestMapping("/datagrid")
    @ResponseBody
    public UIPage<Systemdictionarydetail> datagrid(SystemdictionarydetailQuery query){
        //把Page对象转为UIPage
        return new UIPage<Systemdictionarydetail>(systemdictionarydetailService.findPageByQuery(query));
    }


    //当你在方法上面打上此注解之后，意思就是，在执行任何方法之前，都要先执行@ModelAttribute对应的方法
    @ModelAttribute("updateSystemdictionarydetail")
    public Systemdictionarydetail beforeEdit(Systemdictionarydetail systemdictionarydetail, String cmd){
        if(systemdictionarydetail.getId()!= null && StringUtils.isNotBlank(cmd)){
            systemdictionarydetail = systemdictionarydetailService.findOne(systemdictionarydetail.getId());
            //凡是关联对象都必须清空，否则会报n2n问题
        }
        return systemdictionarydetail;
    }

    /**
     * 保存数据
     * @param systemdictionarydetail
     * @return
     */
    @RequestMapping("/save")
    @ResponseBody
    public AjaxResult save(Systemdictionarydetail systemdictionarydetail){
        return saveOrUpdate(systemdictionarydetail);
    }
    @RequestMapping("/update")
    @ResponseBody
    public AjaxResult update(@ModelAttribute("updateSystemdictionarydetail") Systemdictionarydetail systemdictionarydetail){
        return saveOrUpdate(systemdictionarydetail);
    }

    private AjaxResult saveOrUpdate(Systemdictionarydetail systemdictionarydetail){
        try {
            systemdictionarydetailService.save(systemdictionarydetail);
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
                systemdictionarydetailService.delete(id);
            }
            return new AjaxResult();
        } catch (Exception e) {
            e.printStackTrace();
            return  new AjaxResult(false, "删除失败!" + e.getMessage());
        }
    }


}