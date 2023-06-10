package com.cybersoft.cozastore.controller;

import com.cybersoft.cozastore.payload.request.ProductRequest;
import com.cybersoft.cozastore.payload.response.BaseResponse;
import com.cybersoft.cozastore.service.imp.ICategoryService;
import com.cybersoft.cozastore.service.imp.IProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;

@RestController
@RequestMapping("/product")
public class ProductController {

    @Autowired
    IProductService iProductService;

    @GetMapping("/{id}")
    public ResponseEntity<?> getProductByCategoryId(@PathVariable int id){
        BaseResponse baseResponse = new BaseResponse();
        baseResponse.setData(iProductService.getProductByCategoryId(id));

        return new ResponseEntity<>(baseResponse, HttpStatus.OK);
    }

    @PostMapping("")
    public ResponseEntity<?> addProduct(@Valid ProductRequest productRequest){
        String fileName = productRequest.getFileImage().getOriginalFilename();
        try {
            String rootFolder = "D:\\CyberSoft-20230506T043821Z-001\\CyberSoft\\Spring\\10-05-2023-06-34-44-cozastore-3\\image";
            Path pathRoot = Paths.get(rootFolder);
            if(!Files.exists(pathRoot)){
                Files.createDirectory(pathRoot);
            }
            Files.copy(productRequest.getFileImage().getInputStream(), pathRoot.resolve(fileName), StandardCopyOption.REPLACE_EXISTING);
            iProductService.addProduct(productRequest);
        } catch (Exception e){

        }

        return new ResponseEntity<>(fileName, HttpStatus.OK);
    }
}
