package Com.app.metier;

import Com.app.dao.ProduitDAO;
import java.util.List;

public class ProductService {
    private ProduitDAO produitDAO;

    public ProductService() {
        this.produitDAO = new ProduitDAO();
    }

    public List<Produit> getAllProduits() {
        return produitDAO.getAllProduits();
    }
    public boolean addProduit(Produit produit) {
        return produitDAO.addProduit(produit);
    }
    public boolean deleteProduit(int id) {
        return produitDAO.deleteProduit(id);
    }
    public boolean updateProduit(Produit produit) {
        return produitDAO.update(produit);
    }
    public Produit getProduitById(int id) {
        return produitDAO.getProduitById(id);
    }



}
