package ir.maktab.base;

import java.util.List;

/**
 * Created by Hamed-Abbaszadeh -> 09385136659 on 2/19/2018.
 */
public interface EntityManager<E> {

    EntityDAO getDao();
    boolean add(E e);
    boolean delete(int id);
    E getByid(int id);
    List<E> getAll();
    boolean update(E e);
}
