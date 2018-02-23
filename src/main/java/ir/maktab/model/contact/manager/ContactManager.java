package ir.maktab.model.contact.manager;

import ir.maktab.base.AbstractEntityManager;
import ir.maktab.base.EntityDAO;
import ir.maktab.base.EntityManager;
import ir.maktab.model.contact.Contact;
import ir.maktab.model.contact.dao.ContactDao;

import java.util.List;

/**
 * Created by Hamed-Abbaszadeh on 2/19/2018.
 */
public class ContactManager extends AbstractEntityManager {

    public ContactManager(){
        entityDAO = getDao();
    }

    @Override
    public EntityDAO getDao() {
        return new ContactDao();
    }
}
