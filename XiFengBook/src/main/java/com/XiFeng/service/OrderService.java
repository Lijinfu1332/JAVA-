package com.XiFeng.service;

import com.XiFeng.pojo.Cart;
public interface OrderService {
    public String createOrder(Cart cart,Integer userId);
}
