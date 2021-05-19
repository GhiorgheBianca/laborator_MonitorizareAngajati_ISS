package repository;

import domain.Angajat;
import domain.validators.Validator;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;

import java.util.List;

public class AngajatRepositoryORM_Hibernate implements IAngajatRepository {

    private final SessionFactory sessionFactory;

    public AngajatRepositoryORM_Hibernate(HibernateUtil hibernateUtil) {
        this.sessionFactory = hibernateUtil.getSessionFactory();
    }

    @Override
    public Angajat findOne(Long id) {
        Angajat angajat = null;
        try {
            try (Session session = sessionFactory.openSession()) {
                Transaction tx = null;

                try {
                    tx = session.beginTransaction();
                    angajat = (Angajat) session.createQuery("from Angajat where id = :id")
                            .setParameter("id", id)
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
        return angajat;
    }

    @Override
    public List<Angajat> filterByPresence() {
        try {
            try (Session session = sessionFactory.openSession()) {
                Transaction tx = null;

                try {
                    tx = session.beginTransaction();
                    List<Angajat> angajati = session.createQuery("from Angajat where ora_conectare is not null and ora_deconectare is null", Angajat.class).list();
                    tx.commit();
                    return angajati;
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
        return null;
    }

    @Override
    public Angajat login(String username, String password) {
        Angajat angajat = null;
        try {
            try (Session session = sessionFactory.openSession()) {
                Transaction tx = null;

                try {
                    tx = session.beginTransaction();
                    angajat = (Angajat) session.createQuery("from Angajat where nume_de_utilizator = :nume_de_utilizator and parola = :parola")
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
            sessionFactory.close();
        }
        return angajat;
    }

    @Override
    public Angajat save(Angajat entity) {
        return null;
    }

    @Override
    public Angajat update(Angajat entity) {
        return null;
    }

}
