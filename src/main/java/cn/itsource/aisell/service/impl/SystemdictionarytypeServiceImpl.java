package cn.itsource.aisell.service.impl;
import cn.itsource.aisell.domain.Systemdictionarytype;
import cn.itsource.aisell.repository.SystemdictionarytypeRepository;
import cn.itsource.aisell.service.ISystemdictionarytypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SystemdictionarytypeServiceImpl extends BaseServiceImpl<Systemdictionarytype,Long> implements ISystemdictionarytypeService {
    @Autowired
    private SystemdictionarytypeRepository systemdictionarytypeRepository;
}