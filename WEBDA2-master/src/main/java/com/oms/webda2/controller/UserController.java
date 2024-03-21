package com.oms.webda2.controller;

import com.oms.webda2.DAO.UserDAO;
import com.oms.webda2.database.SQLConnection;
import com.oms.webda2.model.User;
import static com.oms.webda2.database.SQLConnection.*;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class UserController implements UserDAO {
    private static final String INSERT = "INSERT INTO users(first_name, last_name, address, city, province, postal_code, email, password) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
    private static final String GET_SESSION_INFO = "SELECT * FROM users WHERE email = ?";
    private static final String CHECK_EXISTING_EMAIL = "SELECT COUNT(*) FROM users WHERE email = ?";

    // Methods from UserDAO
    @Override
    public void insert(User user) throws SQLException {
        Connection connection = null;
        PreparedStatement stmt = null;

        try {
            connection = getConnection();
            stmt = connection.prepareStatement(INSERT);

            stmt.setString(1, user.getFirstName());
            stmt.setString(2, user.getLastName());
            stmt.setString(3, user.getAddress());
            stmt.setString(4, user.getCity());
            stmt.setString(5, user.getProvince());
            stmt.setString(6, user.getPostalCode());
            stmt.setString(7, user.getEmail());
            stmt.setString(8, user.getPassword());

            stmt.executeUpdate();
        } catch (SQLException e) {
            System.err.println("SQL syntax error: " + e.getMessage());
        } catch (Exception e) {
            System.err.println("Caught exception: " + e.getMessage());
        } finally {
            stmt.close();
            connection.close();
        }
    }

    @Override
    public User getUserSessionInfo(String email) throws SQLException {
        Connection connection = null;
        PreparedStatement stmt = null;
        ResultSet resultSet = null;
        User user = null;

        try {
            connection = SQLConnection.getConnection();
            stmt = connection.prepareStatement(GET_SESSION_INFO);
            stmt.setString(1, email);
            resultSet = stmt.executeQuery();

            // If a user is found, create a user object to fill in order confirmation
            if (resultSet.next()) {
                user = new User(
                        resultSet.getString("first_name"),
                        resultSet.getString("last_name"),
                        resultSet.getString("address"),
                        resultSet.getString("city"),
                        resultSet.getString("province"),
                        resultSet.getString("postal_code"),
                        resultSet.getString("email"),
                        resultSet.getString("password")
                );
            }
        } catch (SQLException e) {
            System.err.println("Error fetching user information: " + e.getMessage());
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        } finally {
            if (resultSet != null) resultSet.close();
            if (stmt != null) stmt.close();
            if (connection != null) connection.close();
        }
        return user;
    }

    @Override
    public List<User> select() throws SQLException {
        List<User> users = new ArrayList<>();
        return users;
    }

    @Override
    public boolean emailExists(String email) throws SQLException {
        Connection connection = null;
        PreparedStatement stmt = null;
        ResultSet resultSet = null;

        try {
            connection = getConnection();
            stmt = connection.prepareStatement(CHECK_EXISTING_EMAIL);
            stmt.setString(1, email);
            resultSet = stmt.executeQuery();
            resultSet.next();
            int emailCount = resultSet.getInt(1);
            return emailCount > 0;
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        } finally {
            if (resultSet != null) resultSet.close();
            if (stmt != null) stmt.close();
            if (connection != null) connection.close();
        }
    }
}
