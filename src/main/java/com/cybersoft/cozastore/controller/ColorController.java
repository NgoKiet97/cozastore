package com.cybersoft.cozastore.controller;

import com.cybersoft.cozastore.payload.response.BaseResponse;
import com.cybersoft.cozastore.service.imp.IColorService;
import com.google.gson.Gson;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/color")
public class ColorController {

    private Gson gson = new Gson();
    private Logger logger = LoggerFactory.getLogger(ColorController.class);

    @Autowired
    private IColorService iColorService;
    @GetMapping("")
    public ResponseEntity<?> getAllColor(){
        BaseResponse baseResponse = new BaseResponse();
        baseResponse.setData(iColorService.getAllColor());

        String dataLog = gson.toJson(baseResponse);
        logger.info(dataLog);

        return new ResponseEntity<>(baseResponse, HttpStatus.OK);
    }
}
