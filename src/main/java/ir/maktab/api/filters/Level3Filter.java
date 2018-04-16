package ir.maktab.api.filters;

import com.sun.jndi.toolkit.dir.ContainmentFilter;
import io.jsonwebtoken.Claims;
import ir.maktab.api.contact.service.ContactServices;
import ir.maktab.api.user.services.LoginRegister;
import ir.maktab.api.user.services.UserServices;
import ir.maktab.model.user.manager.UserManager;

import javax.annotation.Priority;
import javax.naming.directory.Attributes;
import javax.ws.rs.NameBinding;
import javax.ws.rs.Priorities;
import javax.ws.rs.container.ContainerRequestContext;
import javax.ws.rs.container.ContainerRequestFilter;
import javax.ws.rs.core.HttpHeaders;
import javax.ws.rs.core.Response;
import javax.ws.rs.ext.Provider;
import java.io.IOException;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.METHOD;
import static java.lang.annotation.ElementType.TYPE;
import static java.lang.annotation.RetentionPolicy.RUNTIME;


//it's provides accessibility to Services that SuperUsers & Admins & Users Can use it
@Level3Filter.Level3Secure
@Provider
@Priority(Priorities.AUTHENTICATION)
public class Level3Filter implements ContainerRequestFilter {

    @NameBinding
    @Retention(RUNTIME)
    @Target({TYPE, METHOD})
    public @interface Level3Secure { }


    UserManager userManager = new UserManager();
    @Override
    public void filter(ContainerRequestContext requestContext) throws IOException {

        String authHeader = requestContext.getHeaderString(HttpHeaders.AUTHORIZATION);
        try{
            String token = authHeader.substring(0).trim();
            validateToken(token ,requestContext);
        }
        catch (NullPointerException e){
            requestContext.abortWith(
                    Response.status(Response.Status.UNAUTHORIZED)
                            .build());
            return;
        }
    }

    public void validateToken(String token ,ContainerRequestContext requestContext){

        Claims claims = LoginRegister.parseJWT(token);
        String userName =claims.getSubject();
        System.out.format("real token in level3Filter : %s \n" , userName);

        if(!userManager.isSuperUser(userName) && !userManager.isAdmin(userName) && !userManager.isUser(userName)){
            requestContext.abortWith(
                    Response.status(Response.Status.UNAUTHORIZED)
                            .build());
            return;
        }
        else{
            System.out.println("well done");
        }
    }
}
