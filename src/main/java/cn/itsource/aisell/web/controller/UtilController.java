package cn.itsource.aisell.web.controller;

import cn.itsource.aisell.domain.*;
import cn.itsource.aisell.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

/**
 * 提供一个工具类，该工具类中所有的方法都只提供下拉列表
 */
@Controller
@RequestMapping("/util")
public class UtilController {
    @Autowired
    private IDepartmentService departmentService;

    @Autowired
    private ISystemdictionarydetailService systemdictionarydetailService;

    @Autowired
    private IProducttypeService producttypeService;

    @Autowired
    private ISupplierService supplierService;

    @Autowired
    private IEmployeeService employeeService;

    @Autowired
    private IProductService productService;



    /**
     * 获取所有的部门
     * @return
     */
    @RequestMapping("/findAllDepartment")
    @ResponseBody
    public List<Department> findAllDepartment(){
        return departmentService.findAll();
    }


    /**
     * 查询所有品牌
     * @return
     */
    @RequestMapping("/findBrands")
    @ResponseBody
    public List<Systemdictionarydetail> findBrands(){
        return systemdictionarydetailService.findDetailsBySn(Systemdictionarytype.BRAND);
    }

    /**
     * 查询所有单位
     * @return
     */
    @RequestMapping("/findUnits")
    @ResponseBody
    public List<Systemdictionarydetail> findUnits(){
        return systemdictionarydetailService.findDetailsBySn(Systemdictionarytype.UNIT);
    }

    /**
     * 查询所有的产品类型
     * @return
     */
    @RequestMapping("/findTypes")
    @ResponseBody
    public List<Producttype> findTypes(){
        return producttypeService.findChildTypes();
    }

    /**
     * 查询所有的供应商
     * @return
     */
    @RequestMapping("/findSuppliers")
    @ResponseBody
    public List<Supplier> findSuppliers(){
        return supplierService.findAll();
    }

    /**
     * 查询所有的采购员
     * @return
     */
    @RequestMapping("/findBuyers")
    @ResponseBody
    public List<Employee> findBuyers(){
        return employeeService.findBuyers();
    }

    /**
     * 查询所有的产品对象
     * @return
     */
    @RequestMapping("/findProduts")
    @ResponseBody
    public List<Product> findProduts(){
        return productService.findAll();
    }

}
