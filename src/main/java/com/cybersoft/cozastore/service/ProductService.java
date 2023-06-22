package com.cybersoft.cozastore.service;

import com.cybersoft.cozastore.entity.CategoryEntity;
import com.cybersoft.cozastore.entity.ColorEntity;
import com.cybersoft.cozastore.entity.ProductEntity;
import com.cybersoft.cozastore.entity.SizeEntity;
import com.cybersoft.cozastore.payload.request.ProductRequest;
import com.cybersoft.cozastore.payload.response.ProductResponse;
import com.cybersoft.cozastore.repository.ProductRepository;
import com.cybersoft.cozastore.service.imp.IProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ProductService implements IProductService {
    @Autowired
    ProductRepository productRepository;

    @Value("${host.name}")
    private String hostname;

    @Override
    public List<ProductResponse> getProductByCategoryId(int id) {
        List<ProductEntity> list = productRepository.findByCategoryId(id);
        List<ProductResponse> responseList = new ArrayList<>();

        for (ProductEntity productEntity : list){
            ProductResponse productResponse = new ProductResponse();
            productResponse.setName(productEntity.getName());
            productResponse.setImage(hostname+"/product/file/" + productEntity.getImage());
            productResponse.setPrice(productEntity.getPrice());

            responseList.add(productResponse);
        }

        return responseList;
    }

    @Override
    public boolean addProduct(ProductRequest productRequest) {
        try{
            ProductEntity productEntity = new ProductEntity();

            productEntity.setName(productRequest.getName());
            productEntity.setImage(productRequest.getFileImage().getOriginalFilename());
            productEntity.setPrice(productRequest.getPrice());
            productEntity.setQuantity(productRequest.getQuantity());
            productEntity.setDescription(productRequest.getDescription());

            ColorEntity colorEntity = new ColorEntity();
            colorEntity.setId(productRequest.getColorId());

            SizeEntity sizeEntity = new SizeEntity();
            sizeEntity.setId(productRequest.getSizeId());

            CategoryEntity categoryEntity = new CategoryEntity();
            categoryEntity.setId(productRequest.getCategoryId());

            productEntity.setColor(colorEntity);
            productEntity.setSize(sizeEntity);
            productEntity.setCategory(categoryEntity);

            productRepository.save(productEntity);
            return true;
        } catch (Exception e){
            return false;
        }
    }
}
