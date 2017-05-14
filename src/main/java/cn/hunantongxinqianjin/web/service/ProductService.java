package cn.hunantongxinqianjin.web.service;

import cn.hunantongxinqianjin.web.entity.Product;
import cn.hunantongxinqianjin.web.mapper.ProductMapper;
import cn.hunantongxinqianjin.web.utils.OSSClientUtil;
import cn.hunantongxinqianjin.web.utils.PageRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

/**
 * Created by shixiaoqi on 2017/5/13.
 */
@Service
public class ProductService {

    @Autowired
    ProductMapper productMapper;
    @Autowired
    OSSClientUtil ossClient;

    public List<Product> getAllProducts() {
        List<Product> products = productMapper.getAllProducts();
        return products;
    }

    public List<Product> getFirstProducts(PageRequest page) {
        int count = page.getPageSize();
        int offset = page.getOffset();
        List<Product> products = productMapper.getProductByPage(count,offset);
        return products;
    }


    public int getAllProductCount() {
        int recordCount = productMapper.getAllProductCount();
        return recordCount;
    }

    public String updateHead(MultipartFile file) throws Exception {
        if (file == null || file.getSize() <= 0) {
            throw new Exception("头像不能为空");
        }
        String name = ossClient.uploadImg2Oss(file);
        String imgUrl = ossClient.getImgUrl(name);
        return imgUrl;
    }

    public int addProduct(Product product) {
        int res = productMapper.addProduct(product);
        return res;
    }

    public int modifyProduct(Product product) {
        int res = productMapper.modifyProduct(product);
        return res;
    }

    public Product getProductById(long id) {
        return productMapper.getProductById(id);
    }
}
