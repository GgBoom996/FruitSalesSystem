package cn.itsource.aisell.service.impl;
import cn.itsource.aisell.common.UserContext;
import cn.itsource.aisell.domain.Permission;
import cn.itsource.aisell.repository.PermissionRepository;
import cn.itsource.aisell.service.IPermissionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Set;

@Service
public class PermissionServiceImpl extends BaseServiceImpl<Permission,Long> implements IPermissionService {
    @Autowired
    private PermissionRepository permissionRepository;

    @Override
    public Set<String> findPermissionssnsByLoginUser() {
        return permissionRepository.findPermissionsByLoginUser(UserContext.getUser());
    }
}