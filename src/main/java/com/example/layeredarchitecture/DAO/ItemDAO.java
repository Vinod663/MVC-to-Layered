package com.example.layeredarchitecture.DAO;

import com.example.layeredarchitecture.model.ItemDTO;

import java.sql.SQLException;
import java.util.ArrayList;

public interface ItemDAO {
    ArrayList<ItemDTO> getAllItem() throws SQLException, ClassNotFoundException;
    void saveItem(ItemDTO itemDTO) throws SQLException, ClassNotFoundException;
    void updateItem(ItemDTO itemDTO) throws SQLException, ClassNotFoundException;
    boolean existItem(String code) throws SQLException, ClassNotFoundException;
    String generateNewId() throws SQLException, ClassNotFoundException;
    void deleteItem(String code) throws SQLException, ClassNotFoundException;
    ItemDTO searchItem(String newItemCode) throws SQLException, ClassNotFoundException;
    int setItemValues(ItemDTO itemDTO) throws SQLException, ClassNotFoundException;
}