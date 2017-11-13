package com.ssm.service.impl;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.ssm.dao.UserMapper;
import com.ssm.pojo.User;
import com.ssm.service.UserService;

/**
 * 
 */
@Service("userService")
public class UserServiceImpl implements UserService {

    @Resource
    private UserMapper userDao;

    /** 
     * @see com.ssm.service.UserService#getUserById(int)
     */
    @Override
    public User getUserById(int userId) {
        return this.userDao.selectByPrimaryKey(userId);
    }

}