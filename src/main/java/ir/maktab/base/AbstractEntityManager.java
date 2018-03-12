package ir.maktab.base;

import java.util.List;

/**
 * Created by Hamed-Abbaszadeh on 2/21/2018.
 */
public abstract class AbstractEntityManager<E> implements EntityManager {

    protected EntityDAO entityDAO ;

    public boolean add(Object o) {
        return entityDAO.add(o);
    }

    public boolean delete(int id) {
        return entityDAO.delete(id);
    }

    public Object getByid(int id) {
        return entityDAO.getById(id);
    }

    public List getAll() {
        return entityDAO.getAll();
    }

    public boolean update(Object o) {
        return entityDAO.update(o);
    }

}
