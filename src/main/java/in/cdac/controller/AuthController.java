package in.cdac.controller;


import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;

@Slf4j
@Validated
@RestController
@RequestMapping("/auth")
public class AuthController {

    @PostMapping("/generate")
    public ResponseEntity<?> generateToken() {

        String jwt = Jwts.builder()
                .setSubject("admin")
                .setExpiration(new Date(System.currentTimeMillis() + 60*60*24*1000))
                .signWith(SignatureAlgorithm.HS256, "secret")
                .compact();

        return new ResponseEntity<>(jwt, HttpStatus.OK);
    }
}
