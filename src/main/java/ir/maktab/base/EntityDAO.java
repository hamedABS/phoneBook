package ir.maktab.base;

import org.hibernate.Session;
import org.hibernate.Transaction;


import java.util.List;

/**
 * Created by Hamed-Abbaszadeh on 2/19/2018.
 */
public interface EntityDAO<E> {
    boolean add(E e);
    boolean delete(int id);
    E getById(int id);
    List<E> getAll();
    boolean update(E e);
    Session getSession();
    Transaction getTx(Session session);
    void closeSession(Session session);
}
