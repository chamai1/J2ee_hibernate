package dao;

import java.util.List;
import model.Produit;

public interface ProduitDAO {

    void save(Produit produit);

    void update(Produit produit);

    void delete(int id);

    Produit findById(int id);

    List<Produit> findAll();
}