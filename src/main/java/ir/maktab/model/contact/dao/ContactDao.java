package ir.maktab.model.contact.dao;
import ir.maktab.base.AbstractEntityDAO;
import ir.maktab.model.contact.Contact;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;
import java.util.ArrayList;
import java.util.List;

public class ContactDao extends AbstractEntityDAO {

    public ContactDao() {
        super();
    }

    public boolean delete(int id) {
        Session session = getSession();
        boolean deleted = false;
        Transaction tx = null;
        try {
            tx = getTx();
            Contact contact = (Contact) session.get(Contact.class , id);
            session.delete(contact);
            tx.commit();
            deleted = true;
        }
        catch (HibernateException e){
            if(tx!=null) tx.rollback();
            e.printStackTrace();
        }
        finally {
            closeSession();
            return deleted;
        }
    }

    public Object getById(int id) {
        Session session = getSession();
        Contact contact = null;
        Transaction tx =null;
        try {
            tx = getTx();
            contact = (Contact) session.get(Contact.class, id);
            tx.commit();
        }
        catch (HibernateException e){
            if(tx!=null) tx.rollback();
            e.printStackTrace();
        }
        finally {
            closeSession();
            return contact;
        }
    }

    public List getAll() {
        Session session = getSession();
        List contacts = new ArrayList();
        Transaction tx = null;
        try {
            tx =getTx();
            contacts =session.createQuery("from Contact c").list();
            tx.commit();
        }
        catch (HibernateException e){
            if(tx!=null) tx.rollback();
            e.printStackTrace();
        }
        finally {
            closeSession();
            return contacts;
        }
    }

    public boolean update(Object o) {
        Session session = getSession();
        boolean updated =false;
        Transaction tx = null;
        try {
            tx = getTx();
            Contact contact = (Contact) session.get(o.getClass() , ((Contact) o).getId());
            if(((Contact)o).getEmail()!=null) contact.setEmail(((Contact) o).getEmail());
            if(((Contact) o).getFirstName()!=null) contact.setFirstName(((Contact) o).getFirstName());
            if(((Contact) o).getLastName()!=null) contact.setLastName(((Contact) o).getLastName());
            if(((Contact) o).getMobile()!=null) contact.setMobile(((Contact) o).getMobile());
            if(((Contact) o).getHome()!=null) contact.setHome(((Contact) o).getHome());
            session.update(contact);
            updated = true;
            tx.commit();
        }
        catch (HibernateException e){
            if(tx!=null) tx.rollback();
            e.printStackTrace();
        }
        finally {
            closeSession();
            return updated;
        }
    }
}
