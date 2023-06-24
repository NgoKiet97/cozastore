package com.cybersoft.cozastore.service.imp;

import com.cybersoft.cozastore.payload.request.ProductRequest;
import com.cybersoft.cozastore.payload.response.ProductResponse;

import java.util.List;

public interface IProductService {

    List<ProductResponse> getProductByCategoryId(int id);

    boolean addProduct(ProductRequest productRequest);

    ProductResponse getDetailProduct(int id);
}
