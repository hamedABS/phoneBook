package ir.maktab.base;

import ir.maktab.model.role.Role;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;


/**
 * Created by Hamed-Abbaszadeh on 2/19/2018.
 */
public abstract class AbstractEntityDAO<E> implements EntityDAO{
    private Session session ;
    private SessionFactory factory ;
    public AbstractEntityDAO(){
        factory = new Configuration().configure().buildSessionFactory();
    }

    public boolean add(Object o) {
        Session session = getSession();
        boolean added = false;
        Transaction tx = null;
        try {
            tx = getTx();
            session.save(o) ;
            tx.commit();
            added = true;
        }
        catch (HibernateException e){
            if(tx!=null) tx.rollback();
            e.printStackTrace();
        }
        finally {
            closeSession();
            return added;
        }
    }

    public Transaction getTx() {
        if(session!=null) return session.beginTransaction();
        else{
            session = factory.openSession();
            return session.beginTransaction();
        }
    }

    public Session getSession() {
        session = factory.openSession();
        return session;
    }

    public void closeSession() {
        session.close();
    }
}
