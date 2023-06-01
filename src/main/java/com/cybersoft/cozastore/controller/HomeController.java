package com.cybersoft.cozastore.controller;

import com.cybersoft.cozastore.payload.response.BaseResponse;
import com.cybersoft.cozastore.service.HomeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/home")
public class HomeController {

    @Autowired
    private HomeService homeService;

//    Cần phải xác định được kiểu JSON trả ra cho bên FE và để tiện cho BE xử lý chức
//    năng sau này

    @GetMapping("/category")
    public ResponseEntity<?> getAllCategory(){
        BaseResponse response = new BaseResponse();
        response.setData(homeService.getAllCategory());

        return new ResponseEntity<>(response, HttpStatus.OK);
    }

//    Viết API Lấy toàn bộ danh sách sản phẩm theo category id
//    Bước 1 : Phải xác định được kiểu JSON trả cho bên FE
//    Spring Security
}
