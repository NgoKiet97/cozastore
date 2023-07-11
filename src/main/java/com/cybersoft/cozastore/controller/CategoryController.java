package com.cybersoft.cozastore.controller;

import com.cybersoft.cozastore.payload.response.BaseResponse;
import com.cybersoft.cozastore.service.imp.ICategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/category")
@CrossOrigin("*")
public class CategoryController {

    @Autowired
    ICategoryService iCategoryService;

    @GetMapping("")
    public ResponseEntity<?> getAllCategory(){
        BaseResponse baseResponse = new BaseResponse();
        baseResponse.setData(iCategoryService.getAllCategory());
        return new ResponseEntity<>(baseResponse, HttpStatus.OK);
    }
}
