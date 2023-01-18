package data;

import beans.Order;
import java.sql.*;
import java.util.*;

public class OrderData
{

    public ArrayList<Order> getAllOrders(Connection conn) throws SQLException
    {
        ArrayList<Order> orders = new ArrayList<>();

        String query1 = "SELECT * FROM orders ORDER BY date";
        Statement stmt = conn.createStatement();
        ResultSet rs = stmt.executeQuery(query1);

        while (rs.next())
        {
            Order order = new Order();
            order.setOrderId(rs.getInt("orderId"));
            order.setEmail(rs.getString("email"));
            order.setUserId(rs.getInt("userId"));
            order.setUserName(rs.getString("userName"));
            order.setUserSurname(rs.getString("userSurname"));
            order.setAddress(rs.getString("address"));
            order.setPhone(rs.getInt("phone"));
            order.setTotalPrice(rs.getDouble("totalPrice"));
            order.setDate(rs.getString("date"));

            orders.add(order);
        }

        return orders;
    }

    public ArrayList<Order> getOrdersByAgency(Connection conn, String storeId) throws SQLException
    {
        ArrayList<Order> orders = new ArrayList<>();

        String query1 = "SELECT * FROM orders WHERE storeId = '" + storeId + "' ORDER BY date";
        Statement stmt = conn.createStatement();
        ResultSet rs = stmt.executeQuery(query1);

        while (rs.next())
        {
            Order order = new Order();
            order.setOrderId(rs.getInt("orderId"));
            order.setEmail(rs.getString("email"));
            order.setUserId(rs.getInt("userId"));
            order.setUserName(rs.getString("userName"));
            order.setUserSurname(rs.getString("userSurname"));
            order.setAddress(rs.getString("address"));
            order.setPhone(rs.getInt("phone"));
            order.setTotalPrice(rs.getDouble("totalPrice"));
            order.setDate(rs.getString("date"));

            orders.add(order);
        }

        return orders;
    }

    public ArrayList<Order> getOrdersByUserId(Connection conn, int userId) throws SQLException
    {
        ArrayList<Order> orders = new ArrayList<>();

        String query1 = "SELECT * FROM orders WHERE orders.userId = ? ORDER BY orders.orderId DESC";
        PreparedStatement pstm = conn.prepareStatement(query1);
        pstm.setInt(1, userId);
        ResultSet rs = pstm.executeQuery();

        while (rs.next())
        {
            Order order = new Order();
            order.setOrderId(rs.getInt("orderId"));
            order.setEmail(rs.getString("email"));
            order.setUserId(rs.getInt("userId"));
            order.setUserName(rs.getString("userName"));
            order.setUserSurname(rs.getString("userSurname"));
            order.setAddress(rs.getString("address"));
            order.setPhone(rs.getInt("phone"));
            order.setTotalPrice(rs.getDouble("totalPrice"));
            order.setDate(rs.getString("date"));
            orders.add(order);
        }
        
        pstm.close();
        rs.close();

        return orders;
    }
}
