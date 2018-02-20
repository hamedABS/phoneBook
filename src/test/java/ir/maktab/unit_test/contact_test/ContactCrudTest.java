package ir.maktab.unit_test.contact_test;

import ir.maktab.base.AbstractEntityDAO;
import ir.maktab.model.contact.dao.ContactDao;
import ir.maktab.model.contact.Contact;
import org.junit.Before;
import org.junit.Test;

import java.util.Iterator;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

/**
 * Created by Hamed-Abbaszadeh on 2/19/2018.
 */
public class ContactCrudTest {
    private ContactDao contactDao;
    @Before
    public void start(){
        contactDao = new ContactDao();
    }
    @Test
    public void add(){
        Contact contact = new Contact("Qolam","Rahmani",
                "Qolam.rahmani@gmial.com","0217555878","093855666");
        assertTrue(contactDao.add(contact));
    }

    @Test
    public void delete(){
        assertTrue(contactDao.delete(4));
    }

    @Test
    public void edit(){
        Contact contact = new Contact("Hamedof",null,"Abbaszadeh",null,"021555864078");
        contact.setId(5);
        contactDao.update(contact);
    }

    @Test
    public void getById(){
        Contact contact = (Contact) contactDao.getById(6);
        Contact contact2 = new Contact("Nader","Abolfazli",
                "nader.abolfazli@gmial.com","02155555","093855555");
        contact2.setId(6);
        assertEquals(contact , contact2);

    }

    @Test
    public void getAll(){
        List list = contactDao.getAll();
        Iterator iterator = list.iterator();
        while (iterator.hasNext()){
            System.out.println(((Contact)iterator.next()).getFirstName());
        }
    }
}
