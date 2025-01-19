package com.example.layeredarchitecture.DAO;

import com.example.layeredarchitecture.db.DBConnection;
import com.example.layeredarchitecture.model.CustomerDTO;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
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
