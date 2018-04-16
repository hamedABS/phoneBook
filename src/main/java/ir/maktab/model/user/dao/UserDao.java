package ir.maktab.model.user.dao;

import ir.maktab.base.AbstractEntityDAO;
import ir.maktab.model.user.User;
import org.hibernate.*;

import java.util.List;

/**
 * Created by Hamed-Abbaszadeh -> 09385136659 on 2/19/2018.
 */

public class UserDao extends AbstractEntityDAO {

    public boolean delete(int id) {
        Session session = getSession();
        Transaction tx = null;
        boolean deleted = false;
        try {
            tx = getTx(session);
            User user = (User) session.get(User.class , id);
            session.delete(user);
            tx.commit();
            deleted = true;
        }
        catch (HibernateException e){
            if(tx!=null) tx.rollback();
            e.printStackTrace();
        }
        finally {
            closeSession(session);
            return deleted;
        }
    }

    public Object getById(int id) {
        Session session = getSession();
        Transaction tx = null;
        User user = null;
        try {
            tx = getTx(session);
            user = (User) session.get(User.class , id);
            session.get(User.class , id);
            tx.commit();
        }
        catch (HibernateException e){
            if(tx!=null) tx.rollback();
            e.printStackTrace();
        }
        finally {
            closeSession(session);
            return user;
        }
    }

    public List getAll() {
        Session session = getSession();
        List list =null;
        Transaction tx = null;
        try {
            tx =getTx(session);
            list= session.createCriteria(User.class).list();
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
            tx = getTx(session);
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

    public boolean isSuperUser(String userName){
        Session session = getSession();
        Transaction tx =null;
        boolean isSuperUser =false;
        try {
            tx = getTx(session);
            String hql="from User where username= :username";
            Query query = session.createQuery(hql).setParameter("username",userName);
            User user  = (User) query.list().get(0);
            System.out.println(user);
            System.out.println(user.getRole().getName());
            if(user.getRole().getName().equals("SuperUser")) isSuperUser=true;
            tx.commit();
        }
        catch (Exception e ){
            if(tx!=null) tx.rollback();
            e.printStackTrace();
        }
        finally {
            closeSession(session);
            System.out.println("isSuperUser " + isSuperUser );
            return isSuperUser;
        }
    }

    public  boolean isAdmin(String userName){
        Session session = getSession();
        Transaction tx=null;
        boolean isAdmin = false;

        try {
            tx=getTx(session);
            String hql="from User where username=:username";
            Query query = session.createQuery(hql);
            query.setParameter("username",userName);
            User user  = (User) query.list().get(0);
            if(user.getRole().getName().equals("Admin")) isAdmin = true;
            tx.commit();
        }
        catch (HibernateException e){
            if(tx!=null) tx.rollback();
            e.printStackTrace();
        }
        finally {
            closeSession(session);
            System.out.println("isAdmin: "+isAdmin);
            return isAdmin;
        }
    }

    public boolean isUser(String userName){
        Session session = getSession();
        Transaction tx =null;
        boolean isUser =false;

        try {
            tx =getTx(session);
            String hql="from User where username=:username";
            Query query = session.createQuery(hql);
            query.setParameter("username",userName);
            User user  = (User) query.list().get(0);
            if (user.getRole().getName().equals("User")) isUser = true;
            tx.commit();
        }
        catch (HibernateException e){
            if(tx!=null) tx.rollback();
            e.printStackTrace();
        }
        finally {
            closeSession(session);
            return isUser;
        }
    }

    public boolean isGuest(String userName){
        Session session = getSession();
        Transaction tx =null;
        boolean isGuest =false;

        try {
            tx = getTx(session);
            String hql="from User where username=:username";
            Query query = session.createQuery(hql);
            query.setParameter("username",userName);
            User user  = (User) query.list().get(0);
            if (user.getRole().getName().equals("Guest")) isGuest = true;
            tx.commit();
        }
        catch(HibernateException e){
            if(tx!=null) tx.rollback();
            e.printStackTrace();
        }
        finally {
            closeSession(session);
            return isGuest;
        }
    }
}
