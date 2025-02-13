package com.example.layeredarchitecture.DAO.custom;

import com.example.layeredarchitecture.DAO.CrudDAO;
import com.example.layeredarchitecture.entity.Customer;

public interface CustomerDAO extends CrudDAO<Customer> {
    void uniqueMethodForCustomer();
}
