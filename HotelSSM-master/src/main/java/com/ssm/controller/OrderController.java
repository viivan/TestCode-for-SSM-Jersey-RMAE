package com.ssm.controller;

import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.ssm.pojo.Order;
import com.ssm.service.OrderService;

/**
 * 
 */
@Controller
@RequestMapping("/order")
public class OrderController {

    @Resource
    private OrderService orderService;

    @RequestMapping(method = { RequestMethod.PUT })
    @ResponseBody
    public int addOrder(HttpServletRequest request) {
        Order record = new Order();
        record.setUserId(Integer.parseInt(request.getParameter("user_id")));
        record.setRoomId(Integer.parseInt(request.getParameter("room_id")));
        record.setBookNum(Integer.parseInt(request.getParameter("book_num")));
        record.setGuest(request.getParameter("guest"));
        record.setPhone(request.getParameter("phone"));
        int i = this.orderService.addOrder(record);
        return i;
    }

    @RequestMapping(value = "/pay/{id}", method = { RequestMethod.POST })
    @ResponseBody
    public int changeStates(@PathVariable("id") int id) {
        Order record = new Order();
        record.setId(id);
        record.setStates("已付款");
        int i = this.orderService.pay(record);
        return i;
    }

    @RequestMapping(value = "/{id}", method = { RequestMethod.DELETE })
    @ResponseBody
    public int deleteOrder(@PathVariable("id") int id) {
        int i = this.orderService.deleteOrder(id);
        return i;
    }

    @RequestMapping(value = "user/{id}", method = { RequestMethod.GET })
    @ResponseBody
    public List<Order> showOrders(@PathVariable("id") int userId) {
        List<Order> orders = this.orderService.getOrders(userId);
        return orders;
    }
}