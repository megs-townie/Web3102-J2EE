<%@ page import="com.oms.webda2.model.User" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<html>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link rel="stylesheet" href="css/universal.css">
    <link rel="stylesheet" href="css/orderconfirmation.css">
    <link rel="icon" type="image/x-icon" href="img/favicon.png">
    <title>Order Confirmation</title>
</head>
<body>
<%
    User user = null;
    if (request.getSession(false) != null) {
        user = (User) session.getAttribute("user");
    }


    String productId = request.getParameter("productId");
%>
    <div class="container">
        <h1>Confirm your order</h1>
        <div id="delivery-info">
            <h2>Deliver to:</h2>
            <p id="name"> <%= user.getFirstName() + " " + user.getLastName() %></p>
            <p> <%= user.getAddress() + ", " +  user.getCity() + ", " + user.getProvince() %></p>
            <p> <%= user.getPostalCode() %></p>
        </div>

        <div id="btns">
            <form action="orderpage" method="post">
                <input type="hidden" name="productId" value="<%= productId %>">
                <input type="submit" value="Confirm Order">
            </form>
            <form action="products.jsp" method="get">
                <input type="submit" value="Cancel">
            </form>
        </div>

    </div>
</body>
</html>
