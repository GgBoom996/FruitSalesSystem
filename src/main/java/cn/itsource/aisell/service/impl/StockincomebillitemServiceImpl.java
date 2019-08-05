package cn.itsource.aisell.service.impl;
import cn.itsource.aisell.domain.Stockincomebillitem;
import cn.itsource.aisell.repository.StockincomebillitemRepository;
import cn.itsource.aisell.service.IStockincomebillitemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class StockincomebillitemServiceImpl extends BaseServiceImpl<Stockincomebillitem,Long> implements IStockincomebillitemService {
    @Autowired
    private StockincomebillitemRepository stockincomebillitemRepository;
}