package cn.itsource.aisell.service.impl;
import cn.itsource.aisell.domain.*;
import cn.itsource.aisell.repository.DepotRepository;
import cn.itsource.aisell.repository.ProductstockRepository;
import cn.itsource.aisell.repository.StockincomebillRepository;
import cn.itsource.aisell.service.IStockincomebillService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.annotation.Transient;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

@Service
public class StockincomebillServiceImpl extends BaseServiceImpl<Stockincomebill,Long> implements IStockincomebillService {
    @Autowired
    private StockincomebillRepository stockincomebillRepository;
    @Autowired
    private DepotRepository depotRepository;

    @Autowired
    private ProductstockRepository productstockRepository;

    public static void main(String[] args) {
        BigDecimal a = new BigDecimal("10");
        BigDecimal b = new BigDecimal("3");
        System.out.println(a.divide(b,2,BigDecimal.ROUND_HALF_UP));
    }

    @Transient
    public void audit(Long billId, Employee autor) {
        //通过id获取到入库单对象
        Stockincomebill stockincomebill = super.findOne(billId);
        if(stockincomebill == null){
            throw new RuntimeException("该单据不存在!请刷新界面再试!!");
        }
        if(stockincomebill.getStatus() == 1 || stockincomebill.getStatus() == -1){
            throw new RuntimeException("该单据已审核完毕，请刷新界面再试!!");
        }
        stockincomebill.setAuditorTime(new Date());
        stockincomebill.setAuditor(autor);
        stockincomebill.setStatus(1);
        //修改入库单
        super.save(stockincomebill);

        //获取仓库对象
        Depot depot = stockincomebill.getDepot();
        depot.setCurrentCapacity(depot.getCurrentCapacity().add(stockincomebill.getTotalNum()));
        depot.setTotalAmount(depot.getTotalAmount().add(stockincomebill.getTotalAmount()));
        //修改仓库对象
        depotRepository.save(depot);

        //拿到所有的入库单明细
        List<Stockincomebillitem> items = stockincomebill.getItems();
        for (Stockincomebillitem item : items) {
            String jpql = "from Productstock where product=? and depot=?";
            //获取及时库存中的数据
            List<Productstock> productstocks = productstockRepository.findByJpql(jpql, item.getProduct(), depot);
            if(productstocks.size() == 0){
                Productstock productstock = new Productstock();
                productstock.setNum(item.getNum());
                productstock.setPrice(item.getPrice());
                productstock.setAmount(item.getAmount());
                productstock.setDepot(depot);
                productstock.setIncomeDate(new Date());
                productstock.setProduct(item.getProduct());
                productstockRepository.save(productstock);
            }else if(productstocks.size() == 1){
                Productstock productstock = productstocks.get(0);
                productstock.setIncomeDate(new Date());
                //总金额
                BigDecimal totalAmount  = productstock.getAmount().add(item.getAmount());
                //总数量
                BigDecimal totalNum = productstock.getNum().add(item.getNum());

                BigDecimal price = totalAmount.divide(totalNum, 2, BigDecimal.ROUND_HALF_UP);
                //设置单价
                productstock.setPrice(price);
                //总数量
                productstock.setNum(totalNum);
                productstock.setAmount(productstock.getPrice().multiply(productstock.getNum()));
                //修改数据
                productstockRepository.save(productstock);
            }else{
                throw new RuntimeException("程序有bug，赶快联系程序员进行修理");
            }
        }


    }
}