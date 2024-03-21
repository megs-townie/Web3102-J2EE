package com.oms.webda2;

import com.oms.webda2.controller.ProductController;
import com.oms.webda2.model.User;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;
import java.sql.SQLException;

public class OrderServlet extends HttpServlet {
    @Override
    public void init() throws ServletException {
        super.init();
    }

    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession(false);
        User user = null;
        if (session != null) {
            user = (User) session.getAttribute("user");
        }

        request.setAttribute("user", user);
        request.getRequestDispatcher("orderpage.jsp").forward(request, response);
    }

    @Override
    public void doPost(HttpServletRequest request, HttpServletResponse response) {
        String productId = request.getParameter("productId");

        try {
            ProductController pc = new ProductController();
            pc.handleSale(Integer.parseInt(productId));
            response.sendRedirect(request.getContextPath() + "/products.jsp");
            System.out.println("Method run");
        } catch (SQLException | IOException e) {
            e.printStackTrace();
        }
    }
}
