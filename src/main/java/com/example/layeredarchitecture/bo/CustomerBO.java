package com.example.layeredarchitecture.bo;

import com.example.layeredarchitecture.dto.CustomerDTO;

import java.sql.SQLException;
import java.util.ArrayList;

public interface CustomerBO extends SuperBO {
    ArrayList<CustomerDTO> getAll() throws SQLException, ClassNotFoundException;
    void save(String id, String name, String address) throws SQLException, ClassNotFoundException;
    void save(CustomerDTO customerDTO) throws SQLException, ClassNotFoundException;
    void update(CustomerDTO customerDTO) throws SQLException, ClassNotFoundException;
    void delete(String id) throws SQLException, ClassNotFoundException;
    boolean exist(String id) throws SQLException, ClassNotFoundException;
    String generateNewId() throws SQLException, ClassNotFoundException;
    CustomerDTO search(String newValue) throws SQLException, ClassNotFoundException;
}
