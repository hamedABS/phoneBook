package ir.maktab.model.logger.dao;

import ir.maktab.base.AbstractEntityDAO;
import ir.maktab.model.logger.MyLogger;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.List;

/**
 * Created by Hamed-Abbaszadeh on 3/14/2018.
 */
public class LoggerDAO extends AbstractEntityDAO {

    @Override
    public boolean delete(int id) {
        return false;
    }

    @Override
    public Object getById(int id) {
        return null;
    }

    @Override
    public List getAll() {
        Session session = getSession();
        Transaction tx =null;
        List<MyLogger> loggers=null ;
        try {
            tx = session.beginTransaction();
            loggers = session.createQuery("from MyLogger logger").list();
            tx.commit();
        }
        catch (Exception e){
            if(tx!=null)tx.rollback();
            e.printStackTrace();
        }
        finally {
            closeSession(session);
            return loggers;
        }
    }

    @Override
    public boolean update(Object o) {
        return false;
    }
}
