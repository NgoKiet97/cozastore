package com.cybersoft.cozastore.controller;

import com.cybersoft.cozastore.payload.request.OrderRequest;
import com.cybersoft.cozastore.service.imp.IOrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/order")
public class OrderController {

    @Autowired
    private IOrderService iOrderService;

    @PostMapping("")
    public ResponseEntity<?> addOrder(@RequestBody OrderRequest orderRequest){
        iOrderService.addOrder(orderRequest);

        return new ResponseEntity<>("", HttpStatus.OK);
    }
}
