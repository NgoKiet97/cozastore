package com.cybersoft.cozastore.service;

import com.cybersoft.cozastore.entity.CountryEntity;
import com.cybersoft.cozastore.entity.OrderDetailEntity;
import com.cybersoft.cozastore.entity.OrderEntity;
import com.cybersoft.cozastore.entity.UserEntity;
import com.cybersoft.cozastore.entity.ids.OrderDetailIds;
import com.cybersoft.cozastore.exception.UserNotFoundException;
import com.cybersoft.cozastore.payload.request.OrderProductRequest;
import com.cybersoft.cozastore.payload.request.OrderRequest;
import com.cybersoft.cozastore.repository.OrderDetailRepository;
import com.cybersoft.cozastore.repository.OrderRepository;
import com.cybersoft.cozastore.repository.UserRepository;
import com.cybersoft.cozastore.service.imp.IOrderService;
import com.cybersoft.cozastore.utils.JWTHelperUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.criteria.Order;
import javax.servlet.http.HttpServletRequest;

@Service
@Transactional
public class OrderService implements IOrderService {

    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private OrderDetailRepository orderDetailRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private JWTHelperUtils jwtHelperUtils;


    @Override
    public boolean addOrder(HttpServletRequest request, OrderRequest orderRequest) {

        String headerAuthorization = request.getHeader("Authorization");
        if(headerAuthorization == null || headerAuthorization.isEmpty()){
            throw new UserNotFoundException("Bạn không có quyền sử dụng tính nng này", 500);
        }

        String token = headerAuthorization.substring(7);
        String dataToken = jwtHelperUtils.validToken(token);

        UserEntity userEntity = userRepository.findByUsername(dataToken);
        if (userEntity == null){
            throw new UserNotFoundException("Bạn không có quyền sử dụng tính năng này", 500);
        }

        //Thêm mới Order
        CountryEntity country = new CountryEntity();
        country.setId(orderRequest.getCountryId());

        OrderEntity orderEntity = new OrderEntity();
        orderEntity.setCountry(country);
        orderEntity.setUser(userEntity);

        orderRepository.save(orderEntity);

        //Thêm mới OrderDetail
        for (OrderProductRequest data : orderRequest.getListProduct()) {
            OrderDetailIds orderDetailIds = new OrderDetailIds();
            orderDetailIds.setOrderId(orderEntity.getId());
            orderDetailIds.setProductId(data.getId());


            OrderDetailEntity orderDetailEntity = new OrderDetailEntity();
            orderDetailEntity.setIds(orderDetailIds);
            orderDetailEntity.setPrice(data.getPrice());
            orderDetailEntity.setQuantity(data.getQuantity());

            orderDetailRepository.save(orderDetailEntity);
        }
        return false;
    }
}
