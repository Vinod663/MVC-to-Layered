package com.example.layeredarchitecture.DAO.custom;

import com.example.layeredarchitecture.DAO.SuperDAO;

import java.sql.SQLException;

public interface QueryDAO extends SuperDAO {
    void findOrdersByOrderDetailID() throws SQLException, ClassNotFoundException;
}
