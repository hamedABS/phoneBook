package ir.maktab.api.user.services;

import ir.maktab.api.filters.Level1Filter;
import ir.maktab.model.user.User;
import ir.maktab.model.user.manager.UserManager;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;


/**
 * Created by Hamed-Abbaszadeh on 2/22/2018.
 */
@Path("/UserServices")
public class UserServices {

    UserManager userManager = new UserManager();

    @POST
    @Path("/getAll")
    @Produces(MediaType.APPLICATION_JSON)
    @Level1Filter.Level1Secure
    public Response getAllUsers() {
        List<User> users = userManager.getAll();
        return Response.status(200).entity(users).build();
    }

    @Path("/delete/{id}")
    @Level1Filter.Level1Secure
    public Response deleteById(@PathParam("id") int id) {
        return Response.status(200).entity(userManager.delete(id)).build();
    }


    @POST
    @Path("update")
    @Consumes(MediaType.APPLICATION_JSON)
    @Level1Filter.Level1Secure
    public Response updateUser(User user) {
        return Response.status(200).entity(userManager.update(user)).build();
    }


}
