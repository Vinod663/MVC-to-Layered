package com.example.layeredarchitecture.DAO.custom.impl;

import com.example.layeredarchitecture.DAO.SQLUtil;
import com.example.layeredarchitecture.DAO.custom.OrderDetailDAO;
import com.example.layeredarchitecture.model.OrderDetailDTO;

import java.sql.SQLException;

public class OrderDetailDAOImpl implements OrderDetailDAO {
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
