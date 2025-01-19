package com.example.layeredarchitecture.DAO;

import com.example.layeredarchitecture.db.DBConnection;
import com.example.layeredarchitecture.model.OrderDTO;

import java.sql.*;

public class OrderDAOImpl {
    public String generateNewOrderId() throws SQLException, ClassNotFoundException {
        /*Connection connection = DBConnection.getDbConnection().getConnection();
        Statement stm = connection.createStatement();
        ResultSet rst = stm.executeQuery("SELECT oid FROM `Orders` ORDER BY oid DESC LIMIT 1");*/
        ResultSet rst=SQLUtil.execute("SELECT oid FROM `Orders` ORDER BY oid DESC LIMIT 1");

        return rst.next() ? String.format("OID-%03d", (Integer.parseInt(rst.getString("oid").replace("OID-", "")) + 1)) : "OID-001";
    }

    public boolean saveOrder(String orderId) throws SQLException, ClassNotFoundException {
        /*Connection connection = null;

        connection = DBConnection.getDbConnection().getConnection();
        PreparedStatement stm = connection.prepareStatement("SELECT oid FROM `Orders` WHERE oid=?");
        stm.setString(1, orderId);*/

        ResultSet result =SQLUtil.execute("SELECT oid FROM `Orders` WHERE oid=?", orderId);

        return result.next();
    }

    public int setOrderValues(OrderDTO orderDTO) throws SQLException, ClassNotFoundException {
        /*Connection connection = null;
        connection = DBConnection.getDbConnection().getConnection();
        PreparedStatement stm = connection.prepareStatement("INSERT INTO `Orders` (oid, date, customerID) VALUES (?,?,?)");
        stm.setString(1, orderDTO.getOrderId());
        stm.setDate(2, Date.valueOf(orderDTO.getOrderDate()));
        stm.setString(3, orderDTO.getCustomerId().toString());
        return stm.executeUpdate();*/

        return SQLUtil.execute("INSERT INTO `Orders` (oid, date, customerID) VALUES (?,?,?)",
                orderDTO.getOrderId(),
                Date.valueOf(orderDTO.getOrderDate()),
                orderDTO.getCustomerId().toString());
    }
}
