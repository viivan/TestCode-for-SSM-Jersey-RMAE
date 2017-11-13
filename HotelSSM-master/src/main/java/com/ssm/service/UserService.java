package com.ssm.service;

import com.ssm.pojo.User;

/**
 * 
 * @author Luxus_C
 * @version $Id: IUserService.java, v 0.1 2017年10月20日 下午8:46:53 Luxus_C Exp $
 */
public interface UserService {

    /**
     * 根据id查询用户
     * @param userId
     * @return
     */
    public User getUserById(int userId);
}