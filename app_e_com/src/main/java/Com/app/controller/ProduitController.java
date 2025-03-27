package Com.app.controller;

import Com.app.metier.ProductService;
import Com.app.metier.Produit;
import java.math.BigDecimal;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet("/produits")
public class ProduitController extends HttpServlet {

    private ProductService productService;

    public ProduitController() throws ServletException {
        this.productService = new ProductService();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("action");

        if ("delete".equals(action)) {
            String idParam = request.getParameter("id");

            if (idParam != null) {
                try {
                    int id = Integer.parseInt(idParam);
                    boolean isDeleted = productService.deleteProduit(id);

                    if (isDeleted) {
                        // Redirect after successful deletion to refresh the product list
                        response.sendRedirect("produits");  // This will refresh the products list
                    } else {
                        request.setAttribute("error", "Erreur lors de la suppression du produit.");
                        request.getRequestDispatcher("produit.jsp").forward(request, response);
                    }
                } catch (NumberFormatException e) {
                    response.sendRedirect("produits"); // If the ID format is invalid, redirect back to product list
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
                        // Set product data as request attributes to populate the form
                        request.setAttribute("produit", produit);
                        request.getRequestDispatcher("edit.jsp").forward(request, response);
                    } else {
                        response.sendRedirect("produits"); // Redirect if product is not found
                    }
                } catch (NumberFormatException e) {
                    response.sendRedirect("produits"); // Redirect in case of invalid ID format
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
        }
        else {
            // Default behavior: Display the product list
            List<Produit> produits = productService.getAllProduits();
            request.setAttribute("produits", produits);
            request.getRequestDispatcher("produit.jsp").forward(request, response);
        }
    }
        @Override
        protected void doPost (HttpServletRequest request, HttpServletResponse response) throws
        ServletException, IOException {
            String action = request.getParameter("action");

            // Handle the "edit" action to update product data
            if ("edit".equals(action)) {
                try {
                    // Retrieve the updated product details from the form
                    int id = Integer.parseInt(request.getParameter("id"));
                    String nom = request.getParameter("nom");
                    String description = request.getParameter("description");
                    String prixStr = request.getParameter("prix");
                    String image = request.getParameter("image");

                    BigDecimal prix = new BigDecimal(prixStr);

                    // Create a new Product object with the updated data
                    Produit produit = new Produit();
                    produit.setId(id);
                    produit.setNom(nom);
                    produit.setDescription(description);
                    produit.setPrix(prix);
                    produit.setImage(image);

                    // Update the product in the database
                    boolean success = productService.updateProduit(produit);

                    if (success) {
                        response.sendRedirect("produits"); // Redirect to the product list after successful update
                    } else {
                        request.setAttribute("error", "Erreur lors de la mise à jour du produit.");
                        request.getRequestDispatcher("edit.jsp").forward(request, response); // Forward to the edit page if update fails
                    }
                } catch (NumberFormatException e) {
                    request.setAttribute("error", "Prix invalide.");
                    request.getRequestDispatcher("edit.jsp").forward(request, response); // Forward back to the edit page if there's a price format error
                }
            }

            // Handle the "add" action to add a new product
            else if ("add".equals(action)) {
                try {
                    // Retrieve new product data from the form
                    String nom = request.getParameter("nom");
                    String description = request.getParameter("description");
                    String prixStr = request.getParameter("prix");
                    String image = request.getParameter("image");

                    BigDecimal prix = new BigDecimal(prixStr);

                    // Create a new Product object with the provided data
                    Produit produit = new Produit();
                    produit.setNom(nom);
                    produit.setDescription(description);
                    produit.setPrix(prix);
                    produit.setImage(image);

                    // Add the product to the database
                    boolean success = productService.addProduit(produit);

                    if (success) {
                        response.sendRedirect("produits"); // Redirect to the product list after successful addition
                    } else {
                        request.setAttribute("error", "Erreur lors de l'ajout du produit.");
                        request.getRequestDispatcher("ajout.jsp").forward(request, response); // Forward to the add page if adding fails
                    }
                } catch (NumberFormatException e) {
                    request.setAttribute("error", "Prix invalide.");
                    request.getRequestDispatcher("ajout.jsp").forward(request, response); // Forward back if there's a price format error
                }
            }
        }
    }
