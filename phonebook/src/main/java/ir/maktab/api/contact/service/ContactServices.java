package ir.maktab.api.contact.service;
import com.sun.org.apache.regexp.internal.RE;
import ir.maktab.model.contact.Contact;
import ir.maktab.model.contact.dao.ContactDao;
import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Hamed-Abbaszadeh on 2/22/2018.
 */
@Path("Contact")
public class ContactServices {


    ContactDao contactDao = new ContactDao();

    @POST
    @Path("getAll")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getAllUsers(){
        List<Contact> contacts;
        contacts = contactDao.getAll();
        return Response.status(200).entity(contacts).build();
    }

    @GET
    @Path("delete/{id}")
    public Response deleteById(@PathParam("id") int id){
        if (contactDao.delete(id))
            return Response.status(200).build();
        else
        return Response.status(201).build();
    }


    @POST
    @Path("update")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response updateUser(Contact contact){
        if (contactDao.update(contact))
            return Response.status(200).build();
        else return Response.status(201).build();
    }

    @POST
    @Path("add")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response addContact(Contact contact){
        if(contactDao.add(contact)){
            return Response.status(201).build();
        }
        return Response.status(202).build();
    }

}
