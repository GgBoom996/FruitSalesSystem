package cn.itsource.aisell.web.controller;

import cn.itsource.aisell.common.AjaxResult;
import cn.itsource.aisell.common.UserContext;
import cn.itsource.aisell.domain.Employee;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;


@Controller
public class LoginController {

    /**
     * 跳转到登录界面
     * @return
     */
    @RequestMapping(value = "/login",method = RequestMethod.GET)
    public String login(){
        return "login";
    }

    @RequestMapping(value = "/login",method = RequestMethod.POST)
    @ResponseBody
    public AjaxResult login(String username, String password){
        try {
            UsernamePasswordToken token = new UsernamePasswordToken(username, password);
            Subject subject = SecurityUtils.getSubject();
            if(!subject.isAuthenticated()){
                subject.login(token);
            }
            //当用户登录成功之后，你应该放到session中
            Employee employee = ((Employee) subject.getPrincipal());

            UserContext.setSesssion(employee);
            return new AjaxResult();
        } catch (UnknownAccountException e) {
            e.printStackTrace();
            return new AjaxResult(false, "账号不存在!!");
        }catch (IncorrectCredentialsException e){
            e.printStackTrace();
            return new AjaxResult(false, "密码错误!!");
        }catch (AuthenticationException e){
            e.printStackTrace();
        }catch (Exception e){
            e.printStackTrace();
        }
        return new AjaxResult(false, "网咯繁忙，请稍后再试!!");

    }
}

