package cn.itsource.aisell.web.controller;

import cn.itsource.aisell.common.AjaxResult;
import cn.itsource.aisell.common.UIPage;
import cn.itsource.aisell.domain.Producttype;
import cn.itsource.aisell.query.ProducttypeQuery;
import cn.itsource.aisell.service.IProducttypeService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/productType")
public class ProducttypeController {

    @Autowired
    private IProducttypeService producttypeService;

    /**
     * 跳到主页面
     * @return
     */
    @RequestMapping("/index")
    public String index(){
        return "producttype/producttype";
    }

    /**
     * 获取列表
     * @return
     */
    @RequestMapping("/datagrid")
    @ResponseBody
    public UIPage<Producttype> datagrid(ProducttypeQuery query){
        //把Page对象转为UIPage
        return new UIPage<Producttype>(producttypeService.findPageByQuery(query));
    }


    //当你在方法上面打上此注解之后，意思就是，在执行任何方法之前，都要先执行@ModelAttribute对应的方法
    @ModelAttribute("updateProducttype")
    public Producttype beforeEdit(Producttype producttype, String cmd){
        if(producttype.getId()!= null && StringUtils.isNotBlank(cmd)){
            producttype = producttypeService.findOne(producttype.getId());
            //凡是关联对象都必须清空，否则会报n2n问题
        }
        return producttype;
    }

    /**
     * 保存数据
     * @param producttype
     * @return
     */
    @RequestMapping("/save")
    @ResponseBody
    public AjaxResult save(Producttype producttype){
        return saveOrUpdate(producttype);
    }
    @RequestMapping("/update")
    @ResponseBody
    public AjaxResult update(@ModelAttribute("updateProducttype") Producttype producttype){
        return saveOrUpdate(producttype);
    }

    private AjaxResult saveOrUpdate(Producttype producttype){
        try {
            producttypeService.save(producttype);
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
                producttypeService.delete(id);
            }
            return new AjaxResult();
        } catch (Exception e) {
            e.printStackTrace();
            return  new AjaxResult(false, "删除失败!" + e.getMessage());
        }
    }


}