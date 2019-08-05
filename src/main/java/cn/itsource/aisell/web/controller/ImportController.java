package cn.itsource.aisell.web.controller;

import cn.afterturn.easypoi.excel.ExcelImportUtil;
import cn.afterturn.easypoi.excel.entity.ImportParams;
import cn.afterturn.easypoi.excel.entity.result.ExcelImportResult;
import cn.afterturn.easypoi.handler.inter.IExcelVerifyHandler;
import cn.itsource.aisell.domain.Department;
import cn.itsource.aisell.domain.Employee;
import cn.itsource.aisell.service.IDepartmentService;
import cn.itsource.aisell.service.IEmployeeService;
import cn.itsource.aisell.common.MyExcelVerifyHandlerImpl;
import org.apache.commons.lang3.StringUtils;
import org.apache.poi.ss.usermodel.Workbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

@Controller
@RequestMapping("/import")
public class ImportController {

    @Autowired
    private IEmployeeService employeeService;

    @Autowired
    private IDepartmentService departmentService;

    @Autowired
    private IExcelVerifyHandler excelVerifyHandler;

    /**
     * 跳入到导入界面
     * @return
     */
    @RequestMapping("/index")
    public String index(){
        return "import";
    }

    @RequestMapping("/upload")
    public String upload(MultipartFile file, HttpServletResponse response,Model model) throws Exception {
//        System.out.println(file.getContentType());//上传文件的mime类型
//        System.out.println(file.getName());//前端页面  name对应的值
//        System.out.println(file.getOriginalFilename());//文件名
//        System.out.println(file.getSize());//上传文件的大小

        //导入的基本配置
        ImportParams params = new ImportParams();
        params.setHeadRows(1);
        params.setTitleRows(1);
        //我要使用自定义校验来严重我每个employee对象
        params.setVerifyHandler(excelVerifyHandler);
        //导入的数据必须要经过校验
        params.setNeedVerfiy(true);
        ExcelImportResult<Employee> result = ExcelImportUtil.importExcelMore(file.getInputStream(), Employee.class, params);//别人写好的导入功能
        //导入成功的数据
        List<Employee> list = result.getList();
        for (Employee employee : list) {
            if(employee.getDepartment()!=null&&StringUtils.isNotBlank(employee.getDepartment().getName())){//如果前端传递的部门不为空，则在数据库中进行查询
                Department department = departmentService.findDepartmentByName(employee.getDepartment().getName());
                employee.setDepartment(department);
            }
            employee.setPassword(employee.getUsername());
            employeeService.save(employee);
        }

        //拿到导出失败的工作簿
        Workbook failWorkbook = result.getFailWorkbook();
        if (result.isVerfiyFail()) { //验证是否有失败的数据
            ServletOutputStream fos = response.getOutputStream();
            response.setContentType("application/vnd.openxmlformats-officedocument.spreadsheetml.sheet"); //mime类型
            response.setHeader("Content-disposition", "attachment;filename=error.xlsx");
            result.getFailWorkbook().write(fos);
            fos.close();
        }
        return "import";
    }
}
