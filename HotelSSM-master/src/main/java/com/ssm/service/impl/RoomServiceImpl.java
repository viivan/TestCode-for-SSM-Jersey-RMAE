package com.ssm.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.ssm.dao.RoomMapper;
import com.ssm.pojo.Room;
import com.ssm.service.RoomService;

/**
 * 
 */
@Service("roomService")
public class RoomServiceImpl implements RoomService {

    @Resource
    private RoomMapper roomDao;

    /** 
     * @see com.ssm.service.RoomService#getRoomStates()
     */
    @Override
    public List<Room> getRoomStates() {
        return this.roomDao.selectAll();
    }

    /** 
     * @see com.ssm.service.RoomService#reduceRoomNum(int, java.lang.String)
     */
    @Override
    public int reduceRoomNum(int bookNum, String type) {
        return this.roomDao.reduceByPrimaryKey(bookNum, type);
    }

    /** 
     * @see com.ssm.service.RoomService#addRoomNum(int, java.lang.String)
     */
    @Override
    public int addRoomNum(int bookNum, String type) {
        return this.roomDao.addByPrimaryKey(bookNum, type);
    }

    /** 
     * @see com.ssm.service.RoomService#getPrice(java.lang.String)
     */
    @Override
    public double getPrice(String type) {
        return this.roomDao.selectByType(type);
    }

}