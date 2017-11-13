/**
 * Viivan.
 * Copyright (c) 2011-2017 All Rights Reserved.
 */
package com.jersey.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 *

 */
public class JdbcUtil {

    public static final String DRIVER   = "com.mysql.jdbc.Driver";
    public static final String URL      = "jdbc:mysql://localhost:3306/hotel?serverTimezone=UTC";
    public static final String USERNAME = "test_user";
    public static final String PASSWORD = "common_password";

    /**
     * 通过静态代码块 注册数据库驱动
     */
    static {
        try {
            Class.forName(DRIVER);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    /**
     * 获得Connection
     *
     * @return
     */
    public static Connection getConnection() {
        Connection conn = null;
        try {
            conn = DriverManager.getConnection(URL, USERNAME, PASSWORD);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return conn;
    }

    /**
     * 获得Statement
     *
     * @return
     */
    public static Statement getStatement() {
        Statement st = null;
        try {
            st = getConnection().createStatement();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return st;
    }

    /**
     * 关闭ResultSet
     *
     * @param rs
     */
    public static void closeResultSet(ResultSet rs) {
        if (rs != null) {
            try {
                rs.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * 关闭Statement
     *
     * @param st
     */
    public static void closeStatement(Statement st) {
        if (st != null) {
            try {
                st.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * 关闭Connection
     *
     * @param conn
     */
    public static void closeConnection(Connection conn) {
        if (conn != null) {
            try {
                conn.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * 关闭全部
     *
     * @param rs
     * @param st
     * @param conn
     */
    public static void closeAll(ResultSet rs, Statement st, Connection conn) {
        closeResultSet(rs);
        closeStatement(st);
        closeConnection(conn);
    }
}
