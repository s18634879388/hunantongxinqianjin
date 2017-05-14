package cn.hunantongxinqianjin.web.mapper;

import cn.hunantongxinqianjin.web.entity.Product;

import java.util.List;

/**
 * Created by shixiaoqi on 2017/5/13.
 */
public interface ProductMapper {

    List<Product> getAllProducts();

    List<Product> getProductByPage(int count, int offset);

    int getAllProductCount();
}
