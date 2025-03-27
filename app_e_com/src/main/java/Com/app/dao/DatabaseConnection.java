package Com.app.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseConnection {
    // URL de connexion à MySQL
    private static final String URL = "jdbc:mysql://localhost:3306/ecommerce_db?serverTimezone=UTC";
    private static final String USER = "root"; // Remplace par ton utilisateur MySQL
    private static final String PASSWORD = "laviebelle33-A"; // Mets ton mot de passe MySQL


    public static Connection getConnection() {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");

            return DriverManager.getConnection(URL, USER, PASSWORD);
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException("Erreur de connexion à la base de données !");
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }
}

