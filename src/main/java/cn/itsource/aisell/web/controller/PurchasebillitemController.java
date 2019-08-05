package cn.itsource.aisell.web.controller;
import cn.itsource.aisell.domain.Purchasebillitem;
import cn.itsource.aisell.common.AjaxResult;
import cn.itsource.aisell.common.UIPage;
import cn.itsource.aisell.domain.vo.PurchasebillItemVo;
import cn.itsource.aisell.query.PurchasebillitemQuery;
import cn.itsource.aisell.service.IPurchasebillitemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/purchaseBillItem")
public class PurchasebillitemController {

    @Autowired
    private IPurchasebillitemService purchasebillitemService;

    /**
     * 跳转到列表界面
     * @return
     */
    @RequestMapping("/index")
    public String index(){
        return "purchasebillitem/purchasebillitem";
    }


    /**
     * 查询所有的明细
     * @param query
     * @return
     */
    @RequestMapping("/findItems")
    @ResponseBody
    public List<PurchasebillItemVo> findItems(PurchasebillitemQuery query){
        return purchasebillitemService.findItemsByQuery(query);
    }

    /**
     * 获取图形报表的数据
     * @param query
     * @return
     */
    @RequestMapping("/findGroups")
    @ResponseBody
    public List<Map<String,Object>> findGroups(PurchasebillitemQuery query){
        return purchasebillitemService.findGroupByQuery(query);
    }
}