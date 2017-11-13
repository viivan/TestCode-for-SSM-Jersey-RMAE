package com.ssm.service;

import com.ssm.pojo.User;

/**
 * 
 */
public interface UserService {

    /**
     * 根据id查询用户
     * @param userId
     * @return
     */
    public User getUserById(int userId);
}