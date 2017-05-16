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

    /**
     * 获取所有上架的产品 根据修改时间倒序排列
     * @return
     */
    public List<Product> getAllProducts() {
        List<Product> products = productMapper.getAllProducts();
        return products;
    }

    /**
     * 分页查找产品列表
     * @param page
     * @return
     */
    public List<Product> getFirstProducts(PageRequest page) {
        int count = page.getPageSize();
        int offset = page.getOffset();
        List<Product> products = productMapper.getProductByPage(count,offset);
        return products;
    }

    /**
     * 获取所有产品数量
     * @return
     */
    public int getAllProductCount() {
        int recordCount = productMapper.getAllProductCount();
        return recordCount;
    }

    /**
     * OOS上传图片  并获取图片链接
     * @param file
     * @return
     * @throws Exception
     */
    public String updateHead(MultipartFile file) throws Exception {
        if (file == null || file.getSize() <= 0) {
            throw new Exception("头像不能为空");
        }
        String name = ossClient.uploadImg2Oss(file);
        String imgUrl = ossClient.getImgUrl(name);
        return imgUrl;
    }

    /**
     * 添加产品
     * @param product
     * @return
     */
    public int addProduct(Product product) {
        int res = productMapper.addProduct(product);
        return res;
    }

    /**
     * 修改产品信息
     * @param product
     * @return
     */
    public int modifyProduct(Product product) {
        int res = productMapper.modifyProduct(product);
        return res;
    }

    /**
     * 根据产品id获取产品信息
     * @param id
     * @return
     */
    public Product getProductById(long id) {
        return productMapper.getProductById(id);
    }

    /**
     * 修改产品状态
     * @param id
     * @return
     */
    public int modifyProState(long id) {
        synchronized (this){
            Product product = productMapper.getProductById(id);
            int state = product.getIsDelete()==0 ? 1 : 0;
            int res =  productMapper.modifyProState(state,id);
            return res;
        }
    }
}
