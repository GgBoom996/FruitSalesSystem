package cn.itsource.aisell.service.impl;
import cn.itsource.aisell.domain.Dept;
import cn.itsource.aisell.repository.DeptRepository;
import cn.itsource.aisell.service.IDeptService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class DeptServiceImpl extends BaseServiceImpl<Dept,Long> implements IDeptService {
    @Autowired
    private DeptRepository deptRepository;
}