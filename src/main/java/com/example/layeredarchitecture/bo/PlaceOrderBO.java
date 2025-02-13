package com.example.layeredarchitecture.bo;

import com.example.layeredarchitecture.dto.CustomerDTO;
import com.example.layeredarchitecture.dto.ItemDTO;
import com.example.layeredarchitecture.dto.OrderDTO;
import com.example.layeredarchitecture.dto.OrderDetailDTO;
import com.example.layeredarchitecture.entity.Order;

import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public interface PlaceOrderBO extends SuperBO {
    CustomerDTO searchCustomer(String id) throws SQLException, ClassNotFoundException;
    boolean existItem(String code) throws SQLException, ClassNotFoundException;
    boolean existCustomer(String id) throws SQLException, ClassNotFoundException;
    String generateNewOrderId() throws SQLException, ClassNotFoundException;

    ArrayList<CustomerDTO> getAllCustomerIds() throws SQLException, ClassNotFoundException;

    ArrayList<ItemDTO> getAllItemIds() throws SQLException, ClassNotFoundException;

    ItemDTO searchItem(String code) throws SQLException, ClassNotFoundException;

    OrderDTO search(String newValue) throws SQLException, ClassNotFoundException;

    int setOrderValues(OrderDTO orderDTO) throws SQLException, ClassNotFoundException;

    boolean saveOrder(String orderId, LocalDate orderDate, String customerId, List<OrderDetailDTO> orderDetails);
    ItemDTO findItem(String code);
    boolean saveOrder(String orderId) throws SQLException, ClassNotFoundException;
}
