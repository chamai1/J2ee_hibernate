package util;

import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class HibernateUtil {

    private static SessionFactory sessionFactory;

    static {
        try {
            // ✔ هنا Hibernate كيقرأ hibernate.cfg.xml
            // وكيعرف الكلاسات (annotations أو XML)
            sessionFactory = new Configuration().configure().buildSessionFactory();
        } catch (Exception e) {
            System.err.println("Erreur SessionFactory: " + e);
            throw new ExceptionInInitializerError(e);
        }
    }

    // ✔ هادي اللي كنستعملو فـ DAO
    public static SessionFactory getSessionFactory() {
        return sessionFactory;
    }
}