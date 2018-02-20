package ir.maktab.base;

import java.util.List;

/**
 * Created by Hamed-Abbaszadeh on 2/19/2018.
 */
public interface EntityManager<E> {

    EntityDAO getDao();
    void add(E e);
    void delete(E e);
    E getByid(int id);
    List<E> getAll();
    void update(E e);
}
