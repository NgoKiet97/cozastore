package com.cybersoft.cozastore.controller;

import com.cybersoft.cozastore.payload.response.BaseResponse;
import com.cybersoft.cozastore.service.imp.ISizeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/size")
public class SizeController {

    @Autowired
    private ISizeService iSizeService;

    @GetMapping("")
    public ResponseEntity<?> getAllSize(){
        BaseResponse baseResponse = new BaseResponse();
        baseResponse.setData(iSizeService.getAllSize());
        return new ResponseEntity<>(baseResponse, HttpStatus.OK);
    }
}
