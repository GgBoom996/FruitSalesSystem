package cn.itsource.aisell.web.controller;

import cn.itsource.aisell.common.AjaxResult;
import cn.itsource.aisell.common.UIPage;
import cn.itsource.aisell.domain.Stockincomebill;
import cn.itsource.aisell.query.StockincomebillQuery;
import cn.itsource.aisell.service.IStockincomebillService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;
import java.util.Map;

@Controller
@RequestMapping("/stockincomebill")
public class StockincomebillController {

    @Autowired
    private IStockincomebillService stockincomebillService;

    /**
     * 跳转到列表界面
     * @return
     */
    @RequestMapping("/index")
    public String index(){
        return "stockincomebill/stockincomebill";
    }


    /**
     * 显示员工列表信息
     * @return
     */
    @RequestMapping("/datagrid")
    @ResponseBody
    public UIPage<Stockincomebill> datagrid(StockincomebillQuery query){
        Page<Stockincomebill> pageByQuery = stockincomebillService.findPageByQuery(query);
        return new UIPage<Stockincomebill>(pageByQuery);
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
                stockincomebillService.delete(id);
            }
            return new AjaxResult();
        } catch (Exception e) {
            e.printStackTrace();
            return new AjaxResult(false, "删除失败,原因:" + e.getMessage());
        }
    }

    @ModelAttribute("updateStockincomebill")//在执行目标方法之前，它会首先执行当前方法
    public Stockincomebill beforeEdit(Long id){
        if(id != null){
            Stockincomebill stockincomebill = stockincomebillService.findOne(id);
            //以后使用jpa凡是看到关联对象都给我清空，保你永生
            return stockincomebill;
        }
        return null;
    }



    /**
     * 添加和修改都是此方法
     * @param stockincomebill  判断id是否为空，如果id为空，则新增，否则修改
     * @return
     */
    @RequestMapping("/save")
    @ResponseBody
    public AjaxResult save(Stockincomebill stockincomebill){
        return saveOrUpdate(stockincomebill);
    }

    @RequestMapping("/update")
    @ResponseBody
    public AjaxResult update(@ModelAttribute("updateStockincomebill") Stockincomebill stockincomebill){
        return saveOrUpdate(stockincomebill);
    }

    public AjaxResult saveOrUpdate(Stockincomebill stockincomebill){
        try {
            stockincomebillService.save(stockincomebill);
            return new AjaxResult();
        } catch (Exception e) {
            e.printStackTrace();
            return new AjaxResult(false, "操作失败!原因:" + e.getMessage());
        }
    }

}