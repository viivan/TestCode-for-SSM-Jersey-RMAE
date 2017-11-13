package com.ssm.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.ssm.dao.OrderMapper;
import com.ssm.pojo.Order;
import com.ssm.service.OrderService;

/**
 * 
 */
@Service("orderService")
public class OrderServiceImpl implements OrderService {

    @Resource
    private OrderMapper orderDao;

    /** 
     * @see com.ssm.service.OrderService#addOrder(com.ssm.pojo.Order)
     */
    @Override
    public int addOrder(Order record) {
        return this.orderDao.insertSelective(record);
    }

    /** 
     * @see com.ssm.service.OrderService#pay(com.ssm.pojo.Order)
     */
    @Override
    public int pay(Order record) {
        return this.orderDao.updateByPrimaryKeySelective(record);
    }

    /** 
     * @see com.ssm.service.OrderService#deleteOrder(int)
     */
    @Override
    public int deleteOrder(int id) {
        return this.orderDao.deleteByPrimaryKey(id);
    }

    /** 
     * @see com.ssm.service.OrderService#getOrders(int)
     */
    @Override
    public List<Order> getOrders(int userId) {
        return this.orderDao.selectByUserId(userId);
    }
}