package cn.itsource.aisell.service;
import cn.itsource.aisell.domain.Systemdictionarydetail;

import java.util.List;

public interface ISystemdictionarydetailService extends IBaseService<Systemdictionarydetail, Long> {
    /**
     * 根据编号查询对应的数据字典明细
     * @param sn   数据字典类型的编号
     * @return
     */
    List<Systemdictionarydetail> findDetailsBySn(String sn);

}