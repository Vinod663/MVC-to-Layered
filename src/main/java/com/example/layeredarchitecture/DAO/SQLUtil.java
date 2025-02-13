package com.example.layeredarchitecture.DAO;

import com.example.layeredarchitecture.db.DBConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class SQLUtil {
    //return type
    //parameters
    public static <T> T execute(String sql, Object... args) throws SQLException, ClassNotFoundException {
        Connection connection = DBConnection.getDbConnection().getConnection();
        PreparedStatement preparedStatement = connection.prepareStatement(sql);

        for (int i = 0; i < args.length; i++) {
            preparedStatement.setObject(i + 1, args[i]);
        }

        // Handle SELECT and non-SELECT queries differently
        if (sql.trim().toUpperCase().startsWith("SELECT")) {
            return (T) preparedStatement.executeQuery(); // For SELECT queries
        } else {
            return (T) (Integer) preparedStatement.executeUpdate(); // For INSERT, UPDATE, DELETE queries
        }
    }
}
