package web;

import services.ProduitMetier;
import services.ProduitMetierImpl;
import model.Produit;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.List;

@WebServlet("/produits")
public class ProduitServlet extends HttpServlet {

    private ProduitMetier metier;

    @Override
    public void init() {
        metier = new ProduitMetierImpl();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String action = request.getParameter("action");

        if (action == null) {
            List<Produit> produits = metier.getAllProduits();
            request.setAttribute("listeProduits", produits);
            request.getRequestDispatcher("/produits.jsp").forward(request, response);

        } else if ("delete".equals(action)) {
            int id = Integer.parseInt(request.getParameter("id"));
            metier.deleteProduit(id);
            response.sendRedirect("produits");

        } else if ("edit".equals(action)) {
            int id = Integer.parseInt(request.getParameter("id"));
            Produit produit = metier.getProduit(id);

            List<Produit> produits = metier.getAllProduits();
            request.setAttribute("listeProduits", produits);
            request.setAttribute("produitToEdit", produit);

            request.getRequestDispatcher("/produits.jsp").forward(request, response);
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String action = request.getParameter("action");

        if ("add".equals(action)) {
            String nom = request.getParameter("nom");
            double prix = Double.parseDouble(request.getParameter("prix"));
            int quantite = Integer.parseInt(request.getParameter("quantite"));

            Produit p = new Produit(nom, prix, quantite);
            metier.addProduit(p);

        } else if ("update".equals(action)) {
            int id = Integer.parseInt(request.getParameter("id"));
            String nom = request.getParameter("nom");
            double prix = Double.parseDouble(request.getParameter("prix"));
            int quantite = Integer.parseInt(request.getParameter("quantite"));

            Produit p = new Produit();
            p.setId(id);
            p.setNom(nom);
            p.setPrix(prix);
            p.setQuantite(quantite);

            metier.updateProduit(p);
        }

        response.sendRedirect("produits");
    }
}