package com.blacksheep.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.blacksheep.domain.OrderItem;
import com.blacksheep.domain.OrderItemId;

public interface OrderItemRepository extends JpaRepository<OrderItem, OrderItemId> {

}
