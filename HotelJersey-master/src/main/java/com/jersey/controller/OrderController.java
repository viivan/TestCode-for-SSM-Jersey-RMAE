/**
 * Copyright (c) 2011-2017 All Rights Reserved.
 */
package com.jersey.controller;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import javax.ws.rs.DELETE;
import javax.ws.rs.FormParam;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import com.alibaba.fastjson.JSONObject;
import com.jersey.pojo.Order;
import com.jersey.util.JdbcUtil;

/**
 *
 */
@Path("order")
public class OrderController {
    @GET
    @Path("/user/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public String showOrders(@PathParam("id") int userId) {
        JSONObject jsonObject = new JSONObject();
        try {
            Connection conn = JdbcUtil.getConnection();
            PreparedStatement st = conn.prepareStatement("select * from `order` where user_id = ?");
            st.setObject(1, userId);
            ResultSet rs = st.executeQuery();
            List<Order> orders = new ArrayList<Order>();
            while (rs.next()) {
                Order order = new Order();
                order.setId(rs.getInt(1));
                order.setUserId(rs.getInt(2));
                order.setRoomId(rs.getInt(3));
                order.setBookNum(rs.getInt(4));
                order.setGuest(rs.getString(5));
                order.setPhone(rs.getString(6));
                order.setStates(rs.getString(7));
                orders.add(order);
            }
            JdbcUtil.closeAll(rs, st, conn);
            jsonObject.put("reslut", orders);
            jsonObject.put("success", true);
        } catch (Exception e) {
            jsonObject.put("success", false);
            jsonObject.put("errorMsg", e.getMessage());
        }
        return jsonObject.toJSONString();
    }

    @DELETE
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public String deleteOrder(@PathParam("id") int id) {
        JSONObject jsonObject = new JSONObject();
        try {
            Connection conn = JdbcUtil.getConnection();
            PreparedStatement st = conn.prepareStatement("delete from `order` where id = ?");
            st.setObject(1, id);
            int result = st.executeUpdate();
            JdbcUtil.closeStatement(st);
            JdbcUtil.closeConnection(conn);
            jsonObject.put("reslut", result);
            jsonObject.put("success", true);
        } catch (Exception e) {
            jsonObject.put("success", false);
            jsonObject.put("errorMsg", e.getMessage());
        }
        return jsonObject.toJSONString();
    }

    @POST
    @Path("/pay/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public String changeStates(@PathParam("id") int id) {
        JSONObject jsonObject = new JSONObject();
        try {
            Connection conn = JdbcUtil.getConnection();
            PreparedStatement st = conn
                .prepareStatement("update `order` set states = '已付款' where id = ?");
            st.setObject(1, id);
            int result = st.executeUpdate();
            JdbcUtil.closeStatement(st);
            JdbcUtil.closeConnection(conn);
            jsonObject.put("reslut", result);
            jsonObject.put("success", true);
        } catch (Exception e) {
            jsonObject.put("success", false);
            jsonObject.put("errorMsg", e.getMessage());
        }
        return jsonObject.toJSONString();
    }

    @PUT
    @Produces(MediaType.APPLICATION_JSON)
    public String addOrder(@FormParam("user_id") int userId, @FormParam("room_id") int roomId,
                           @FormParam("book_num") int bookNum, @FormParam("guest") String guest,
                           @FormParam("phone") String phone) {
        JSONObject jsonObject = new JSONObject();
        try {
            Connection conn = JdbcUtil.getConnection();
            PreparedStatement st = conn
                .prepareStatement("insert into `order` values(null,?,?,?,?,?,'未付款')");
            st.setObject(1, userId);
            st.setObject(2, roomId);
            st.setObject(3, bookNum);
            st.setObject(4, guest);
            st.setObject(5, phone);
            int result = st.executeUpdate();
            JdbcUtil.closeStatement(st);
            JdbcUtil.closeConnection(conn);
            jsonObject.put("result", result);
            jsonObject.put("success", true);
        } catch (Exception e) {
            jsonObject.put("success", false);
            jsonObject.put("errorMsg", e.getMessage());
        }
        return jsonObject.toJSONString();
    }
}
