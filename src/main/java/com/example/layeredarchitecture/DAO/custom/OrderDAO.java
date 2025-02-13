package com.example.layeredarchitecture.DAO.custom;

import com.example.layeredarchitecture.DAO.CrudDAO;
import com.example.layeredarchitecture.dto.OrderDTO;
import com.example.layeredarchitecture.entity.Order;

import java.sql.SQLException;

public interface OrderDAO extends CrudDAO<Order> {
//    String generateNewOrderId() throws SQLException, ClassNotFoundException;
   boolean saveOrder(String orderId) throws SQLException, ClassNotFoundException;
    int setOrderValues(Order order) throws SQLException, ClassNotFoundException;
}
