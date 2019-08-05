package cn.itsource.aisell.service.impl;
import cn.itsource.aisell.domain.Productstock;
import cn.itsource.aisell.repository.ProductstockRepository;
import cn.itsource.aisell.service.IProductstockService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ProductstockServiceImpl extends BaseServiceImpl<Productstock,Long> implements IProductstockService {
    @Autowired
    private ProductstockRepository productstockRepository;
}