package com.oms.webda2.controller;

import com.oms.webda2.DAO.ProductDAO;
import com.oms.webda2.database.SQLConnection;
import com.oms.webda2.model.Product;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ProductController implements ProductDAO {
    private static final String SELECT_ALL = "SELECT * FROM products";
    private static final String SELECT_CATEGORY = "SELECT DISTINCT product_category FROM products";
    private static final String SELECT_BY_CATEGORY = "SELECT * FROM products WHERE product_category = ?";
    private static final String SELECT_QTY = "SELECT quantity_in_stock FROM products WHERE product_id = ?";
    private static final String HANDLE_PRODUCT_SALE = "UPDATE products SET quantity_in_stock = ? WHERE product_id = ?";

    // Methods from ProductDAO
    @Override
    public List<Product> select() throws SQLException {
        Connection connection = null;
        PreparedStatement stmt = null;
        ResultSet resultSet = null;

        List<Product> products = new ArrayList<>();

        try {
            connection = SQLConnection.getConnection();
            stmt = connection.prepareStatement(SELECT_ALL);
            resultSet = stmt.executeQuery();

            // Add products to list while there is a 'next product'
            while (resultSet.next()) {
                products.add(new Product(
                        resultSet.getInt("product_id"),
                        resultSet.getString("product_name"),
                        resultSet.getString("product_category"),
                        resultSet.getString("product_info"),
                        resultSet.getInt("quantity_in_stock")
                ));
            }
        } catch (Exception e) {
            System.err.println("Exception in execution: " + e.getMessage());
        } finally {
            if (resultSet != null) resultSet.close();
            if (stmt != null) stmt.close();
            if (connection != null) connection.close();
        }
        return products;
    }

    @Override
    public List<String> getCategories() throws SQLException {
        Connection connection = null;
        PreparedStatement stmt = null;
        ResultSet resultSet = null;

        List<String> categories = new ArrayList<>();

        try {
            connection = SQLConnection.getConnection();
            stmt = connection.prepareStatement(SELECT_CATEGORY);
            resultSet = stmt.executeQuery();

            while (resultSet.next()) {
                String category = resultSet.getString("product_category");
                categories.add(category);
            }
        } catch (SQLException | ClassNotFoundException e) {
            System.err.println("Error: " + e.getMessage());
        } finally {
            if (resultSet != null) resultSet.close();
            if (stmt != null) stmt.close();
            if (connection != null) connection.close();
        }
        return categories;
    }

    @Override
    public List<Product> selectByCategory(String category) throws SQLException {
        Connection connection = null;
        PreparedStatement stmt = null;
        ResultSet resultSet = null;

        List<Product> products = new ArrayList<>();

        try {
            connection = SQLConnection.getConnection();
            stmt = connection.prepareStatement(SELECT_BY_CATEGORY);
            stmt.setString(1, category);
            resultSet = stmt.executeQuery();

            while (resultSet.next()) {
                products.add(new Product(
                        resultSet.getInt("product_id"),
                        resultSet.getString("product_name"),
                        resultSet.getString("product_category"),
                        resultSet.getString("product_info"),
                        resultSet.getInt("quantity_in_stock")

                ));
            }
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        } finally {
            if (resultSet != null) resultSet.close();
            if (stmt != null) stmt.close();
            if (connection != null) connection.close();
        }
        return products;
    }

    @Override
    public void handleSale(int productId) throws SQLException {
        Connection connection = null;
        PreparedStatement stmtSelect = null;
        PreparedStatement stmtUpdate = null;
        ResultSet resultSet = null;

        try {
            connection = SQLConnection.getConnection();
            stmtSelect = connection.prepareStatement(SELECT_QTY);
            stmtSelect.setInt(1, productId);
            resultSet = stmtSelect.executeQuery();

            if (resultSet.next()) {
                int currentQty = resultSet.getInt("quantity_in_stock");
                if (currentQty > 0) {
                    stmtUpdate = connection.prepareStatement(HANDLE_PRODUCT_SALE);
                    stmtUpdate.setInt(1, currentQty - 1);
                    stmtUpdate.setInt(2, productId);
                    stmtUpdate.executeUpdate();
                } else {
                    // If there isn't enough stock
                    throw new RuntimeException("No stock for product id #" + productId);
                }
            } else {
                // If there is no product id found
                throw new RuntimeException("Product with id #" + productId + " not found in the database");
            }
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        } finally {
            if (resultSet != null) resultSet.close();
            if (stmtSelect != null) stmtSelect.close();
            if (stmtUpdate != null) stmtUpdate.close();
            if (connection != null) connection.close();
        }
    }
}
