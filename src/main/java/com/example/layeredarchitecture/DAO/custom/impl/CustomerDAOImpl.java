package com.example.layeredarchitecture.DAO.custom.impl;

import com.example.layeredarchitecture.DAO.SQLUtil;
import com.example.layeredarchitecture.DAO.custom.CustomerDAO;
import com.example.layeredarchitecture.db.DBConnection;
import com.example.layeredarchitecture.entity.Customer;

import java.sql.*;
import java.util.ArrayList;


public class CustomerDAOImpl implements CustomerDAO {

    @Override
    public ArrayList<Customer> getAll() throws SQLException, ClassNotFoundException {
        //ArrayList<CustomerDTO> customers = new ArrayList<>();
//        Connection connection = DBConnection.getDbConnection().getConnection();
//        Statement statement = connection.createStatement();
//        ResultSet resultSet = statement.executeQuery("select * from Customer");
        ResultSet resultSet = SQLUtil.execute("SELECT * FROM Customer");

        ArrayList<Customer> customerDTOArrayList = new ArrayList<>();
        while (resultSet.next()) {
            Customer customer = new Customer();
            customer.setId(resultSet.getString("id"));
            customer.setName(resultSet.getString("name"));
            customer.setAddress(resultSet.getString("address"));

            customerDTOArrayList.add(customer);

        }
        return customerDTOArrayList;
    }

    @Override
    public void save(String id, String name, String address) throws SQLException, ClassNotFoundException {
//        Connection connection = DBConnection.getDbConnection().getConnection();
//        PreparedStatement pstm = connection.prepareStatement("INSERT INTO Customer (id,name, address) VALUES (?,?,?)");
//        pstm.setString(1, id);
//        pstm.setString(2, name);
//        pstm.setString(3, address);
//        pstm.executeUpdate();

        SQLUtil.execute("INSERT INTO Customer (id,name, address) VALUES (?,?,?) ",
                id, name, address);
    }

    /*Using CustomerDto- save customer*/
    @Override
    public void save(Customer customer) throws SQLException, ClassNotFoundException {
        Connection connection = DBConnection.getDbConnection().getConnection();
        PreparedStatement pstm = connection.prepareStatement("INSERT INTO Customer (id,name, address) VALUES (?,?,?)");
        pstm.setString(1, customer.getId());
        pstm.setString(2, customer.getName());
        pstm.setString(3, customer.getAddress());
        pstm.executeUpdate();
    }

    @Override
    public void update(Customer customer) throws SQLException, ClassNotFoundException {
//        Connection connection = DBConnection.getDbConnection().getConnection();
//        PreparedStatement pstm = connection.prepareStatement("UPDATE Customer SET name=?, address=? WHERE id=?");
//        pstm.setString(1, customerDTO.getName());
//        pstm.setString(2, customerDTO.getAddress());
//        pstm.setString(3, customerDTO.getId());
//        pstm.executeUpdate();

        SQLUtil.execute("UPDATE Customer SET name=?, address=? WHERE id=?",
                customer.getName(),
                customer.getAddress(),
                customer.getId());
    }

    @Override
    public void delete(String id) throws SQLException, ClassNotFoundException {
//        Connection connection = DBConnection.getDbConnection().getConnection();
//        PreparedStatement pstm = connection.prepareStatement("c");
//        pstm.setString(1, id);
//        pstm.executeUpdate();

        SQLUtil.execute("DELETE FROM Customer WHERE id=?", id);

    }

    @Override
    public boolean exist(String id) throws SQLException, ClassNotFoundException {
//        Connection connection = DBConnection.getDbConnection().getConnection();
//        PreparedStatement pstm = connection.prepareStatement("SELECT id FROM Customer WHERE id=?");
//        pstm.setString(1, id);
//        return pstm.executeQuery().next();

        ResultSet resultSet = SQLUtil.execute("SELECT id FROM Customer WHERE id=?", id);
        return resultSet.next(); // Returns true if the ResultSet has a row, indicating the customer exists
    }

    @Override
    public String generateNewId() throws SQLException, ClassNotFoundException {
//        Connection connection = DBConnection.getDbConnection().getConnection();
//        ResultSet rst = connection.createStatement().executeQuery("SELECT id FROM Customer ORDER BY id DESC LIMIT 1");
        ResultSet rst = SQLUtil.execute("SELECT id FROM Customer ORDER BY id DESC LIMIT 1");
        if (rst.next()) {
            String id = rst.getString("id");
            int newCustomerId = Integer.parseInt(id.replace("C00-", "")) + 1;
            return String.format("C00-%03d", newCustomerId);
        } else {
            return "C00-001";
        }
    }

    @Override
    public Customer search(String newValue) throws SQLException, ClassNotFoundException {
        Connection connection = DBConnection.getDbConnection().getConnection();

//        if (!existCustomer(newValue + "")) {
////                            "There is no such customer associated with the id " + id
//            new Alert(Alert.AlertType.ERROR, "There is no such customer associated with the id " + newValue + "").show();
//        }

//        PreparedStatement pstm = connection.prepareStatement("SELECT * FROM Customer WHERE id=?");
//        pstm.setString(1, newValue + "");
//        ResultSet rst = pstm.executeQuery();

        ResultSet rst = SQLUtil.execute("SELECT * FROM Customer WHERE id=?",
                newValue+"");
        rst.next();
        Customer customer = new Customer(newValue + "", rst.getString("name"), rst.getString("address"));
        return customer;
    }

    @Override
    public void uniqueMethodForCustomer() {

    }
}
