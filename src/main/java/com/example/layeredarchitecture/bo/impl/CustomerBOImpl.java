package com.example.layeredarchitecture.bo.impl;

import com.example.layeredarchitecture.DAO.DAOFactory;
import com.example.layeredarchitecture.DAO.custom.CustomerDAO;
import com.example.layeredarchitecture.DAO.custom.impl.CustomerDAOImpl;
import com.example.layeredarchitecture.bo.CustomerBO;
import com.example.layeredarchitecture.dto.CustomerDTO;
import com.example.layeredarchitecture.entity.Customer;

import java.sql.SQLException;
import java.util.ArrayList;

public class CustomerBOImpl implements CustomerBO {

    CustomerDAO customerDAO=(CustomerDAO) DAOFactory.getInstance()
            .getDAO(DAOFactory.DAOType.CUSTOMER);

    @Override
    public ArrayList<CustomerDTO> getAll() throws SQLException, ClassNotFoundException {


        /*Businesses Logic*/
        ArrayList<CustomerDTO> customerDTOs = new ArrayList<>();
        ArrayList<Customer> customers = customerDAO.getAll();
        for (Customer customer : customers) {
            customerDTOs.add(new CustomerDTO(customer.getId(), customer.getName(), customer.getAddress()));
        }
        return customerDTOs;
    }

    @Override
    public void save(String id, String name, String address) throws SQLException, ClassNotFoundException {

        customerDAO.save(id, name, address);
    }

    /*Using CustomerDto- save customer*/
    @Override
    public void save(CustomerDTO customerDTO) throws SQLException, ClassNotFoundException {

        customerDAO.save(new Customer(customerDTO.getId(),customerDTO.getName(),customerDTO.getAddress()));
    }

    @Override
    public void update(CustomerDTO customerDTO) throws SQLException, ClassNotFoundException {

        customerDAO.update(new Customer(customerDTO.getId(),customerDTO.getName(),customerDTO.getAddress()));
    }

    @Override
    public void delete(String id) throws SQLException, ClassNotFoundException {

       customerDAO.delete(id);

    }

    @Override
    public boolean exist(String id) throws SQLException, ClassNotFoundException {

        return customerDAO.exist(id);


    }

    @Override
    public String generateNewId() throws SQLException, ClassNotFoundException {
//
        CustomerDAO customerDAO=new CustomerDAOImpl();
        return customerDAO.generateNewId();
    }

    @Override
    public CustomerDTO search(String newValue) throws SQLException, ClassNotFoundException {

        Customer customer = customerDAO.search(newValue);
        return new CustomerDTO(customer.getId(), customer.getName(), customer.getAddress());
    }
}
