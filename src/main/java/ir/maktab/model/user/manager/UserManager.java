package ir.maktab.model.user.manager;

import ir.maktab.api.user.dto.UserAuthDTO;
import ir.maktab.base.AbstractEntityManager;
import ir.maktab.base.EntityDAO;
import ir.maktab.model.user.User;
import ir.maktab.model.user.dao.UserDao;

import java.util.ArrayList;
import java.util.Iterator;

/**
 * Created by Hamed-Abbaszadeh on 2/19/2018.
 */
public class UserManager extends AbstractEntityManager{

    public UserManager(){
        entityDAO = getDao();
    }
    public EntityDAO getDao() {
        return new UserDao();
    }

    public ArrayList<UserAuthDTO> getUserAuthDTO(){
        ArrayList<UserAuthDTO> result  = new ArrayList();
        UserAuthDTO userDTO ;
        ArrayList users ;
        User user;

        users = (ArrayList) entityDAO.getAll();
        Iterator iterator = users.iterator();

        while (iterator.hasNext()){
            user = (User) iterator.next();
            userDTO = new UserAuthDTO(user.getUsername(),user.getPassword());
            result.add(userDTO);
        }
        return result;
    }
}
