package cn.itsource.aisell.common;

import cn.afterturn.easypoi.excel.entity.result.ExcelVerifyHandlerResult;
import cn.afterturn.easypoi.handler.inter.IExcelVerifyHandler;
import cn.itsource.aisell.domain.Employee;
import cn.itsource.aisell.service.IEmployeeService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * 自定义校验，专门来验证用户名是否重复
 */
@Component
public class MyExcelVerifyHandlerImpl implements IExcelVerifyHandler<Employee> {
    @Autowired
    private IEmployeeService employeeService;
    @Override
    public ExcelVerifyHandlerResult verifyHandler(Employee employee) {
        //默认验证是成功的
        ExcelVerifyHandlerResult excelVerifyHandlerResult = new ExcelVerifyHandlerResult(true);
        if(StringUtils.isNotBlank(employee.getUsername())){
            Employee emp = employeeService.findEmployeeByUsername(employee.getUsername());
            if(emp != null){
                excelVerifyHandlerResult.setSuccess(false);
                excelVerifyHandlerResult.setMsg("用户名已经存在了!!");
            }
        }

        return excelVerifyHandlerResult;
    }
}
