<%@ page import="java.util.List" %>
<%@ page import="Com.app.metier.Produit" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Liste des Produits</title>
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
            </tr>
        </thead>
        <tbody>
            <%
                List<Produit> produits = (List<Produit>) request.getAttribute("produits");

                if (produits == null) {
                    out.println("L'attribut 'produits' est null.");
                } else {
                    out.println("Nombre de produits : " + produits.size());
                    if (produits.isEmpty()) {
                        out.println("La liste des produits est vide.");
                    }
                }

                if (produits != null && !produits.isEmpty()) {
                    for (Produit produit : produits) {
            %>
                <tr>
                    <td><%= produit.getNom() %></td>
                    <td><%= produit.getDescription() %></td>
                    <td><%= produit.getPrix() %></td>
                    <td><img src="<%= produit.getImage() %>" alt="Image du produit" width="100"/></td>
                </tr>
            <%
                    }
                } else {
            %>
                <tr><td colspan="4">Aucun produit trouvé.</td></tr>
            <%
                }
            %>
        </tbody>
    </table>
</body>
</html>