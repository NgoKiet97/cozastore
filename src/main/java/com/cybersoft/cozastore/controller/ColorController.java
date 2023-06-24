package com.cybersoft.cozastore.controller;

import com.cybersoft.cozastore.payload.response.BaseResponse;
import com.cybersoft.cozastore.service.imp.IColorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/color")
public class ColorController {

    @Autowired
    private IColorService iColorService;
    @GetMapping("")
    public ResponseEntity<?> getAllColor(){
        BaseResponse baseResponse = new BaseResponse();
        baseResponse.setData(iColorService.getAllColor());

        return new ResponseEntity<>(baseResponse, HttpStatus.OK);
    }
}
