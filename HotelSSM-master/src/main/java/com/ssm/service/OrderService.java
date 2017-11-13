package com.ssm.service;

import java.util.List;

import com.ssm.pojo.Order;

/**
 * 
 */
public interface OrderService {

    /**
     * 添加订单
     * @param record
     * @return
     */
    public int addOrder(Order record);

    /**
     * 付款
     * @param record
     * @return
     */
    public int pay(Order record);

    /**
     * 删除订单
     * @param id
     * @return
     */
    public int deleteOrder(int id);

    /**
     * 查询用户所有订单
     * @param userId
     * @return
     */
    public List<Order> getOrders(int userId);
}