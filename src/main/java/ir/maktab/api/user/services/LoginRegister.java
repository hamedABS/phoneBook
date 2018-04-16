package ir.maktab.api.user.services;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtBuilder;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import ir.maktab.api.user.dto.UserAuthDTO;
import ir.maktab.model.user.User;
import ir.maktab.model.user.manager.UserManager;
import org.apache.log4j.Logger;

import javax.crypto.spec.SecretKeySpec;
import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.xml.bind.DatatypeConverter;
import java.security.Key;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;

/**
 * Created by Hamed-Abbaszadeh on 2/21/2018.
 */
@Path("/loginRegister")
public class LoginRegister {

    UserManager userManager =new UserManager();
    Logger logger = Logger.getRootLogger();
    @POST
    @Path("authenticate")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response authenticate(UserAuthDTO userAuthDTO) {
        ArrayList users = (ArrayList) userManager.getAll();
        Iterator iterator = users.iterator();

        User user;
        while (iterator.hasNext()) {
            user = (User) iterator.next();
            if (user.getPassword().equals(userAuthDTO.getPassword()) && user.getUsername().equals(userAuthDTO.getUsername())) {
                // I send user role in authentication header to setting special page in client side!

                logger.fatal("Some body Logged in ..PPPP");
                return Response.ok(createJWT(user.getUsername())).header("authentication",user.getRole().getName()).build();
            }
        }
        return Response.status(Response.Status.FORBIDDEN).build();
    }
    //generate a token to users
    public static String createJWT(String subject) {
        SignatureAlgorithm signatureAlgorithm = SignatureAlgorithm.HS256;
        long nowMillis = System.currentTimeMillis();
        Date now = new Date(nowMillis);
        Date exp = new Date(System.currentTimeMillis()+604800000);

        // generating phoneBook Base64 api secret Key
        byte[] apiKeySecretBytes = DatatypeConverter.parseBase64Binary("cGhvbmVib29rCg==");
        Key signingKey = new SecretKeySpec(apiKeySecretBytes, signatureAlgorithm.getJcaName());

        JwtBuilder builder = Jwts.builder().setIssuedAt(now)
                .setSubject(subject)
                .setExpiration(exp)
                .signWith(signatureAlgorithm, signingKey);

        return builder.compact();
    }

    public static Claims parseJWT(String jwt) {

        //This line will throw an exception if it is not a signed JWS (as expected)
        Claims claims = Jwts.parser()
                .setSigningKey(DatatypeConverter.parseBase64Binary("cGhvbmVib29rCg=="))
                .parseClaimsJws(jwt).getBody();
        return claims;
    }

}
