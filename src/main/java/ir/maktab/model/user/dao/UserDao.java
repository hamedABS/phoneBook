package ir.maktab.model.user.dao;

import ir.maktab.base.AbstractEntityDAO;
import ir.maktab.model.user.User;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;

import javax.jws.soap.SOAPBinding;
import javax.xml.bind.DatatypeConverter;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Hamed-Abbaszadeh -> 09385136659 on 2/19/2018.
 */
public class UserDao extends AbstractEntityDAO {

    public boolean add(Object o) {
        o = (User)o;
        Session session = getSession();
        boolean added = false;
        Transaction tx =null;
        try {
            tx = getTx();
            session.save((User)o);
            tx.commit();
            added = true;
        }
        catch (Exception e){
            if (tx!=null)tx.rollback();
            e.printStackTrace();
        }
        finally {
            closeSession();
            return added;
        }
    }
    public boolean delete(int id) {
        Session session = getSession();
        Transaction tx = null;
        boolean deleted = false;
        try {
            tx = getTx();
            User user = (User) session.get(User.class , id);
            session.delete(user);
            tx.commit();
            deleted = true;
        }
        catch (HibernateException e){
            if(tx!=null) tx.rollback();
            e.printStackTrace();
        }
        return deleted;
    }

    public Object getById(int id) {
        Session session = getSession();
        Transaction tx = null;
        User user = null;
        try {
            tx = getTx();
            user = (User) session.get(User.class , id);
            session.get(User.class , id);
            tx.commit();
        }
        catch (HibernateException e){
            if(tx!=null) tx.rollback();
            e.printStackTrace();
        }
        return user;
    }

    public List getAll() {
        Session session = getSession();
        List list =null;
        Transaction tx = null;
        try {
            tx =getTx();
            list=session.createQuery("from User user").list();
            tx.commit();
        }
        catch (HibernateException e){
            if(tx!=null)tx.rollback();
            e.printStackTrace();
        }
        return list;
    }

    public boolean update(Object o) {
        Session session = getSession();
        Transaction tx = null;
        boolean updated = false;
        try {
            tx = getTx();
            User user = (User) session.get(User.class,((User)o).getId());
            if (user.getFirstName()!=null) user.setFirstName(((User) o).getFirstName());
            if (user.getLastName()!=null) user.setLastName(((User) o).getLastName());
            if (user.getUsername()!=null) user.setUsername(((User) o).getUsername());
            if (user.getPassword()!=null) user.setPassword((((User) o).getPassword()));
            if (user.getRole()!=null) user.setRole(((User) o).getRole());
            session.update(user);
            tx.commit();
            updated = true;
        }
        catch (HibernateException e){
            if (tx!=null) tx.rollback();
            e.printStackTrace();
        }
        return updated;
    }

}
