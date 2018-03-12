package ir.maktab.api.filters;

import com.google.common.annotations.VisibleForTesting;
import io.jsonwebtoken.Claims;
import ir.maktab.api.contact.service.ContactServices;
import ir.maktab.api.user.services.LoginRegister;
import ir.maktab.api.user.services.UserServices;
import ir.maktab.model.user.dao.UserDao;
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
import java.util.List;

import static java.lang.annotation.ElementType.METHOD;
import static java.lang.annotation.ElementType.TYPE;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

/**
 * Created by Hamed-Abbaszadeh on 3/5/2018.
 */

// its provides accessibility to Services that SuperUsers & Admins can use it.
@Level2Filter.Level2Secure
@Provider
@Priority(Priorities.AUTHENTICATION)
public class Level2Filter implements ContainerRequestFilter {

    @NameBinding
    @Retention(RUNTIME)
    @Target({TYPE, METHOD})
    public @interface Level2Secure { }

    UserManager userManager = new UserManager();

    @Override
    public void filter(ContainerRequestContext requestContext) throws IOException {

        String authHeader = requestContext.getHeaderString(HttpHeaders.AUTHORIZATION);
        String token = authHeader.substring(0).trim();
        System.out.println("in level2 token: "+token);
        validateToken(token ,requestContext);
    }

    public void validateToken(String token ,ContainerRequestContext requestContext){
        Claims claims = LoginRegister.parseJWT(token);
        String userName =claims.getSubject();
        System.out.format("real token level2Filter  : %s \n" , userName);
        if(!userManager.isSuperUser(userName) && !userManager.isAdmin(userName)){
            System.out.println("not recognized");
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
