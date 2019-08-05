package cn.itsource.aisell.service;
import cn.itsource.aisell.domain.Product;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

public interface IProductService extends IBaseService<Product, Long> {
    /**
     * 保存产品，上传附件
     * @param product 产品对象
     * @param fileImage 附件
     * @param request 请求
     */
    void save(Product product, MultipartFile fileImage, HttpServletRequest request) throws IOException;
  
}