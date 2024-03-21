package com.oms.webda2.controller;

import com.oms.webda2.DAO.ProductReviewDAO;
import com.oms.webda2.database.SQLConnection;
import com.oms.webda2.model.ProductReview;
import static com.oms.webda2.database.SQLConnection.*;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ProductReviewController implements ProductReviewDAO {
    private static final String INSERT = "INSERT INTO product_reviews(product_id, review_info, rating) VALUES (?, ?, ?)";
    private static final String SELECT = "SELECT * FROM product_reviews WHERE product_id = ?";

    // Methods from ProductReviewDAO
    @Override
    public void insert(ProductReview productReview) throws SQLException {
        Connection connection = null;
        PreparedStatement stmt = null;

        try {
            connection = getConnection();
            stmt = connection.prepareStatement(INSERT);

            stmt.setInt(1, productReview.getProductId());
            stmt.setString(2, productReview.getReviewInfo());
            stmt.setInt(3, productReview.getRating());

            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        } finally {
            stmt.close();
            connection.close();
        }
    }

    @Override
    public List<ProductReview> select(int productId) throws SQLException {
        List<ProductReview> productReviews = new ArrayList<>();
        Connection connection = null;
        PreparedStatement stmt = null;
        ResultSet resultSet = null;

        try {
            connection = SQLConnection.getConnection();
            stmt = connection.prepareStatement(SELECT);
            stmt.setInt(1, productId);
            resultSet = stmt.executeQuery();

            while (resultSet.next()) {
                int reviewId = resultSet.getInt("review_id");
                String reviewInfo = resultSet.getString("review_info");
                int rating = resultSet.getInt("rating");

                ProductReview pr = new ProductReview(reviewId, reviewInfo, rating);
                productReviews.add(pr);
            }
        } catch (ClassNotFoundException e) {
            throw new SQLException("DB error: " + e);
        } finally {
            if (resultSet != null) resultSet.close();
            if (stmt != null) stmt.close();
            if (connection != null) connection.close();
        }
        return productReviews;
    }
}
