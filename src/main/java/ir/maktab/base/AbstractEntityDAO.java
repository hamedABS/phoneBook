package ir.maktab.base;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;


/**
 * Created by Hamed-Abbaszadeh on 2/19/2018.
 */
public abstract class AbstractEntityDAO<E> implements EntityDAO{
    private SessionFactory factory ;

    public AbstractEntityDAO(){
        factory = new Configuration().configure().buildSessionFactory();
    }

    public boolean add(Object o) {
        Session session = getSession();
        boolean added = false;
        Transaction tx = null;
        try {
            tx = getTx(session);
            session.save(o) ;
            tx.commit();
            added = true;
        }
        catch (HibernateException e){
            if(tx!=null) tx.rollback();
            e.printStackTrace();
        }
        finally {
            closeSession(session);
            return added;
        }
    }

    public Transaction getTx(Session session) {
        if(session!=null) return session.beginTransaction();
        else{
            session = factory.openSession();
            return session.beginTransaction();
        }
    }

    public Session getSession() {
        Session session = factory.openSession();
        return session;
    }

    public void closeSession(Session session) {
        session.close();
    }

}
