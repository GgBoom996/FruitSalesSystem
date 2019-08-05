package cn.itsource.aisell.service.impl;
import cn.itsource.aisell.domain.Product;
import cn.itsource.aisell.repository.ProductRepository;
import cn.itsource.aisell.service.IProductService;
import net.coobird.thumbnailator.Thumbnails;
import org.apache.commons.io.FileUtils;
import org.apache.commons.io.FilenameUtils;
import org.apache.commons.io.IOUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

@Service
public class ProductServiceImpl extends BaseServiceImpl<Product,Long> implements IProductService {
    @Autowired
    private ProductRepository productRepository;

    @Override
    public void save(Product product, MultipartFile fileImage, HttpServletRequest request) throws IOException {
        //如果fileImage不为空就证明在上传
        if(fileImage.getSize() != 0L){
            ServletContext servletContext = request.getServletContext();
            //获取upload的根路径
            String rootPath = servletContext.getRealPath("/");
            //如果产品类型不为空，证明是修改图片
            if(product.getId() != null && StringUtils.isNotBlank(product.getPic())){
                //删除大图
                File file = new File(rootPath, product.getPic());
                file.delete();
                //删除小图
                file = new File(rootPath, product.getSmallPic());
                file.delete();
            }
            //随机产生名字
            long name = System.currentTimeMillis();//3434543534534534
            //获取上传附件的名字
            String filename = fileImage.getOriginalFilename();//a.png
            //获取文件的后缀名
            String extension = FilenameUtils.getExtension(filename);//png
            //最终生成小图的名字
            String smallFileName = name + "_small." + extension;//3434543534534534_small.png
            //最终生成文件的名字（大图）
            String bigFileName = name + "." + extension;//3434543534534534.png
            //存储大图的路径
            String filePath = "/upload/" + bigFileName;//    /upload/3434543534534534.png
            //存储小图的路径
            String smallFilePath = "/upload/" + smallFileName;//  /upload/3434543534534534_small.png
            //大图的绝对路径
            File file = new File(rootPath, filePath);//   E:\ideaProject\target\aisell/upload/3434543534534534.png
            //如果图片的父文件夹不存在，则创建
            if(!file.getParentFile().exists()){
                file.getParentFile().mkdirs();
            }
            //获取输出流
            FileOutputStream fileOutputStream = new FileOutputStream(file);
            //上传的核心代码
            IOUtils.copy(fileImage.getInputStream(),fileOutputStream);

            //大图上传完毕之后，则把路径设置到product中
            product.setPic(filePath);
            //压缩小图
            Thumbnails.of(fileImage.getInputStream()).scale(0.2f).toFile(new File(rootPath,smallFilePath));//E:\ideaProject\aisell\target/upload/3434543534534534_small.png
            //小图设置完毕之后，把路径设置到product中
            product.setSmallPic(smallFilePath);
            fileOutputStream.close();
        }
        super.save(product);
    }
}