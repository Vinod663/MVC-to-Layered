package com.example.layeredarchitecture.bo.impl;

import com.example.layeredarchitecture.DAO.DAOFactory;
import com.example.layeredarchitecture.DAO.custom.OrderDAO;
import com.example.layeredarchitecture.dto.OrderDTO;
import com.example.layeredarchitecture.entity.Order;

import java.sql.SQLException;
import java.util.ArrayList;

public class OrderBOImpl {
    OrderDAO orderDAO = (OrderDAO) DAOFactory.getInstance().getDAO(DAOFactory.DAOType.ORDER);

    public ArrayList<OrderDTO> getAll() throws SQLException, ClassNotFoundException {
        return null;
    }


    public void save(String id, String name, String address) throws SQLException, ClassNotFoundException {
        orderDAO.save(id, name, address);
    }


//    public void save(OrderDTO DTO) throws SQLException, ClassNotFoundException {
//        orderDAO.save(DTO);
//    }


//    public void update(OrderDTO DTO) throws SQLException, ClassNotFoundException {
//        orderDAO.update(DTO);
//    }


    public void delete(String id) throws SQLException, ClassNotFoundException {
        orderDAO.delete(id);
    }


    public boolean exist(String id) throws SQLException, ClassNotFoundException {
        return orderDAO.exist(id);
    }


    public String generateNewId() throws SQLException, ClassNotFoundException {

        return orderDAO.generateNewId();
    }


    public OrderDTO search(String newValue) throws SQLException, ClassNotFoundException {
        Order order=orderDAO.search(newValue);
        return new OrderDTO(order.getOrderId(),order.getOrderDate(),order.getCustomerId(),order.getCustomerName(),
                order.getOrderTotal());
        //return orderDAO.search(newValue);
    }


    public boolean saveOrder(String orderId) throws SQLException, ClassNotFoundException {

        return orderDAO.saveOrder(orderId);
    }


    public int setOrderValues(OrderDTO orderDTO) throws SQLException, ClassNotFoundException {
       return orderDAO.setOrderValues(new Order(orderDTO.getOrderId(),orderDTO.getOrderDate(),orderDTO.getCustomerId(),
               orderDTO.getCustomerName(),orderDTO.getOrderTotal()));
    }
}
