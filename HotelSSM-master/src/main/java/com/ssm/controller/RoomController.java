package com.ssm.controller;

import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.ssm.pojo.Room;
import com.ssm.service.RoomService;

/**
 * 
 */
@Controller
@RequestMapping(value = "/room")
public class RoomController {

    @Resource
    private RoomService roomService;

    @RequestMapping(method = { RequestMethod.GET })
    @ResponseBody
    public List<Room> showStates(HttpServletRequest request) {
        List<Room> rooms = this.roomService.getRoomStates();
        return rooms;
    }

    @RequestMapping(value = "/reduceNum", method = { RequestMethod.POST })
    @ResponseBody
    public int reduceNum(HttpServletRequest request) {
        int bookNum = Integer.parseInt(request.getParameter("book_num"));
        String type = request.getParameter("room_type");
        int i = this.roomService.reduceRoomNum(bookNum, type);
        return i;
    }

    @RequestMapping(value = "/addNum", method = { RequestMethod.POST })
    @ResponseBody
    public int addNum(HttpServletRequest request) {
        int bookNum = Integer.parseInt(request.getParameter("book_num"));
        String type = request.getParameter("room_type");
        int i = this.roomService.addRoomNum(bookNum, type);
        return i;
    }

    @RequestMapping(value = "/calPrice", method = { RequestMethod.GET })
    @ResponseBody
    public double calPrice(HttpServletRequest request) {
        String type = request.getParameter("room_type");
        String oldType = request.getParameter("old_room_type");
        double price = this.roomService.getPrice(type);
        double oldPrice = this.roomService.getPrice(oldType);
        double diff = price - oldPrice;
        return diff;
    }
}