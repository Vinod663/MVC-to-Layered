package com.example.layeredarchitecture.bo;

import com.example.layeredarchitecture.dto.ItemDTO;

import java.sql.SQLException;
import java.util.ArrayList;

public interface ItemBO extends SuperBO {
    ArrayList<ItemDTO> getAll() throws SQLException, ClassNotFoundException;
    void save(String id, String name, String address) throws SQLException, ClassNotFoundException;
    void save(ItemDTO itemDTO) throws SQLException, ClassNotFoundException;
    void update(ItemDTO itemDTO) throws SQLException, ClassNotFoundException;
    boolean exist(String code) throws SQLException, ClassNotFoundException;
    String generateNewId() throws SQLException, ClassNotFoundException;
    void delete(String code) throws SQLException, ClassNotFoundException;
    ItemDTO search(String newItemCode) throws SQLException, ClassNotFoundException;
    int setItemValues(ItemDTO itemDTO) throws SQLException, ClassNotFoundException;
}
