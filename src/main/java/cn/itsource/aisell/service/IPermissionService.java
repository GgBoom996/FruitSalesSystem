package cn.itsource.aisell.service;
import cn.itsource.aisell.domain.Permission;

import java.util.Set;

public interface IPermissionService extends IBaseService<Permission, Long> {

    /**
     * 根据登录用户查询所拥有的权限编号
     * @return
     */
    Set<String> findPermissionssnsByLoginUser();
  
}