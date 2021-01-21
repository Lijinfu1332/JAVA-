package com.XiFeng.dao;

import com.XiFeng.pojo.OrderItem;
import org.springframework.stereotype.Repository;

@Repository
public interface OrderItemDao {
    public int saveOrderItem(OrderItem orderItem);
}
