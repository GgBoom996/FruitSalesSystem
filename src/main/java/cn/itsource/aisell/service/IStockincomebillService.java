package cn.itsource.aisell.service;
import cn.itsource.aisell.domain.Employee;
import cn.itsource.aisell.domain.Stockincomebill;

public interface IStockincomebillService extends IBaseService<Stockincomebill, Long> {

    /**
     * 审核入库单
     * @param billId 入库单id
     * @param autor  审核人
     */
    void audit(Long billId, Employee autor);

}