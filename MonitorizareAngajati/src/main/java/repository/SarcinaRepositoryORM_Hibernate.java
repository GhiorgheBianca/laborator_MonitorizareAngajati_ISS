package repository;

import domain.Angajat;
import domain.Sarcina;
import domain.validators.Validator;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import java.util.List;

public class SarcinaRepositoryORM_Hibernate implements ISarcinaRepository {

    private final SessionFactory sessionFactory;

    public SarcinaRepositoryORM_Hibernate(HibernateUtil hibernateUtil) {
        this.sessionFactory = hibernateUtil.getSessionFactory();
    }

    @Override
    public List<Sarcina> filterByEmployee(Long employee_id) {
        try {
            try (Session session = sessionFactory.openSession()) {
                Transaction tx = null;

                try {
                    tx = session.beginTransaction();
                    List<Sarcina> sarcini = session.createQuery("from Sarcina where id_angajat = :id_angajat ", Sarcina.class)
                            .setParameter("id_angajat", employee_id)
                            .list();
                    tx.commit();
                    return sarcini;
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
    public Sarcina save(Sarcina entity) {
        return null;
    }

    @Override
    public Sarcina update(Sarcina entity) {
        return null;
    }

}
