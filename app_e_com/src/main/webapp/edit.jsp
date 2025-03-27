<%@ page import="Com.app.metier.Produit" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Modifier un Produit</title>
    <link rel="stylesheet" type="text/css" href="css/styles.css">
</head>
<body>
    <h2>Modifier un Produit</h2>

    <!-- Form to edit product details -->
    <form action="produits?action=edit" method="post">
        <input type="hidden" name="id" value="<%= ((Produit) request.getAttribute("produit")).getId() %>" /> <!-- Hidden field to send product ID -->

        <label for="nom">Nom:</label>
        <input type="text" id="nom" name="nom" value="<%= ((Produit) request.getAttribute("produit")).getNom() %>" required/><br/><br/>

        <label for="description">Description:</label>
        <input type="text" id="description" name="description" value="<%= ((Produit) request.getAttribute("produit")).getDescription() %>" required/><br/><br/>

        <label for="prix">Prix:</label>
        <input type="text" id="prix" name="prix" value="<%= ((Produit) request.getAttribute("produit")).getPrix() %>" required/><br/><br/>

        <label for="image">Image:</label>
        <input type="text" id="image" name="image" value="<%= ((Produit) request.getAttribute("produit")).getImage() %>" required/><br/><br/>

        <input type="submit" value="Mettre à jour le produit"/>
    </form>

</body>
</html>
