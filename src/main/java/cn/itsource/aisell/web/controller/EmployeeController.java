package cn.itsource.aisell.web.controller;

import cn.afterturn.easypoi.entity.vo.NormalExcelConstants;
import cn.afterturn.easypoi.excel.entity.ExportParams;
import cn.afterturn.easypoi.excel.entity.enmus.ExcelType;
import cn.itsource.aisell.common.AjaxResult;
import cn.itsource.aisell.common.UIPage;
import cn.itsource.aisell.domain.Employee;
import cn.itsource.aisell.query.EmployeeQuery;
import cn.itsource.aisell.service.IEmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/employee")
public class EmployeeController {

    @Autowired
    private IEmployeeService employeeService;

    /**
     * 跳转到列表界面
     * @return
     */
    @RequestMapping("/index")
    public String index(){
        return "employee_index";
    }


    /**
     * 显示员工列表信息
     * @return
     */
    @RequestMapping("/datagrid")
    @ResponseBody
    public UIPage<Employee> datagrid(EmployeeQuery query){
        Page<Employee> pageByQuery = employeeService.findPageByQuery(query);
        return new UIPage<Employee>(pageByQuery);
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
                employeeService.delete(id);
            }
            return new AjaxResult();
        } catch (Exception e) {
            e.printStackTrace();
            return new AjaxResult(false, "删除失败,原因:" + e.getMessage());
        }
    }

    /**
     * 判断用户名是否存在
     * @param username
     * @return
     */
    @RequestMapping("/checkName")
    @ResponseBody
    public boolean checkName(String username,Long id){
        if(id == null){//新增判断用户名是否存在
            return employeeService.findEmployeeByUsername(username) == null ;
        }else{
            Employee employee = employeeService.findOne(id);
            if(employee.getUsername().equals(username)){
                return true;
            }else{
                return employeeService.findEmployeeByUsername(username) == null ;
            }
        }
    }


    @ModelAttribute("updateEmployee")//在执行目标方法之前，它会首先执行当前方法
    public Employee beforeEdit(Long id){
        if(id != null){
            Employee employee = employeeService.findOne(id);
            //以后使用jpa凡是看到关联对象都给我清空，保你永生
            employee.setDepartment(null);
            return employee;
        }
        return null;
    }



    /**
     * 添加和修改都是此方法
     * @param employee  判断id是否为空，如果id为空，则新增，否则修改
     * @return
     */
    @RequestMapping("/save")
    @ResponseBody
    public AjaxResult save(Employee employee){
        return saveOrUpdate(employee);
    }

    @RequestMapping("/update")
    @ResponseBody
    public AjaxResult update(@ModelAttribute("updateEmployee") Employee employee){
        return saveOrUpdate(employee);
    }

    public AjaxResult saveOrUpdate(Employee employee){
        try {

            employeeService.save(employee);
            return new AjaxResult();
        } catch (Exception e) {
            e.printStackTrace();
            return new AjaxResult(false, "操作失败!原因:" + e.getMessage());
        }
    }

    //Freeze  冻结
    @RequestMapping("/download")
    public String download(HttpServletRequest request,ModelMap map,EmployeeQuery query) {
        List<Employee> list = employeeService.findAllByQuery(query);

        for (Employee employee : list) {
            String rootPath = request.getServletContext().getRealPath("/");
            employee.setHeadImage(rootPath+employee.getHeadImage());
        }
        //导出基本信息的配置
        ExportParams params = new ExportParams("员工列表", "员工", ExcelType.XSSF);
//        params.setFreezeCol(2);//冻结的列
        map.put(NormalExcelConstants.DATA_LIST, list); // 数据集合
        map.put(NormalExcelConstants.CLASS, Employee.class);//导出实体
        map.put(NormalExcelConstants.PARAMS, params);//参数
        map.put(NormalExcelConstants.FILE_NAME, "employee");//文件名称
        return NormalExcelConstants.EASYPOI_EXCEL_VIEW;//View名称

    }

}
