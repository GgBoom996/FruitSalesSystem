package cn.itsource.aisell.common;

import cn.itsource.aisell.domain.Employee;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;

/**
 * 把当前登录对象放到session中，  也可以在session中获取到当前登录对象
 *
 * 使用shiro之后，我们明明可以通过subject.getPrincipal()获取到当前登录用户，为什么还要放到session中
 *
 * 放到session中的目的是让我们当前项目功能更加健全，比如有些时候在jsp页面中，不能用标签达到我们某个功能
 * 只能通过el表达式，所以我们要把当前登录用户放到session中
 */
public class UserContext {
    public static final String LOGIN_IN_SESSION = "loginUser";
    public static void setSesssion(Employee employee){
        Subject subject = SecurityUtils.getSubject();
        subject.getSession().setAttribute(LOGIN_IN_SESSION, employee);
    }

    public static Employee getUser(){
        Subject subject = SecurityUtils.getSubject();
        return (Employee) subject.getSession().getAttribute(LOGIN_IN_SESSION);
    }
}
