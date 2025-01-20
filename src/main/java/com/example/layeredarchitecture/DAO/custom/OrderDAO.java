package com.example.layeredarchitecture.DAO.custom;

import com.example.layeredarchitecture.DAO.CrudDAO;
import com.example.layeredarchitecture.model.OrderDTO;

import java.sql.SQLException;

public interface OrderDAO extends CrudDAO<OrderDTO> {
//    String generateNewOrderId() throws SQLException, ClassNotFoundException;
   boolean saveOrder(String orderId) throws SQLException, ClassNotFoundException;
    int setOrderValues(OrderDTO orderDTO) throws SQLException, ClassNotFoundException;
}
