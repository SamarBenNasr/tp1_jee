package Com.app.controller;

import Com.app.metier.ProductService;
import Com.app.metier.Produit;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;
import java.math.BigDecimal;
import java.util.List;

@WebServlet("/produits")
public class ProduitController extends HttpServlet {

    private ProductService productService;

    public ProduitController() throws ServletException {
        this.productService = new ProductService();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession(false);

        // Check if the user is logged in
        if (session == null || session.getAttribute("user") == null) {
            response.sendRedirect("login.jsp"); // Redirect to login if the user is not logged in
            return;
        }

        String action = request.getParameter("action");

        if ("delete".equals(action)) {
            String idParam = request.getParameter("id");

            if (idParam != null) {
                try {
                    int id = Integer.parseInt(idParam);
                    boolean isDeleted = productService.deleteProduit(id);

                    if (isDeleted) {
                        response.sendRedirect("produits");
                    } else {
                        request.setAttribute("error", "Erreur lors de la suppression du produit.");
                        request.getRequestDispatcher("produit.jsp").forward(request, response);
                    }
                } catch (NumberFormatException e) {
                    response.sendRedirect("produits");
                } 
            } else {
                // If no ID is provided, show the produit.jsp page with the list of products for deletion
                List<Produit> produits = productService.getAllProduits();
                request.setAttribute("produits", produits);
                request.getRequestDispatcher("produit.jsp").forward(request, response);
            }
        } else if ("edit".equals(action)) {
            String idParam = request.getParameter("id");

            if (idParam != null) {
                try {
                    int id = Integer.parseInt(idParam);
                    Produit produit = productService.getProduitById(id);

                    if (produit != null) {
                        request.setAttribute("produit", produit);
                        request.getRequestDispatcher("edit.jsp").forward(request, response);
                    } else {
                        response.sendRedirect("produits");
                    }
                } catch (NumberFormatException e) {
                    response.sendRedirect("produits");
                }
            }
        } else if ("details".equals(action)) {
            String idParam = request.getParameter("id");
            if (idParam != null) {
                try {
                    int id = Integer.parseInt(idParam);
                    Produit produit = productService.getProduitById(id);
                    if (produit != null) {
                        request.setAttribute("produit", produit);
                        request.getRequestDispatcher("details.jsp").forward(request, response);
                    } else {
                        response.sendRedirect("produits");
                    }
                } catch (NumberFormatException e) {
                    response.sendRedirect("produits");
                }
            }
        } else {
            List<Produit> produits = productService.getAllProduits();
            request.setAttribute("produits", produits);
            request.getRequestDispatcher("produit.jsp").forward(request, response);
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("action");

        if ("edit".equals(action)) {
            try {
                int id = Integer.parseInt(request.getParameter("id"));
                String nom = request.getParameter("nom");
                String description = request.getParameter("description");
                String prixStr = request.getParameter("prix");
                String image = request.getParameter("image");

                BigDecimal prix = new BigDecimal(prixStr);

                Produit produit = new Produit();
                produit.setId(id);
                produit.setNom(nom);
                produit.setDescription(description);
                produit.setPrix(prix);
                produit.setImage(image);

                boolean success = productService.updateProduit(produit);

                if (success) {
                    response.sendRedirect("produits");
                } else {
                    request.setAttribute("error", "Erreur lors de la mise à jour du produit.");
                    request.getRequestDispatcher("edit.jsp").forward(request, response);
                }
            } catch (NumberFormatException e) {
                request.setAttribute("error", "Prix invalide.");
                request.getRequestDispatcher("edit.jsp").forward(request, response);
            }
        } else if ("add".equals(action)) {
            try {
                String nom = request.getParameter("nom");
                String description = request.getParameter("description");
                String prixStr = request.getParameter("prix");
                String image = request.getParameter("image");

                BigDecimal prix = new BigDecimal(prixStr);

                Produit produit = new Produit();
                produit.setNom(nom);
                produit.setDescription(description);
                produit.setPrix(prix);
                produit.setImage(image);

                boolean success = productService.addProduit(produit);

                if (success) {
                    response.sendRedirect("produits");
                } else {
                    request.setAttribute("error", "Erreur lors de l'ajout du produit.");
                    request.getRequestDispatcher("ajout.jsp").forward(request, response);
                }
            } catch (NumberFormatException e) {
                request.setAttribute("error", "Prix invalide.");
                request.getRequestDispatcher("ajout.jsp").forward(request, response);
            }
        }
    }
}
