package services;

import java.util.List;
import model.Produit;

public interface ProduitMetier {
    void addProduit(Produit p);
    void updateProduit(Produit p);
    void deleteProduit(int id);
    Produit getProduit(int id);
    List<Produit> getAllProduits();
}