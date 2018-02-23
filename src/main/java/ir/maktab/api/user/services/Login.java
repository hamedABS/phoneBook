package ir.maktab.api.user.services;

import io.jsonwebtoken.Jwt;
import io.jsonwebtoken.JwtBuilder;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import ir.maktab.api.user.dto.UserAuthDTO;
import ir.maktab.model.user.User;
import ir.maktab.model.user.manager.UserManager;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.awt.event.KeyListener;
import java.util.ArrayList;
import java.util.Iterator;
import javax.crypto.spec.SecretKeySpec;
import javax.xml.bind.DatatypeConverter;
import javax.xml.crypto.Data;
import java.security.Key;
import java.util.Date;
import javax.crypto.spec.SecretKeySpec;
import javax.xml.bind.DatatypeConverter;
import java.security.Key;
import io.jsonwebtoken.*;
import java.util.Date;

/**
 * Created by Hamed-Abbaszadeh on 2/21/2018.
 */
@Path("/login")
public class Login {

    UserManager userManager =new UserManager();

    @POST
    @Path("authenticate")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response authenticate(UserAuthDTO userAuthDTO) {
        ArrayList users = userManager.getUserAuthDTO();
        Iterator iterator = users.iterator();
        System.out.println(userAuthDTO.getPassword());
        UserAuthDTO user;
        System.out.println("Request Received");
        while (iterator.hasNext()) {
            user = (UserAuthDTO) iterator.next();
            if (user.getPassword().equals(userAuthDTO.getPassword()) && user.getUsername().equals(userAuthDTO.getUsername())) {
                //user.setToken(createJWT(user.getUsername()));
                //return Response.ok(user.getToken()).build();
                System.out.println("success");
                return Response.status(200).build();
            }
        }
        return Response.status(Response.Status.FORBIDDEN).build();
    }
    //generate a token to users
    private String createJWT(String subject) {
        SignatureAlgorithm signatureAlgorithm = SignatureAlgorithm.HS256;
        long nowMillis = System.currentTimeMillis();
        Date now = new Date(nowMillis);
        Date exp = new Date(System.currentTimeMillis()+604800000);

        byte[] apiKeySecretBytes = DatatypeConverter.parseBase64Binary("cGhvbmVib29rCg==");
        Key signingKey = new SecretKeySpec(apiKeySecretBytes, signatureAlgorithm.getJcaName());

        JwtBuilder builder = Jwts.builder().setIssuedAt(now)
                .setSubject(subject)
                .setExpiration(exp)
                .signWith(signatureAlgorithm, signingKey);

        return builder.compact();
    }

    private Claims parseJWT(String jwt) {

        //This line will throw an exception if it is not a signed JWS (as expected)
        Claims claims = Jwts.parser()
                .setSigningKey(DatatypeConverter.parseBase64Binary("cGhvbmVib29rCg=="))
                .parseClaimsJws(jwt).getBody();
        return claims;
    }

}
