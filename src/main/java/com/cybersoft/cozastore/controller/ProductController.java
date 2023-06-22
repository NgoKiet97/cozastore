package com.cybersoft.cozastore.controller;

import com.cybersoft.cozastore.payload.request.ProductRequest;
import com.cybersoft.cozastore.payload.response.BaseResponse;
import com.cybersoft.cozastore.service.imp.ICategoryService;
import com.cybersoft.cozastore.service.imp.IProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.io.FileNotFoundException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;

@RestController
@RequestMapping("/product")
public class ProductController {

    @Value("${root.file.path}")
    private String rootPath;

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
            String rootFolder = rootPath;
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

    @GetMapping("/file/{filename}")
    public ResponseEntity<?> downloadFileProduct(@PathVariable String filename) throws FileNotFoundException {
        try {
            Path path = Paths.get(rootPath);
            Path pathFile = path.resolve(filename);
            Resource resource = new UrlResource(pathFile.toUri());

            if(resource.exists() || resource.isReadable()){
                return ResponseEntity.ok()
                        .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + filename + "\"")
                        .body(resource);
            } else{
                throw new FileNotFoundException("Không tìm thấy file");
//                        RuntimeException("Không tìm thấy file");
            }
        } catch (Exception e){
            throw new FileNotFoundException("Không tìm thấy file");
        }

    }
}
