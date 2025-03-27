<%@ page import="Com.app.metier.Produit" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Détails du Produit</title>
    <link rel="stylesheet" type="text/css" href="css/styles.css">
</head>
<body>
    <h2>Détails du Produit</h2>

    <%
        Produit produit = (Produit) request.getAttribute("produit");
        if (produit != null) {
    %>

    <p><strong>Nom:</strong> <%= produit.getNom() %></p>
    <p><strong>Description:</strong> <%= produit.getDescription() %></p>
    <p><strong>Prix:</strong> <%= produit.getPrix() %></p>
    <p><strong>Image:</strong> <img src="<%= produit.getImage() %>" alt="Image du produit" width="200"/></p>

    <br>
    <!-- Link to go back to the product list -->
    <a href="produits">Retour à la liste des produits</a>

    <% } else { %>
        <p>Produit introuvable.</p>
    <% } %>
</body>
</html>
