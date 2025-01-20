package com.example.layeredarchitecture.DAO;

import com.example.layeredarchitecture.model.CustomerDTO;

import java.sql.SQLException;
import java.util.ArrayList;

public interface CrudDAO<T> {
    ArrayList<T> getAll() throws SQLException, ClassNotFoundException;
    void save(String id, String name, String address) throws SQLException, ClassNotFoundException;
    void save(T DTO) throws SQLException, ClassNotFoundException;
    void update(T DTO) throws SQLException, ClassNotFoundException;
    void delete(String id) throws SQLException, ClassNotFoundException;
    boolean exist(String id) throws SQLException, ClassNotFoundException;
    String generateNewId() throws SQLException, ClassNotFoundException;
    T search(String newValue) throws SQLException, ClassNotFoundException;

}
