package com.booking.service;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.booking.entity.PropertyUser;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.Date;


@Service
public class JWTService {



    @Value("${jwt.algo.key}")
    private String algorithmKey;

    @Value("${jwt.issuer}")
    private String issuer;

    @Value("${jwt.expiryTime.duration}")
    private int expiryTime;


    private Algorithm algorithm;

    private final static String USERNAME ="username";

    @PostConstruct
    public void postConstruct(){
        algorithm = Algorithm.HMAC256(algorithmKey);
    }

    public String generateToken(PropertyUser propertyUser){

            return JWT.create().withClaim(USERNAME,propertyUser.getUsername())
                    .withExpiresAt(new Date(System.currentTimeMillis()+expiryTime))
                    .withIssuer(issuer)
                    .sign(algorithm);  //{CEIS}- CS engr.is seeking job.......
    }

    public String getUsername(String token){

        DecodedJWT decodedJWT = JWT.require(algorithm).withIssuer(issuer).build().verify(token);
        return decodedJWT.getClaim(USERNAME).asString();
    }



}
