package cn.itsource.aisell.service.impl;
import cn.itsource.aisell.domain.Producttype;
import cn.itsource.aisell.repository.ProducttypeRepository;
import cn.itsource.aisell.service.IProducttypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProducttypeServiceImpl extends BaseServiceImpl<Producttype,Long> implements IProducttypeService {
    @Autowired
    private ProducttypeRepository producttypeRepository;

    @Override
    public List<Producttype> findChildTypes() {
        return producttypeRepository.findChildTypes();
    }

}