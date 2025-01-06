package com.example.layeredarchitecture.DAO;

import com.example.layeredarchitecture.db.DBConnection;
import com.example.layeredarchitecture.model.CustomerDTO;

import java.sql.*;
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

    public void saveCustomer(String id, String name, String address) throws SQLException, ClassNotFoundException {
        Connection connection = DBConnection.getDbConnection().getConnection();
        PreparedStatement pstm = connection.prepareStatement("INSERT INTO Customer (id,name, address) VALUES (?,?,?)");
        pstm.setString(1, id);
        pstm.setString(2, name);
        pstm.setString(3, address);
        pstm.executeUpdate();
    }
}
