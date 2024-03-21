<%@ page import="java.util.List" %>
<%@ page import="com.oms.webda2.model.ProductReview" %>
<%@ page import="com.oms.webda2.controller.ProductReviewController" %>
<%@ page import="java.util.ArrayList" %>
<%@ page import="java.sql.SQLException" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<%
    int productId = Integer.parseInt(request.getParameter("productId"));
    List<ProductReview> reviews = new ArrayList<>();
    try {
        ProductReviewController prc = new ProductReviewController();
        reviews = prc.select(productId);
    } catch (SQLException e) {
        e.printStackTrace();
    }

    // Block of code to calculate product rating
    double avgRating = 0;
    int totalRatings = 0;

    for (ProductReview review : reviews) {
        totalRatings += review.getRating();
    }
    if (!reviews.isEmpty()) {
        avgRating = (double) totalRatings / reviews.size();
    }

%>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link rel="stylesheet" href="css/universal.css">
    <link rel="stylesheet" href="css/review.css">
    <link rel="icon" type="image/x-icon" href="img/favicon.png">
    <title>Product Reviews</title>
</head>
<body>

<div class="container">
    <h1>Product Reviews</h1>

    <% if (!reviews.isEmpty()) { %>
        <p id="avg-rating">Average rating: <%= String.format("%.2f", avgRating) %>/5</p>
    <% } %>

    <a href="products.jsp">Return to shopping</a>

    <% if (reviews.isEmpty()) { %>
        <p id="no-review">No reviews available for this product.</p>
    <% } else { %>

        <% for (ProductReview review : reviews) { %>
            <div class="review">
                <p id="rating">Rating: <%= review.getRating() %>/5</p>
                <p id="review-info"> <%= review.getReviewInfo() %></p>
            </div>
        <% } %>
    <% } %>
</div>

</body>
</html>
