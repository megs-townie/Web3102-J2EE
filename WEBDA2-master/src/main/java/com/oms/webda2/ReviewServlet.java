package com.oms.webda2;

import com.oms.webda2.controller.ProductController;
import com.oms.webda2.controller.ProductReviewController;
import com.oms.webda2.model.ProductReview;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

public class ReviewServlet extends HttpServlet {

    @Override
    public void init() throws ServletException {
        super.init();
    }

    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        int productId = Integer.parseInt(request.getParameter("productId"));

        try {
            ProductReviewController prc = new ProductReviewController();
            List<ProductReview> reviews = prc.select(productId);

            request.setAttribute("reviews", reviews);
            request.getRequestDispatcher("/productreview.jsp").forward(request, response);
        } catch (SQLException | ServletException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        int productId = Integer.parseInt(request.getParameter("productId"));
        String reviewInfo = request.getParameter("reviewInfo");
        int rating = Integer.parseInt(request.getParameter("rating"));

        ProductReview pr = new ProductReview(productId, reviewInfo, rating);
        ProductReviewController prc = new ProductReviewController();

        try {
            prc.insert(pr);
            response.sendRedirect(request.getContextPath() + "/products.jsp");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
