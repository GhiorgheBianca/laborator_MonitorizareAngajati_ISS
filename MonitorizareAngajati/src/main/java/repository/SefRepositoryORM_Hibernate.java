package repository;

import domain.Sef;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

public class SefRepositoryORM_Hibernate implements ISefRepository {

    private final SessionFactory sessionFactory;

    public SefRepositoryORM_Hibernate(HibernateUtil hibernateUtil) {
        this.sessionFactory = hibernateUtil.getSessionFactory();
    }

    @Override
    public Sef login(String username, String password) {
        Sef sef = null;
        try {
            try (Session session = sessionFactory.openSession()) {
                Transaction tx = null;

                try {
                    tx = session.beginTransaction();
                    sef = (Sef) session.createQuery("from Sef where nume_de_utilizator = :nume_de_utilizator and parola = :parola")
                            .setParameter("nume_de_utilizator", username)
                            .setParameter("parola", password)
                            .list().get(0);
                    tx.commit();
                } catch (RuntimeException ex) {
                    if (tx != null)
                        tx.rollback();
                }
            }
        } catch (Exception e) {
            System.err.println("Exception " + e);
            e.printStackTrace();
        } finally {
            //sessionFactory.close();
        }
        return sef;
    }

}
