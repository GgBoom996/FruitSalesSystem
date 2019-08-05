package cn.itsource.aisell.service.impl;
import cn.itsource.aisell.domain.Depot;
import cn.itsource.aisell.repository.DepotRepository;
import cn.itsource.aisell.service.IDepotService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class DepotServiceImpl extends BaseServiceImpl<Depot,Long> implements IDepotService {
    @Autowired
    private DepotRepository depotRepository;
}