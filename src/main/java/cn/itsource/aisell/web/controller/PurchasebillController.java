package cn.itsource.aisell.web.controller;
import cn.itsource.aisell.common.UserContext;
import cn.itsource.aisell.domain.Purchasebill;
import cn.itsource.aisell.common.AjaxResult;
import cn.itsource.aisell.common.UIPage;
import cn.itsource.aisell.domain.Purchasebillitem;
import cn.itsource.aisell.query.PurchasebillQuery;
import cn.itsource.aisell.service.IPurchasebillService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.math.BigDecimal;
import java.util.*;

@Controller
@RequestMapping("/purchaseBill")
public class PurchasebillController {

    @Autowired
    private IPurchasebillService purchasebillService;

    /**
     * 跳转到列表界面
     * @return
     */
    @RequestMapping("/index")
    public String index(){
        return "purchasebill/purchasebill";
    }


    /**
     * 显示员工列表信息
     * @return
     */
    @RequestMapping("/datagrid")
    @ResponseBody
    public UIPage<Purchasebill> datagrid(PurchasebillQuery query){
        Page<Purchasebill> pageByQuery = purchasebillService.findPageByQuery(query);
        return new UIPage<Purchasebill>(pageByQuery);
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
                purchasebillService.delete(id);
            }
            return new AjaxResult();
        } catch (Exception e) {
            e.printStackTrace();
            return new AjaxResult(false, "删除失败,原因:" + e.getMessage());
        }
    }

    @ModelAttribute("updatePurchasebill")//在执行目标方法之前，它会首先执行当前方法
    public Purchasebill beforeEdit(Long id){
        if(id != null){
            Purchasebill purchasebill = purchasebillService.findOne(id);
            //以后使用jpa凡是看到关联对象都给我清空，保你永生
            purchasebill.setBuyer(null);
            purchasebill.setSupplier(null);
            purchasebill.getItems().clear();
            return purchasebill;
        }
        return null;
    }



    /**
     * 添加和修改都是此方法
     * @param purchasebill  判断id是否为空，如果id为空，则新增，否则修改
     * @return
     */
    @RequestMapping("/save")
    @ResponseBody
    public AjaxResult save( Purchasebill purchasebill){
        purchasebill.setInputUser(UserContext.getUser());
        purchasebill.setInputTime(new Date());
        return saveOrUpdate(purchasebill);
    }

    @RequestMapping("/update")
    @ResponseBody
    public AjaxResult update(@ModelAttribute("updatePurchasebill") Purchasebill purchasebill){
        return saveOrUpdate(purchasebill);
    }

    public AjaxResult saveOrUpdate(Purchasebill purchasebill){
        try {
            //获取明细
            List<Purchasebillitem> items = purchasebill.getItems();
            //定义一个变量来装总数量
            BigDecimal totalNum = new BigDecimal("0");
            //定义一个变量来装总金额
            BigDecimal totalAmount = new BigDecimal("0");
            //获取到及具体的某个明细
            for (Purchasebillitem item : items) {
                //计算小计
                item.setAmount(item.getNum().multiply(item.getPrice()));
                //总数量做累加
                totalNum = totalNum.add(item.getNum());
                //总金额做累加
                totalAmount = totalAmount.add(item.getAmount());
                //多方知道一方
                item.setBill(purchasebill);
            }
            purchasebill.setTotalAmount(totalAmount);
            purchasebill.setTotalNum(totalNum);
            purchasebillService.save(purchasebill);
            return new AjaxResult();
        } catch (Exception e) {
            e.printStackTrace();
            return new AjaxResult(false, "操作失败!原因:" + e.getMessage());
        }
    }

}