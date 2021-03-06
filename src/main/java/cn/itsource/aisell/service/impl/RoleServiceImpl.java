package cn.itsource.aisell.service.impl;
import cn.itsource.aisell.domain.Role;
import cn.itsource.aisell.repository.RoleRepository;
import cn.itsource.aisell.service.IRoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RoleServiceImpl extends BaseServiceImpl<Role,Long> implements IRoleService {
    @Autowired
    private RoleRepository roleRepository;
}