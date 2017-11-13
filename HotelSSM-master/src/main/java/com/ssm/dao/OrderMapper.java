package com.ssm.dao;

import java.util.List;

import com.ssm.pojo.Order;

/**
 * 
 * @author Luxus_C
 * @version $Id: OrderMapper.java, v 0.1 2017年10月20日 下午8:47:40 Luxus_C Exp $
 */
public interface OrderMapper {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table order
     * @param id
     * @return
     */
    int deleteByPrimaryKey(Integer id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table order
     * @param record
     * @return
     */
    int insert(Order record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table order
     * @param record
     * @return
     */
    int insertSelective(Order record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table order
     * @param id
     * @return
     */
    Order selectByPrimaryKey(Integer id);

    /**
     * 根据用户id查询所有Order
     * @param userId
     * @return
     */
    List<Order> selectByUserId(Integer userId);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table order
     * @param record
     * @return
     */
    int updateByPrimaryKeySelective(Order record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table order
     * @param record
     * @return
     */
    int updateByPrimaryKey(Order record);
}