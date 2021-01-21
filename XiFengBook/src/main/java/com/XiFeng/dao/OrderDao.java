package com.XiFeng.dao;

import com.XiFeng.pojo.Order;
import org.springframework.stereotype.Repository;

@Repository
public interface OrderDao {

    public int saveOrder(Order order);

}
