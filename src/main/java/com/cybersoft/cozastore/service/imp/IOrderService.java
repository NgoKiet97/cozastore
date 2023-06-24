package com.cybersoft.cozastore.service.imp;

import com.cybersoft.cozastore.payload.request.OrderRequest;

public interface IOrderService {
    boolean addOrder(OrderRequest orderRequest);
}
