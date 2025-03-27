package Com.app.dao;

import Com.app.metier.Produit;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ProduitDAO {

    private static final Logger LOGGER = Logger.getLogger(ProduitDAO.class.getName());

    public List<Produit> getAllProduits() {
        List<Produit> produits = new ArrayList<>();
        String query = "SELECT * FROM produits";

        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement stmt = connection.prepareStatement(query))
             {
             try (ResultSet rs = stmt.executeQuery()) {
                 while (rs.next()) {
                     produits.add(mapResultSetToProduit(rs));
                 }
             }

        }

        catch (SQLException e) {
        e.printStackTrace();
    }


        if (produits.isEmpty()) {
            System.out.println("Aucun produit trouvé dans la base de données.");

        }

        return produits;
    }

    private Produit mapResultSetToProduit(ResultSet rs) throws SQLException {
        Produit produit = new Produit();
        produit.setId(rs.getInt("id"));
        produit.setNom(rs.getString("nom"));
        produit.setDescription(rs.getString("description"));
        produit.setPrix(rs.getBigDecimal("prix"));
        produit.setImage(rs.getString("image"));
        return produit;
    }
    public boolean addProduit(Produit produit) {
        String query = "INSERT INTO produits (nom, description, prix, image) VALUES (?, ?, ?, ?)";

        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement stmt = connection.prepareStatement(query)) {

            // Remplissage des paramètres de la requête
            stmt.setString(1, produit.getNom());
            stmt.setString(2, produit.getDescription());
            stmt.setBigDecimal(3, produit.getPrix());
            stmt.setString(4, produit.getImage());

            // Exécuter la requête d'insertion
            int rowsInserted = stmt.executeUpdate();
            return rowsInserted > 0; // Retourne true si au moins une ligne a été insérée

        } catch (SQLException e) {
            e.printStackTrace();
            return false; // Retourne false en cas d'erreur
        }
    }
    public boolean deleteProduit(int id) {
        String query = "DELETE FROM produits WHERE id = ?";

        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement stmt = connection.prepareStatement(query)) {

            stmt.setInt(1, id);

            int rowsAffected = stmt.executeUpdate();
            return rowsAffected > 0; // Si des lignes ont été supprimées, le produit a été supprimé avec succès

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return false; // En cas d'échec
    }
    public boolean update(Produit produit) {
        String sql = "UPDATE produits SET nom = ?, description = ?, prix = ?, image = ? WHERE id = ?";

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, produit.getNom());
            stmt.setString(2, produit.getDescription());
            stmt.setBigDecimal(3, produit.getPrix());
            stmt.setString(4, produit.getImage());
            stmt.setInt(5, produit.getId());

            int rowsUpdated = stmt.executeUpdate();
            return rowsUpdated > 0; // Return true if the update was successful
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }
    public Produit getProduitById(int id) {
        Produit produit = null;
        String query = "SELECT * FROM produits WHERE id = ?";  // SQL query to select the product by ID

        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement stmt = connection.prepareStatement(query)) {

            stmt.setInt(1, id);  // Set the product ID parameter
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    // If the product is found, map the result set to a Produit object
                    produit = mapResultSetToProduit(rs);
                }
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return produit;  // Return the product (or null if not found)
    }




}
