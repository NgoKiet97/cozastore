package com.cybersoft.cozastore.repository;

import com.cybersoft.cozastore.entity.OrderDetailEntity;
import com.cybersoft.cozastore.entity.ids.OrderDetailIds;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OrderDetailRepository extends JpaRepository<OrderDetailEntity, OrderDetailIds> {
}
