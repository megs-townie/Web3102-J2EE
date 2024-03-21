<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@page import="com.oms.webda2.model.User" %>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link rel="stylesheet" href="css/form.css">
    <link rel="stylesheet" href="css/universal.css">
    <link rel="icon" type="image/x-icon" href="img/favicon.png">
    <title>Register</title>
</head>
<body>
    <%-- REGISTRATION FORM --%>
    <form id="form-container" action="${pageContext.request.contextPath}/registration" method="post">
        <img id="profile-icon" src="img/profile.png">
        <div id="form-container-header">
            <p id="form-container-header-title">Register</p>
        </div>

        <%-- These blocks will check first for duplicate email then if passwords match --%>
        <% if (request.getAttribute("duplicateEmail") != null && (boolean) request.getAttribute("duplicateEmail")) { %>
        <p id="error-msg">Email already exists</p>
        <% } %>

        <% if (request.getAttribute("passwordMismatch") != null && (boolean) request.getAttribute("passwordMismatch")) { %>
        <p id="error-msg">Passwords do not match</p>
        <% } %>

        <label for="firstName"></label>
        <input type="text" id="firstName" name="firstName" placeholder="First name" required>
        <label for="lastName"></label>
        <input type="text" id="lastName" name="lastName" placeholder="Last name" required>
        <label for="address"></label>
        <input type="text" id="address" name="address" placeholder="Address" required>
        <label for="city"></label>
        <input type="text" id="city" name="city" placeholder="City" required>
        <label for="province"></label>
        <input type="text" id="province" name="province" placeholder="Province" required>
        <label for="postalCode"></label>
        <input type="text" id="postalCode" name="postalCode" placeholder="Postal code" required>
        <label for="email"></label>
        <input type="text" id="email" name="email" placeholder="Email" required>
        <label for="password"></label>
        <input type="password" id="password" name="password" placeholder="Password" required>
        <label for="confirmPassword"></label>
        <input type="password" id="confirmPassword" name="confirmPassword" placeholder="Confirm Password" required>

        <button id="btn" type="submit">Register</button>
        <p id="reg-link">Already have an account? Login <a href="login.jsp">here</a></p>
    </form>
</body>
</html>
