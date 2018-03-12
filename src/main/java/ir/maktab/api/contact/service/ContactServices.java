package ir.maktab.api.contact.service;
import ir.maktab.api.filters.Level2Filter;
import ir.maktab.api.filters.Level3Filter;
import ir.maktab.model.contact.Contact;
import ir.maktab.model.contact.dao.ContactDao;
import ir.maktab.model.contact.manager.ContactManager;
import org.apache.log4j.Logger;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;

/**
 * Created by Hamed-Abbaszadeh on 2/22/2018.
 */

@Path("Contact")
public class ContactServices {
    ContactManager contactManager = new ContactManager();

    @POST
    @Path("getAll")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getAllContacts(){
        List<Contact> contacts;
        contacts = contactManager.getAll();
        return Response.status(200).entity(contacts).build();
    }

    @GET
    @Path("delete/{id}")
    @Level2Filter.Level2Secure
    public Response deleteById(@PathParam("id") int id){
        if (contactManager.delete(id))
            return Response.status(200).build();
        else
        return Response.status(201).build();
    }


    @POST
    @Path("update")
    @Consumes(MediaType.APPLICATION_JSON)
    @Level2Filter.Level2Secure
    public Response updateContact(Contact contact){
        if (contactManager.update(contact)) {
            return Response.status(200).build();
        }
        else return Response.status(201).build();
    }

    @POST
    @Path("add")
    @Consumes(MediaType.APPLICATION_JSON)
    @Level3Filter.Level3Secure
    public Response addContact(Contact contact){
        if(contactManager.add(contact)){
            return Response.status(201).build();
        }
        return Response.status(202).build();
    }

}
