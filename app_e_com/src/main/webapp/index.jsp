<html>
<head>
    <title>Login</title>
    <link rel="stylesheet" type="text/css" href="css/stylelog.css">
</head>
<body>
    <%
        if (session.getAttribute("user") != null) {
            response.sendRedirect("produits");
            return;
        }
    %>

    <form action="login" method="POST">
        <label for="username">Username:</label>
        <input type="text" id="username" name="username" required>
        <br>
        <label for="password">Password:</label>
        <input type="password" id="password" name="password" required>
        <br>
        <input type="submit" value="Login">
    </form>

</body>
</html>
