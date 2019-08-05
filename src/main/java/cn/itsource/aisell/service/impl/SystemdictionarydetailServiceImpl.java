package cn.itsource.aisell.service.impl;
import cn.itsource.aisell.domain.Systemdictionarydetail;
import cn.itsource.aisell.repository.SystemdictionarydetailRepository;
import cn.itsource.aisell.service.ISystemdictionarydetailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SystemdictionarydetailServiceImpl extends BaseServiceImpl<Systemdictionarydetail,Long> implements ISystemdictionarydetailService {
    @Autowired
    private SystemdictionarydetailRepository systemdictionarydetailRepository;

    @Override
    public List<Systemdictionarydetail> findDetailsBySn(String sn) {
        return systemdictionarydetailRepository.findDetailsBySn(sn);
    }
}