<html lang="fr">
<head>
    <meta charset="UTF-8">
    <title>Connexion</title>
    <link rel="stylesheet" type="text/css" href="css/stylelog.css">
</head>
<body>
    <%
        // Check if the user is already logged in via session
        HttpSession sessionUser = request.getSession(false);
        if (sessionUser != null && sessionUser.getAttribute("username") != null) {
            response.sendRedirect("produit.jsp"); // Redirect if already logged in
            return;
        }
    %>
    <form action="login" method="post">
        <label>Nom d'utilisateur :</label>
        <input type="text" name="username" required><br><br>

        <label>Mot de passe :</label>
        <input type="password" name="password" required><br><br>

        <button type="submit">Se connecter</button>
    </form>
</body>
</html>
