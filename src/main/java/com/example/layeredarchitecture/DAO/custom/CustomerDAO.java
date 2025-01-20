package com.example.layeredarchitecture.DAO.custom;

import com.example.layeredarchitecture.model.CustomerDTO;

import java.sql.SQLException;
import java.util.ArrayList;

public interface CustomerDAO {
     ArrayList<CustomerDTO> getAllCustomers() throws SQLException, ClassNotFoundException;
     void saveCustomer(String id, String name, String address) throws SQLException, ClassNotFoundException;
     void saveCustomer(CustomerDTO customerDTO) throws SQLException, ClassNotFoundException;
     void updateCustomer(CustomerDTO customerDTO) throws SQLException, ClassNotFoundException;
     void deleteCustomer(String id) throws SQLException, ClassNotFoundException;
     boolean existCustomer(String id) throws SQLException, ClassNotFoundException;
     String generateNewId() throws SQLException, ClassNotFoundException;
     CustomerDTO searchCustomer(String newValue) throws SQLException, ClassNotFoundException;
}
