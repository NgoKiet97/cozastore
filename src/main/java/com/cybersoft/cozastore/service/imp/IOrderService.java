package com.cybersoft.cozastore.service.imp;

import com.cybersoft.cozastore.payload.request.OrderRequest;

import javax.servlet.http.HttpServletRequest;

public interface IOrderService {
    boolean addOrder(HttpServletRequest request, OrderRequest orderRequest);
}
