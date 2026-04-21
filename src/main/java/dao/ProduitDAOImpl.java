package dao;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.Transaction;

import model.Produit;
import util.HibernateUtil;

public class ProduitDAOImpl implements ProduitDAO {

    @Override
    public void save(Produit produit) {
        Transaction transaction = null;
        Session session = null;

        try {
            session = HibernateUtil.getSessionFactory().openSession();
            transaction = session.beginTransaction();
            session.save(produit);
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        } finally {
            if (session != null) {
                session.close();
            }
        }
    }

    @Override
    public void update(Produit produit) {
        Transaction transaction = null;
        Session session = null;

        try {
            session = HibernateUtil.getSessionFactory().openSession();
            transaction = session.beginTransaction();
            session.update(produit);
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        } finally {
            if (session != null) {
                session.close();
            }
        }
    }

    @Override
    public void delete(int id) {
        Transaction transaction = null;
        Session session = null;

        try {
            session = HibernateUtil.getSessionFactory().openSession();
            transaction = session.beginTransaction();

            Produit produit = session.get(Produit.class, id);
            if (produit != null) {
                session.delete(produit);
            }

            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        } finally {
            if (session != null) {
                session.close();
            }
        }
    }

    @Override
    public Produit findById(int id) {
        Session session = null;
        Produit produit = null;

        try {
            session = HibernateUtil.getSessionFactory().openSession();
            produit = session.get(Produit.class, id);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (session != null) {
                session.close();
            }
        }

        return produit;
    }

    @Override
    public List<Produit> findAll() {
        Session session = null;
        List<Produit> produits = null;

        try {
            session = HibernateUtil.getSessionFactory().openSession();
            produits = session.createQuery("from Produit", Produit.class).list();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (session != null) {
                session.close();
            }
        }

        return produits;
    }
}