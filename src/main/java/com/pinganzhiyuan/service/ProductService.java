package com.pinganzhiyuan.service;

import java.util.List;
import com.pinganzhiyuan.model.Product;
import com.pinganzhiyuan.util.ResponseBody;

public interface ProductService {

//    List<Product> getProductList();

    public int create(Product point, ResponseBody resBody);

    public int show(Product point, ResponseBody resBody);

    public int update(Product point, ResponseBody resBody);

//    public int index(List<Product> list, ResponseBody resBody);

    int index(List<Product> pointList, ResponseBody resBody, int pageNumber, int pageSize);

    int index(List<Product> pointList, ResponseBody resBody);

    public int resort(long touchId, long insertId, ResponseBody resBody);

    /**
     * 置顶一个产品
     * @param product
     */
    void bringToTop(Product product);
    
}
