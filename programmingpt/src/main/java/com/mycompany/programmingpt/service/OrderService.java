package com.mycompany.programmingpt.service;

import com.mycompany.programmingpt.model.Order;
import com.mycompany.programmingpt.model.OrderItem;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

public class OrderService {

    public void order(List<OrderItem> orderItems) {
        Order order = new Order();
        order.setUuid(UUID.randomUUID().toString());
        order.setOrderItems(orderItems);
        order.setDateTime(LocalDateTime.now());
        applyTotal(order);
    }

    private void applyTotal(Order order) {
        double total = 0;
        for (OrderItem orderItem : order.getOrderItems()) {
            total += orderItem.getMenu().getPrice() * orderItem.getQty();
        }
        order.setTotal(total);
    }
}
