package com.example.layeredarchitecture.bo.impl;

import com.example.layeredarchitecture.DAO.DAOFactory;
import com.example.layeredarchitecture.DAO.custom.ItemDAO;
import com.example.layeredarchitecture.bo.ItemBO;
import com.example.layeredarchitecture.dto.ItemDTO;
import com.example.layeredarchitecture.entity.Item;

import java.sql.SQLException;
import java.util.ArrayList;

public class ItemBOImpl implements ItemBO {
    ItemDAO itemDAO = (ItemDAO) DAOFactory.getInstance().getDAO(DAOFactory.DAOType.ITEM);

    @Override
    public ArrayList<ItemDTO> getAll() throws SQLException, ClassNotFoundException {
        ArrayList<ItemDTO> itemDTOArrayList = new ArrayList<>();
        ArrayList<Item>items=itemDAO.getAll();
        for(Item item:items){
            itemDTOArrayList.add(new ItemDTO(item.getCode(),item.getDescription(),item.getUnitPrice(),item.getQtyOnHand()));
        }
        return itemDTOArrayList;

    }

    @Override
    public void save(String id, String name, String address) throws SQLException, ClassNotFoundException {
        itemDAO.save(id, name, address);

    }

    @Override
    public void save(ItemDTO items) throws SQLException, ClassNotFoundException {
        itemDAO.save(new Item(items.getCode(),items.getDescription(),items.getUnitPrice(),items.getQtyOnHand()));

    }

    @Override
    public void update(ItemDTO items) throws SQLException, ClassNotFoundException {
        itemDAO.update(new Item(items.getCode(),items.getDescription(),items.getUnitPrice(),items.getQtyOnHand()));

    }

    @Override
    public boolean exist(String code) throws SQLException, ClassNotFoundException {
        return itemDAO.exist(code);

    }

    @Override
    public String generateNewId() throws SQLException, ClassNotFoundException {
        return itemDAO.generateNewId();

    }

    @Override
    public void delete(String code) throws SQLException, ClassNotFoundException {
        itemDAO.delete(code);

    }

    @Override
    public ItemDTO search(String newItemCode) throws SQLException, ClassNotFoundException {
        Item item=itemDAO.search(newItemCode);
        return new ItemDTO(item.getCode(),item.getDescription(),item.getUnitPrice(),item.getQtyOnHand());

    }

    @Override
    public int setItemValues(ItemDTO itemDTO) throws SQLException, ClassNotFoundException {
        return itemDAO.setItemValues(itemDTO);

    }
}
