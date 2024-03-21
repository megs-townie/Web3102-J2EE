package com.oms.webda2;

import com.oms.webda2.controller.ProductController;
import com.oms.webda2.model.Product;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

public class ProductServlet extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response) {
        ProductController pc = new ProductController();

        try {
            List<Product> products = pc.select();
            request.setAttribute("products", products);
            request.getRequestDispatcher("products.jsp").forward(request, response);
        } catch (SQLException | ServletException | IOException e) {
            e.printStackTrace();
        }
    }
}
