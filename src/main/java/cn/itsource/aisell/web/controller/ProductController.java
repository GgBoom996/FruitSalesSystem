package cn.itsource.aisell.web.controller;

import cn.itsource.aisell.common.AjaxResult;
import cn.itsource.aisell.common.UIPage;
import cn.itsource.aisell.domain.Product;
import cn.itsource.aisell.query.ProductQuery;
import cn.itsource.aisell.service.IProductService;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.util.WebUtils;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import java.io.File;

@Controller
@RequestMapping("/product")
public class ProductController {

    @Autowired
    private IProductService productService;

    /**
     * 跳到主页面
     * @return
     */
    @RequestMapping("/index")
    public String index(){
        return "product/product";
    }

    /**
     * 获取列表
     * @return
     */
    @RequestMapping("/datagrid")
    @ResponseBody
    public UIPage<Product> datagrid(ProductQuery query){
        //把Page对象转为UIPage
        return new UIPage<Product>(productService.findPageByQuery(query));
    }


    //当你在方法上面打上此注解之后，意思就是，在执行任何方法之前，都要先执行@ModelAttribute对应的方法
    @ModelAttribute("updateProduct")
    public Product beforeEdit(Product product, String cmd){
        if(product.getId()!= null && StringUtils.isNotBlank(cmd)){
            product = productService.findOne(product.getId());
            //凡是关联对象都必须清空，否则会报n2n问题
            product.setTypes(null);
            product.setUnit(null);
            product.setBrand(null);
        }
        return product;
    }

    /**
     * 保存数据
     * @param product
     * @return
     */
    @RequestMapping("/save")
    @ResponseBody
    public AjaxResult save(Product product, HttpServletRequest request){
        return saveOrUpdate(product,request);
    }
    @RequestMapping("/update")
    @ResponseBody
    public AjaxResult update(@ModelAttribute("updateProduct") Product product, HttpServletRequest request){
        return saveOrUpdate(product,request);
    }

    private AjaxResult saveOrUpdate(Product product, HttpServletRequest request){
        try {
            //下面是解决上传文件为空报错的问题
            MultipartFile fileImage = null;
            boolean isMultipart = ServletFileUpload.isMultipartContent(request);
            if (isMultipart){
                MultipartHttpServletRequest multipartRequest = WebUtils.getNativeRequest(request, MultipartHttpServletRequest.class);
                fileImage = multipartRequest.getFile("file");
            }
            productService.save(product,fileImage,request);
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
    public AjaxResult delete(Long[] ids, HttpServletRequest request){
        try {
            for (Long id : ids) {
                Product product = productService.findOne(id);
                ServletContext servletContext = request.getServletContext();
                //获取upload的根路径
                String rootPath = servletContext.getRealPath("/");
                if(StringUtils.isNotBlank(product.getPic())){
                    //删除大图
                    File file = new File(rootPath, product.getPic());
                    file.delete();
                    //删除小图
                    file = new File(rootPath, product.getSmallPic());
                    file.delete();
                }
                //删除数据
                productService.delete(id);
            }
            return new AjaxResult();
        } catch (Exception e) {
            e.printStackTrace();
            return  new AjaxResult(false, "删除失败!" + e.getMessage());
        }
    }


}