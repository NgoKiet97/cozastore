package com.cybersoft.cozastore.service.imp;

import com.cybersoft.cozastore.payload.request.ProductRequest;
import com.cybersoft.cozastore.payload.response.ProductResponse;

import java.util.List;

public interface IProductService {

    List<ProductResponse> getAllProduct();

    List<ProductResponse> getProductByCategoryId(int id);

    List<ProductResponse> getProductByColorId(int id);

    boolean addProduct(ProductRequest productRequest);

    ProductResponse getDetailProduct(int id);

    boolean clearCache();
}
