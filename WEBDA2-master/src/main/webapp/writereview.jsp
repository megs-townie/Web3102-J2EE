<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link rel="stylesheet" href="css/universal.css">
    <link rel="stylesheet" href="css/writereview.css">
    <link rel="icon" type="image/x-icon" href="img/favicon.png">
    <title>Reviews</title>
</head>
<body>
    <div class="container">
        <h1>Write a review</h1>
        <a href="products.jsp">Return to shopping</a>

        <form id="reviewForm" action="writereview" method="post">

            <select id="rating" name="rating" required>
                <option value="0" disabled selected hidden>Rate this product</option>
                <option value="1">1</option>
                <option value="2">2</option>
                <option value="3">3</option>
                <option value="4">4</option>
                <option value="5">5</option>
            </select>

            <input type="hidden" name="productId" value="<%= request.getParameter("productId") %>">
            <label for="reviewInfo"></label>
            <textarea id="reviewInfo" name="reviewInfo" placeholder="Write your review"></textarea>
            <span id="character-counter">0/255</span>
            <input id="submit-btn" type="submit" value="Submit Review">
        </form>

    </div>

    <script>
        document.getElementById("reviewForm").addEventListener("submit", function (event) {
            const characterCount = document.getElementById("reviewInfo").value.length;
            if (characterCount > 255) {
                alert("Review cannot exceed 255 characters");
                event.preventDefault();
            }
        })

        document.getElementById("reviewInfo").addEventListener("input", function () {
            const characterCount = this.value.length;
            const counter = document.getElementById("character-counter");
            counter.textContent = characterCount + "/255";

            if (characterCount > 255) {
                counter.style.color = "red";
            } else {
                counter.style.color = "";
            }
        });
    </script>

</body>
</html>
