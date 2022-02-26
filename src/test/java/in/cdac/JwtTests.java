package in.cdac;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.Date;


public class JwtTests {
    private static final String SEC_KEY = "3783726c36e074031e5a1c1b4e1ade6a1f658e43b78952409a025f8e2926671f";

    @Test
    void test1() {
        String jwt = Jwts.builder()
                .setId("admin001")
                .setSubject("administration")
                .setIssuer("cdac")
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + 60*60*24*10*1000))
                .signWith(SignatureAlgorithm.HS256, SEC_KEY)
                .compact();

        System.out.println(jwt);
        Assertions.assertNotNull(jwt);
    }


    @Test
    public void test2() {
        String jwt = "eyJhbGciOiJIUzI1NiJ9.eyJqdGkiOiJhZG1pbjAwMSIsInN1YiI6ImFkbWluaXN0cmF0aW9uIiwiaXNzIjoiY2RhYyIsImlhdCI6MTY0NTg0MjQxNiwiZXhwIjoxNjQ2NzA2NDE2fQ.2T0ambyKEZvKyr7h2NM3YiT2SvlsbvkiVft4uaaiB9s";

         Claims claims = Jwts.parser().setSigningKey(SEC_KEY).parseClaimsJws(jwt).getBody();
         String subject = claims.getSubject();

         Assertions.assertNotNull(subject);
         Assertions.assertFalse(claims.getExpiration().before(new Date()));

    }
}
