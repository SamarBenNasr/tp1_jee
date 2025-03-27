<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Ajouter un Produit</title>
    <link rel="stylesheet" type="text/css" href="css/styles.css">
</head>
<body>
    <h2>Ajouter un Produit</h2>

    <form action="produits?action=add" method="post">
        <label for="nom">Nom :</label>
        <input type="text" id="nom" name="nom" required><br><br>

        <label for="description">Description :</label>
        <input type="text" id="description" name="description" required><br><br>

        <label for="prix">Prix :</label>
        <input type="number" id="prix" name="prix" step="0.01" required><br><br>

        <label for="image">Image (URL) :</label>
        <input type="text" id="image" name="image"><br><br>

        <button type="submit">Ajouter</button>
    </form>

    <br>
    <a href="produits">Retour à la liste des produits</a>
</body>
</html>
