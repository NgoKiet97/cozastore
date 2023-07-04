package com.cybersoft.cozastore.service;

import com.cybersoft.cozastore.entity.CategoryEntity;
import com.cybersoft.cozastore.entity.ColorEntity;
import com.cybersoft.cozastore.entity.ProductEntity;
import com.cybersoft.cozastore.entity.SizeEntity;
import com.cybersoft.cozastore.payload.request.ProductRequest;
import com.cybersoft.cozastore.payload.response.ProductResponse;
import com.cybersoft.cozastore.repository.ColorRepository;
import com.cybersoft.cozastore.repository.ProductRepository;
import com.cybersoft.cozastore.service.imp.IProductService;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class ProductService implements IProductService {
    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private RedisTemplate redisTemplate;

    private Gson gson = new Gson();

    @Value("${host.name}")
    private String hostname;

    @Override
    public List<ProductResponse> getAllProduct() {
        List<ProductResponse> responseList = new ArrayList<>();

        if(redisTemplate.hasKey("listAllProduct")){
            String dataProduct = (String) redisTemplate.opsForValue().get("listAllProduct");
            Type listType = new TypeToken<ArrayList<ProductResponse>>(){}.getType();
            responseList = new Gson().fromJson(dataProduct, listType);

        }else{
            List<ProductEntity> list = productRepository.findAll();
            for (ProductEntity productEntity : list){
                ProductResponse productResponse = new ProductResponse();
                productResponse.setId(productEntity.getId());
                productResponse.setName(productEntity.getName());
                productResponse.setImage(hostname+"/product/file/" + productEntity.getImage());
                productResponse.setPrice(productEntity.getPrice());

                responseList.add(productResponse);
            }
            String dataProduct = gson.toJson(responseList);
            redisTemplate.opsForValue().set("listAllProduct", dataProduct);
//            System.out.println(dataProduct);
        }
        return responseList;
    }

    @Override
//    @Cacheable("getProductByCategoryId")
    public List<ProductResponse> getProductByCategoryId(int id) {
        List<ProductResponse> responseList = new ArrayList<>();

        if(redisTemplate.hasKey("listProductByCategoryID")){
            String dataProduct = (String) redisTemplate.opsForValue().get("listProductByCategoryID");
            Type listType = new TypeToken<ArrayList<ProductResponse>>(){}.getType();
            responseList = new Gson().fromJson(dataProduct, listType);

        }else{
            List<ProductEntity> list = productRepository.findByCategoryId(id);
            for (ProductEntity productEntity : list){
                ProductResponse productResponse = new ProductResponse();
                productResponse.setId(productEntity.getId());
                productResponse.setName(productEntity.getName());
                productResponse.setImage(hostname+"/product/file/" + productEntity.getImage());
                productResponse.setPrice(productEntity.getPrice());

                responseList.add(productResponse);
            }
            String dataProduct = gson.toJson(responseList);
            redisTemplate.opsForValue().set("listProductByCategoryID", dataProduct);
//            System.out.println(dataProduct);
        }
        return responseList;
    }

    @Override
    public List<ProductResponse> getProductByColorId(int id) {
        List<ProductResponse> responseList = new ArrayList<>();

        if(redisTemplate.hasKey("listProductByColorID")){
            String dataProduct = (String) redisTemplate.opsForValue().get("listProductByColorID");
            Type listType = new TypeToken<ArrayList<ProductResponse>>(){}.getType();
            responseList = new Gson().fromJson(dataProduct, listType);

        }else{
            List<ProductEntity> list = productRepository.findByColorId(id);
            for (ProductEntity productEntity : list){
                ProductResponse productResponse = new ProductResponse();
                productResponse.setId(productEntity.getId());
                productResponse.setName(productEntity.getName());
                productResponse.setImage(hostname+"/product/file/" + productEntity.getImage());
                productResponse.setPrice(productEntity.getPrice());

                responseList.add(productResponse);
            }
            String dataProduct = gson.toJson(responseList);
            redisTemplate.opsForValue().set("listProductByColorID", dataProduct);
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

    @Override
    public ProductResponse getDetailProduct(int id) {
        Optional<ProductEntity> product =  productRepository.findById(id);
        ProductResponse productResponse = new ProductResponse();

        if(product.isPresent()){
            productResponse.setId(product.get().getId());
            productResponse.setPrice(product.get().getPrice());
            productResponse.setName(product.get().getName());
            productResponse.setDescription(product.get().getDescription());
            productResponse.setImage(product.get().getImageDetail());
        }

        return productResponse;
    }

    @Override
    @CacheEvict(value = "getProductByCategoryId", allEntries = true)
    public boolean clearCache() {
        return true;
    }
}
