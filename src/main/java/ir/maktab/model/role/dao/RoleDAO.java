package ir.maktab.model.role.dao;

import ir.maktab.base.AbstractEntityDAO;
import ir.maktab.model.role.Feature;
import ir.maktab.model.role.Role;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.ArrayList;
import java.util.List;

public class RoleDAO extends AbstractEntityDAO{

    public boolean delete(int id) {
        return false;
    }

    public Object getById(int id) {
        Session session = getSession();
        Role role = null;
        Transaction tx =null;
        try {
            tx = getTx(session);
            role = (Role) session.get(Role.class , id);
            tx.commit();
        }
        catch (HibernateException e){
            if(tx!=null) tx.rollback();
            e.printStackTrace();
        }
        finally {
            System.out.println(role.getFeatures().toString());
            closeSession(session);
            return role;
        }
    }
    public Feature getFeatureById(int id) {
        Session session = getSession();
        Feature feature = null;
        Transaction tx =null;
        try {
            tx = getTx(session);
            feature = (Feature) session.get(Feature.class , id);
            tx.commit();
        }
        catch (HibernateException e){
            if(tx!=null) tx.rollback();
            e.printStackTrace();
        }
        finally {
            closeSession(session);
            return feature;
        }
    }

    public List getAll() {
        Session session = getSession();
        List roles = new ArrayList();
        Transaction tx = null;
        try {
            tx =getTx(session);
            roles =session.createQuery("from Role r").list();
            tx.commit();
        }
        catch (HibernateException e){
            if(tx!=null) tx.rollback();
            e.printStackTrace();
        }
        finally {
            closeSession(session);
            return roles;
        }
    }

    public boolean update(Object o) {
        return false;
    }
}
