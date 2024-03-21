<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="en">
<head>
  <meta charset="UTF-8">
  <meta name="viewport" content="width=device-width, initial-scale=1.0">
  <link rel="stylesheet" href="css/form.css">
  <link rel="stylesheet" href="css/universal.css">
  <link rel="icon" type="image/x-icon" href="img/favicon.png">
  <title>Login</title>
</head>
<body>

<form id="form-container" action="${pageContext.request.contextPath}/login" method="post">
  <img id="profile-icon" src="img/profile.png">
  <div id="form-container-header">
    <p id="form-container-header-title">Login</p>
  </div>


  <% if (request.getAttribute("loginFailed") != null && (boolean) request.getAttribute("loginFailed")) { %>
  <p id="error-msg">Incorrect email or password: please try again</p>
  <% } %>

  <input type="text" id="email" name="email" placeholder="Email" required>
  <label for="password"></label>
  <input type="password" id="password" name="password" placeholder="Password" required>
  <button id="btn" type="submit">Login</button>

  <p id="reg-link">Need an account? Register <a href="register.jsp">here</a></p>
</form>
</body>
</html>
