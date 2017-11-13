/**
 */
package com.jersey.controller;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import javax.ws.rs.FormParam;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;

import com.alibaba.fastjson.JSONObject;
import com.jersey.pojo.Room;
import com.jersey.util.JdbcUtil;

/**
 *
 */
@Path("room")
public class RoomController {
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public String showStates() {
        JSONObject jsonObject = new JSONObject();
        try {
            Connection conn = JdbcUtil.getConnection();
            PreparedStatement st = conn.prepareStatement("select * from room");
            ResultSet rs = st.executeQuery();
            List<Room> rooms = new ArrayList<Room>();
            while (rs.next()) {
                Room room = new Room();
                room.setId(rs.getInt(1));
                room.setType(rs.getString(2));
                room.setLeftNum(rs.getInt(3));
                room.setPrice(rs.getDouble(4));
                rooms.add(room);
            }
            JdbcUtil.closeAll(rs, st, conn);
            jsonObject.put("reslut", rooms);
            jsonObject.put("success", true);
        } catch (Exception e) {
            jsonObject.put("success", false);
            jsonObject.put("errorMsg", e.getMessage());
        }
        return jsonObject.toJSONString();
    }

    @POST
    @Path("/addNum")
    @Produces(MediaType.APPLICATION_JSON)
    public String deleteOrder(@FormParam("book_num") int bookNum,
                              @FormParam("room_type") String roomType) {
        JSONObject jsonObject = new JSONObject();
        try {
            Connection conn = JdbcUtil.getConnection();
            PreparedStatement st = conn
                .prepareStatement("update room set left_num = left_num + ? where type = ?");
            st.setObject(1, bookNum);
            st.setObject(2, roomType);
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
    @Path("/reduceNum")
    @Produces(MediaType.APPLICATION_JSON)
    public String reduceNum(@FormParam("book_num") int bookNum,
                            @FormParam("room_type") String roomType) {
        JSONObject jsonObject = new JSONObject();
        try {
            Connection conn = JdbcUtil.getConnection();
            PreparedStatement st = conn
                .prepareStatement("update room set left_num = left_num - ? where type = ?");
            st.setObject(1, bookNum);
            st.setObject(2, roomType);
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

    @GET
    @Path("/calPrice")
    @Produces(MediaType.APPLICATION_JSON)
    public String addOrder(@QueryParam("room_type") String roomType,
                           @QueryParam("old_room_type") String oldRoomType) {
        JSONObject jsonObject = new JSONObject();
        try {
            Connection conn = JdbcUtil.getConnection();
            PreparedStatement st = conn.prepareStatement(
                "select ( select price from room where type = ? ) - ( select price from room where type = ? )");
            st.setObject(1, roomType);
            st.setObject(2, oldRoomType);
            ResultSet rs = st.executeQuery();
            double result = -1;
            if (rs.next()) {
                result = rs.getDouble(1);
            }
            JdbcUtil.closeAll(rs, st, conn);
            jsonObject.put("result", result);
            jsonObject.put("success", true);
        } catch (Exception e) {
            jsonObject.put("success", false);
            jsonObject.put("errorMsg", e.getMessage());
        }
        return jsonObject.toJSONString();
    }
}
