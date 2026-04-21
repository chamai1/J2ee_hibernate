package services;

import java.util.List;

import dao.ProduitDAO;
import dao.ProduitDAOImpl;
import model.Produit;

public class ProduitMetierImpl implements ProduitMetier {

    private ProduitDAO produitDAO = new ProduitDAOImpl();

    @Override
    public void addProduit(Produit p) {
        produitDAO.save(p);
    }

    @Override
    public void updateProduit(Produit p) {
        produitDAO.update(p);
    }

    @Override
    public void deleteProduit(int id) {
        produitDAO.delete(id);
    }

    @Override
    public Produit getProduit(int id) {
        return produitDAO.findById(id);
    }

    @Override
    public List<Produit> getAllProduits() {
        return produitDAO.findAll();
    }
}