package cn.itsource.aisell.realm;

import cn.itsource.aisell.common.MD5Util;
import cn.itsource.aisell.common.UserContext;
import cn.itsource.aisell.domain.Employee;
import cn.itsource.aisell.service.IEmployeeService;
import cn.itsource.aisell.service.IPermissionService;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.util.ByteSource;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.HashSet;
import java.util.Set;

public class ItsourceRealm extends AuthorizingRealm{
    @Autowired
    private IEmployeeService employeeService;

    @Autowired
    private IPermissionService permissionService;
    //授权  角色和权限 更多的授予权限
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
        Employee user = UserContext.getUser();
        SimpleAuthorizationInfo info = new SimpleAuthorizationInfo();
        Set<String> permiss = permissionService.findPermissionssnsByLoginUser();
        info.setStringPermissions(permiss);
        return info;
    }

    public Set<String> findPermissionsByUsername(String username){
        Set<String> permissions = new HashSet<>();
        permissions.add("employee:index");
        permissions.add("dept:datagrid");
        return permissions;
    }

    //身份认证
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {
        String principal = (String)token.getPrincipal();
        Employee employee = employeeService.findEmployeeByUsername(principal);
        System.out.println(employee);
        //查询出的对象为空 则返回空
        if (employee == null) {
            return null;
        }
        //加盐
        ByteSource itsource = ByteSource.Util.bytes(MD5Util.SALT);
        return new SimpleAuthenticationInfo(employee, employee.getPassword(), itsource, getName());
    }
}
