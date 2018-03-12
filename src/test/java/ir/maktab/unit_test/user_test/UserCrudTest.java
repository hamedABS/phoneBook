package ir.maktab.unit_test.user_test;


import ir.maktab.model.role.Role;
import ir.maktab.model.role.dao.RoleDAO;
import ir.maktab.model.user.User;
import ir.maktab.model.user.dao.UserDao;
import org.junit.Before;
import org.junit.Test;

import java.util.Iterator;
import java.util.List;

import static org.junit.Assert.assertTrue;


public class UserCrudTest {
    private UserDao userDao;
    private RoleDAO roleDAO;

    @Before
    public void start() {
        userDao = new UserDao();
        roleDAO = new RoleDAO();
    }

    @Test
    public void add() {
        User user = new User("Hamed", "Abbaszadeh "
                , "hamed", "1234", (Role) roleDAO.getById(1));
        User user2 = new User("Sima", "Soheili "
                , "Sima", "1234", (Role) roleDAO.getById(2));
        assertTrue(userDao.add(user));
        assertTrue(userDao.add(user2));
    }

    @Test
    public void delete() {
        assertTrue(userDao.delete(1));
    }

    @Test
    public void edit() {
        User user = new User("Sima", "Sanaee"
                , "Sima", "Sima", (Role) roleDAO.getById(3));
        user.setId(2);
        assertTrue(userDao.update(user));
    }

    @Test
    public void getById() {
        User user = (User) userDao.getById(15);
        System.out.println(user);
    }

    @Test
    public void getAll() {
        List list = userDao.getAll();
        Iterator iterator = list.iterator();
        while (iterator.hasNext()) {
            System.out.println(((User) iterator.next()).toString());
        }
    }
}