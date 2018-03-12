package ir.maktab.api.filters;

import com.sun.jndi.toolkit.dir.ContainmentFilter;
import io.jsonwebtoken.Claims;
import ir.maktab.api.contact.service.ContactServices;
import ir.maktab.api.user.services.LoginRegister;
import ir.maktab.api.user.services.UserServices;
import ir.maktab.model.user.manager.UserManager;

import javax.annotation.Priority;
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

/**
 * Created by Hamed-Abbaszadeh on 3/6/2018.
 */

// its provides accessibility to Services that only SuperUsers can use it.

@Level1Filter.Level1Secure
@Provider
@Priority(Priorities.AUTHENTICATION)
public class Level1Filter implements ContainerRequestFilter {

    UserManager userManager = new UserManager();

    @NameBinding
    @Retention(RUNTIME)
    @Target({TYPE, METHOD})
    public @interface Level1Secure { }

    @Override
    public void filter(ContainerRequestContext requestContext) throws IOException {

        String authHeader = requestContext.getHeaderString(HttpHeaders.AUTHORIZATION);
        String token = authHeader.substring(0).trim();
        System.out.println("in level1 token  :" +token);
        validateToken(token,requestContext);
    }

    public void validateToken(String token ,ContainerRequestContext requestContext){

        Claims claims = LoginRegister.parseJWT(token);
        String userName =claims.getSubject();
        System.out.format("real token in level1Filter : %s \n" , userName);
        if(!userManager.isSuperUser(userName)){
            requestContext.abortWith(
                    Response.status(Response.Status.UNAUTHORIZED)
                            .build());
            return;
        }
        else   {
            System.out.println("well done");
        }
    }
}
