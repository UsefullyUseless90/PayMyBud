package com.paymybuddy.paymybuddy.config;

import com.paymybuddy.paymybuddy.models.RoleUtilisateurs;
import com.paymybuddy.paymybuddy.models.dto.UtilisateurDTO;
import io.jsonwebtoken.*;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.crypto.SecretKey;
import java.nio.charset.StandardCharsets;
import java.util.Date;
import java.util.List;

@Component
public class JwtManager {

    @Value("${jwt.secret}")
    private String secret;

    private int expirationTime = 3600000;

    public String generateToken(UtilisateurDTO u) {
        Claims claims = Jwts.claims().setSubject(u.getNomPrenom());
        claims.put("userId", u.getIdUtilisateur() + "");
        claims.put("role", u.getRoles());

        return Jwts.builder()
                .setClaims(claims)
                .signWith(SignatureAlgorithm.HS512, secret)
                .compact();
    }
    public boolean validate(String token){
        try{
            Jwts.parserBuilder().setSigningKey(getJwtKey()).build().parseClaimsJws(token);
            return true;
        }catch (Exception e){
            System.err.println("Token JWT Invalide: "+e.getMessage());
        }
        return false;
    }
    private SecretKey getJwtKey(){
        byte[] keyBytes = this.secret.getBytes(StandardCharsets.UTF_8);
        return Keys.hmacShaKeyFor(keyBytes);
    }

}