package com.oms.webda2.DAO;

import com.oms.webda2.model.ProductReview;

import java.sql.SQLException;
import java.util.List;

public interface ProductReviewDAO {
    void insert(ProductReview productReview) throws SQLException;
    List<ProductReview> select(int productId) throws SQLException;
}
