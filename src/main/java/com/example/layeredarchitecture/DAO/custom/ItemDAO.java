package com.example.layeredarchitecture.DAO.custom;

import com.example.layeredarchitecture.DAO.CrudDAO;
import com.example.layeredarchitecture.dto.ItemDTO;
import com.example.layeredarchitecture.entity.Item;

import java.sql.SQLException;

public interface ItemDAO extends CrudDAO<Item> {
    /*ArrayList<ItemDTO> getAllItem() throws SQLException, ClassNotFoundException;
    void saveItem(ItemDTO itemDTO) throws SQLException, ClassNotFoundException;
    void updateItem(ItemDTO itemDTO) throws SQLException, ClassNotFoundException;
    boolean existItem(String code) throws SQLException, ClassNotFoundException;
    String generateNewId() throws SQLException, ClassNotFoundException;
    void deleteItem(String code) throws SQLException, ClassNotFoundException;
    ItemDTO searchItem(String newItemCode) throws SQLException, ClassNotFoundException;

    */
    int setItemValues(ItemDTO itemDTO) throws SQLException, ClassNotFoundException;
}
