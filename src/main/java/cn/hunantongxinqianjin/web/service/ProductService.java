package cn.hunantongxinqianjin.web.service;

import cn.hunantongxinqianjin.web.entity.Product;
import cn.hunantongxinqianjin.web.mapper.ProductMapper;
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

    public String updateHead(MultipartFile file, int i) {
    }
}
