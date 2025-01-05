package com.example.layeredarchitecture.DAO;

import com.example.layeredarchitecture.db.DBConnection;
import com.example.layeredarchitecture.model.CustomerDTO;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class CustomerDAOImpl {
    public ArrayList<CustomerDTO> getAllCustomers() throws SQLException, ClassNotFoundException {
        //ArrayList<CustomerDTO> customers = new ArrayList<>();
        Connection connection = DBConnection.getDbConnection().getConnection();
        Statement statement = connection.createStatement();
        ResultSet resultSet = statement.executeQuery("select * from Customer");

        ArrayList<CustomerDTO> customerDTOArrayList = new ArrayList<>();
        while (resultSet.next()) {
            CustomerDTO customerDTO = new CustomerDTO();
            customerDTO.setId(resultSet.getString("id"));
            customerDTO.setName(resultSet.getString("name"));
            customerDTO.setAddress(resultSet.getString("address"));

            customerDTOArrayList.add(customerDTO);

        }
        return customerDTOArrayList;
    }
}
