package com.example.layeredarchitecture.bo.impl;

import com.example.layeredarchitecture.DAO.DAOFactory;
import com.example.layeredarchitecture.DAO.custom.*;
import com.example.layeredarchitecture.DAO.custom.impl.OrderDetailDAOImpl;
import com.example.layeredarchitecture.bo.PlaceOrderBO;
import com.example.layeredarchitecture.db.DBConnection;
import com.example.layeredarchitecture.dto.CustomerDTO;
import com.example.layeredarchitecture.dto.ItemDTO;
import com.example.layeredarchitecture.dto.OrderDTO;
import com.example.layeredarchitecture.dto.OrderDetailDTO;
import com.example.layeredarchitecture.entity.Customer;
import com.example.layeredarchitecture.entity.Item;
import com.example.layeredarchitecture.entity.Order;
import com.example.layeredarchitecture.entity.OrderDetail;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class PlaceOrderBOImpl implements PlaceOrderBO {
    CustomerDAO customerDAO= (CustomerDAO) DAOFactory.getInstance().getDAO(DAOFactory.DAOType.CUSTOMER);
    ItemDAO itemDAO= (ItemDAO) DAOFactory.getInstance().getDAO(DAOFactory.DAOType.ITEM);
    OrderDAO orderDAO= (OrderDAO) DAOFactory.getInstance().getDAO(DAOFactory.DAOType.ORDER);
    OrderDetailDAO orderDetailDAO= (OrderDetailDAO) DAOFactory.getInstance().getDAO(DAOFactory.DAOType.ORDER_DETAIL);
    QueryDAO queryDAO= (QueryDAO) DAOFactory.getInstance().getDAO(DAOFactory.DAOType.QUERY);


        @Override
    public CustomerDTO searchCustomer(String id) throws SQLException, ClassNotFoundException {
            Customer customer=customerDAO.search(id);
            CustomerDTO customerDTO=new CustomerDTO();
            customerDTO.setId(customer.getId());
            customerDTO.setName(customer.getName());
            customerDTO.setAddress(customer.getAddress());

        return customerDTO;
    }
    @Override
    public boolean existItem(String code) throws SQLException, ClassNotFoundException {
        return itemDAO.exist(code);
    }
    @Override
    public boolean existCustomer(String id) throws SQLException, ClassNotFoundException {
        return customerDAO.exist(id);
    }

    @Override
    public boolean saveOrder(String orderId) throws SQLException, ClassNotFoundException {

        return orderDAO.saveOrder(orderId);
    }

    @Override
    public String generateNewOrderId() throws SQLException, ClassNotFoundException {

        return orderDAO.generateNewId();
    }

    @Override
    public ArrayList<CustomerDTO> getAllCustomerIds() throws SQLException, ClassNotFoundException {
        ArrayList<CustomerDTO> customerDTOs = new ArrayList<>();
        ArrayList<Customer> customers = customerDAO.getAll();
        for (Customer customer : customers) {
            customerDTOs.add(new CustomerDTO(customer.getId(), customer.getName(), customer.getAddress()));
        }
        return customerDTOs;
    }
    @Override
    public ArrayList<ItemDTO> getAllItemIds() throws SQLException, ClassNotFoundException {
        ArrayList<ItemDTO> itemDTOArrayList = new ArrayList<>();
        ArrayList<Item>items=itemDAO.getAll();
        for(Item item:items){
            itemDTOArrayList.add(new ItemDTO(item.getCode(),item.getDescription(),item.getUnitPrice(),item.getQtyOnHand()));
        }
        return itemDTOArrayList;
    }
    @Override
    public ItemDTO searchItem(String code) throws SQLException, ClassNotFoundException {
        Item item=itemDAO.search(code);
        return new ItemDTO(item.getCode(),item.getDescription(),item.getUnitPrice(),item.getQtyOnHand());
    }

    @Override
    public OrderDTO search(String newValue) throws SQLException, ClassNotFoundException {
            Order order=orderDAO.search(newValue);
            return new OrderDTO(order.getOrderId(),order.getOrderDate(),order.getCustomerId(),order.getCustomerName(),
                    order.getOrderTotal());
        //return orderDAO.search(newValue);
    }

    @Override
    public int setOrderValues(OrderDTO orderDTO) throws SQLException, ClassNotFoundException {

        return orderDAO.setOrderValues(new Order(orderDTO.getOrderId(),orderDTO.getOrderDate(),orderDTO.getCustomerId(),
                orderDTO.getCustomerName(),orderDTO.getOrderTotal()));
    }

    @Override
    public boolean saveOrder(String orderId, LocalDate orderDate, String customerId, List<OrderDetailDTO> orderDetails) {
        /*Transaction*/
        Connection connection = null;
        try {
            connection = DBConnection.getDbConnection().getConnection();
            PreparedStatement stm = connection.prepareStatement("SELECT oid FROM `Orders` WHERE oid=?");
            stm.setString(1, orderId);

            //OrderBOImpl orderBO=new OrderBOImpl();
            //boolean result = orderBO.
            boolean result= saveOrder(orderId);
            /*if order id already exist*/
//            if (stm.executeQuery().next()) {
//
//            }
            if (result) {
//                return false;
            }

            connection.setAutoCommit(false);

            /*stm = connection.prepareStatement("INSERT INTO `Orders` (oid, date, customerID) VALUES (?,?,?)");
            stm.setString(1, orderId);
            stm.setDate(2, Date.valueOf(orderDate));
            stm.setString(3, customerId);*/

            OrderDTO orderDTO=new OrderDTO();
            orderDTO.setOrderId(orderId);
            orderDTO.setOrderDate(orderDate);
            orderDTO.setCustomerId(customerId);

            if (setOrderValues(orderDTO) != 1) {
                connection.rollback();
                connection.setAutoCommit(true);
                return false;
            }

//            stm = connection.prepareStatement("INSERT INTO OrderDetails (oid, itemCode, unitPrice, qty) VALUES (?,?,?,?)");
            List<OrderDetail> orderDetailList=new ArrayList<>();

            for (OrderDetailDTO dto : orderDetails) {
                orderDetailList.add(new OrderDetail(dto.getItemCode(), dto.getQty(), dto.getUnitPrice()));
            }
            for (OrderDetail detail : orderDetailList) {
                //OrderDetailDAOImpl orderDetailDAO = new OrderDetailDAOImpl();

                // Pass each individual detail to setOrderDetailsValues
                if (orderDetailDAO.setOrderDetailValue(detail, orderId) != 1) {
                    connection.rollback();
                    connection.setAutoCommit(true);
                    return false;
                }

//                //Search & Update Item
                ItemDTO item = findItem(detail.getItemCode());
                if(item==null){
                    throw new RuntimeException("Item not found for code: " + detail.getItemCode());
                }

                // Update the quantity in the object
                item.setQtyOnHand(item.getQtyOnHand() - detail.getQty());

                // Update the item in the database
                ItemBOImpl itemBO = new ItemBOImpl();
                if (!(itemBO.setItemValues(item) > 0)) {
                    connection.rollback();
                    connection.setAutoCommit(true);
                    return false;
                }



            }

            connection.commit();
            connection.setAutoCommit(true);
            return true; // Successfully updated
        }

        catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
            try {
                connection.rollback();
                connection.setAutoCommit(true);
            } catch (SQLException rollbackException) {
                rollbackException.printStackTrace();
            }
            return false;
        }
    }

    @Override
    public ItemDTO findItem(String code) {
        try {
            PlaceOrderBOImpl placeOrderBOImpl = new PlaceOrderBOImpl();
            return placeOrderBOImpl.searchItem(code);
        } catch (SQLException e) {
            throw new RuntimeException("Failed to find the Item " + code, e);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return null;
    }


}
