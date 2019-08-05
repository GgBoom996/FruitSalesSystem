package cn.itsource.aisell.service.impl;
import cn.itsource.aisell.domain.Supplier;
import cn.itsource.aisell.repository.SupplierRepository;
import cn.itsource.aisell.service.ISupplierService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SupplierServiceImpl extends BaseServiceImpl<Supplier,Long> implements ISupplierService {
    @Autowired
    private SupplierRepository supplierRepository;
}