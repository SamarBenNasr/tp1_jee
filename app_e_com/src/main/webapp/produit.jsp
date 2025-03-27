<%@ page import="java.util.List" %>
<%@ page import="Com.app.metier.Produit" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Liste des Produits</title>
    <link rel="stylesheet" type="text/css" href="css/styles.css">

</head>
<body>
    <h2>Liste des Produits</h2>

    <table border="1">
        <thead>
            <tr>
                <th>Nom</th>
                <th>Description</th>
                <th>Prix</th>
                <th>Image</th>
                <th>Actions</th>
            </tr>
        </thead>
        <tbody>
            <%
                List<Produit> produits = (List<Produit>) request.getAttribute("produits");
                if (produits == null || produits.isEmpty()) {
            %>
                <tr><td colspan="5">Aucun produit trouvé.</td></tr>
            <%
                } else {
                    for (Produit produit : produits) {
            %>
                <tr>
                    <td><%= produit.getNom() %></td>
                    <td><%= produit.getDescription() %></td>
                    <td><%= produit.getPrix() %></td>
                    <td><img src="<%= produit.getImage() %>" alt="Image du produit" width="100"/></td>
                    <td>
                        <a href="produits?action=delete&id=<%= produit.getId() %>">Supprimer</a> |
                        <a href="produits?action=edit&id=<%= produit.getId() %>">Modifier</a> |
                        <a href="produits?action=details&id=<%= produit.getId() %>">Détails</a> <!-- Link to view product details -->
                    </td>
                </tr>
            <%
                    }
                }
            %>
        </tbody>
    </table>

    <a href="ajout.jsp">Ajouter un nouveau produit</a><br>

</body>
</html>
