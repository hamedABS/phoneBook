package ir.maktab.unit_test.user_test;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtBuilder;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.junit.Test;

import javax.crypto.spec.SecretKeySpec;
import javax.xml.bind.DatatypeConverter;
import java.security.Key;
import java.util.Date;

/**
 * Created by Hamed-Abbaszadeh on 2/22/2018.
 */
public class TestEncrypt {

    @Test
    public void encriptTest(){
        parseJWT(createJWT("Hamed"));
    }

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
    private void parseJWT(String jwt) {

        //This line will throw an exception if it is not a signed JWS (as expected)
        Claims claims = Jwts.parser()
                .setSigningKey(DatatypeConverter.parseBase64Binary("cGhvbmVib29rCg=="))
                .parseClaimsJws(jwt).getBody();
        System.out.println("Subject: " + claims.getSubject());
        System.out.println("Expiration: " + claims.getExpiration());
    }

}
