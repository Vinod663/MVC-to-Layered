package com.example.layeredarchitecture.DAO;

import com.example.layeredarchitecture.db.DBConnection;
import com.example.layeredarchitecture.model.OrderDetailDTO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

public class OrderDetailDAOImpl {
    public int setOrderDetailValue(OrderDetailDTO detail, String orderId) throws SQLException, ClassNotFoundException {
        /*Connection connection = DBConnection.getDbConnection().getConnection();
        PreparedStatement stm = connection.prepareStatement("INSERT INTO OrderDetails (oid, itemCode, unitPrice, qty) VALUES (?,?,?,?)");

        stm.setString(1, orderId);
        stm.setString(2, detail.getItemCode());
        stm.setBigDecimal(3, detail.getUnitPrice());
        stm.setInt(4, detail.getQty());

        return stm.executeUpdate();*/

        return SQLUtil.execute("INSERT INTO OrderDetails (oid, itemCode, unitPrice, qty) VALUES (?,?,?,?)",
                orderId, detail.getItemCode(), detail.getUnitPrice(), detail.getQty());
    }
}
