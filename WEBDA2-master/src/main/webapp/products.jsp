<%@ page import="com.oms.webda2.controller.ProductController" %>
<%@ page import="java.util.List" %>
<%@ page import="com.oms.webda2.model.Product" %>
<%@ page import="java.sql.SQLException" %>
<%@ page import="com.oms.webda2.model.User" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link rel="stylesheet" href="css/product.css">
    <link rel="stylesheet" href="css/universal.css">
    <link rel="icon" type="image/x-icon" href="img/favicon.png">
    <title>Products</title>
</head>
<body>

    <div class="container">
        <div class="container-header">
            <div class="title-and-list">
                <h1>What would you like?</h1>
            </div>

            <div class="container-header-links">

                <% Boolean isLoggedIn = (session.getAttribute("user") != null); %>
                <% if (isLoggedIn) { %>
                    <p id="welcome">Welcome back, <%= ((User) session.getAttribute("user")).getFirstName()%></p>
                    <a style="margin-left: 0" href="${pageContext.request.contextPath}/logout">Logout</a>
                <% } else { %>
                <a href="register.jsp">Register</a>
                <a href="login.jsp">Login</a>
                <% } %>
            </div>
        </div>


        <%

            ProductController pc = new ProductController();
            List<Product> products;
            String categoryFilter = request.getParameter("category");
            try {
                if (categoryFilter != null && !categoryFilter.isEmpty()) {
                    products = pc.selectByCategory(categoryFilter);
                } else {
                    products = pc.select();
                }
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }


            if (products.isEmpty()) {
        %>
        <p>No products available at this time</p>
        <%
            } else {
                for (Product product : products) {
        %>
        <div class="product-card">

            <div class="product-card-body">
                <h2><%= product.getProductName()%></h2>
                <p><%= product.getProductCategory()%></p>
                <p><%= product.getProductInfo()%></p>
                <p><%= product.getQuantityInStock()%> in stock</p>
            </div>
            <div class="btns">
                <a class="action-btn" href="<%= isLoggedIn ? "orderpage.jsp?productId=" + product.getProductId() : "login.jsp" %>">Purchase</a>
                <a class="action-btn" href="productreview.jsp?productId=<%= product.getProductId() %>">Reviews</a>
                <a class="action-btn" href="writereview.jsp?productId=<%= product.getProductId() %>">Leave a Review</a>
            </div>

        </div>
        <%
                }
            }
        %>
    </div>
</body>
</html>

<script>
    function filterProduct() {
        const category = document.getElementById("category").value;

        if (category === "all") {
            window.location.href = "products.jsp";
        }
        window.location.href = "products.jsp?category=" + category;
    }
</script>
