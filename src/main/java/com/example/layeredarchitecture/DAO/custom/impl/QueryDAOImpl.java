package com.example.layeredarchitecture.DAO.custom.impl;

import com.example.layeredarchitecture.DAO.SQLUtil;
import com.example.layeredarchitecture.DAO.custom.QueryDAO;

import java.sql.SQLException;

public class QueryDAOImpl implements QueryDAO {
    @Override
    public void findOrdersByOrderDetailID() throws SQLException, ClassNotFoundException {
        SQLUtil.execute("");
    }
}
