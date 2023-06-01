package com.cybersoft.cozastore.utils;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.io.Encoders;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.crypto.SecretKey;

@Component
public class JWTHelperUtils {
        @Value("${jwt.token.key}")
        String secrectKey;

    public String generateToken(String data){
        SecretKey key = Keys.hmacShaKeyFor(Decoders.BASE64.decode(secrectKey));
        String token = Jwts.builder().setSubject(data).signWith(key).compact();
        return token;
    }

    public String validToken(String token){
        SecretKey key = Keys.hmacShaKeyFor(Decoders.BASE64.decode(secrectKey));
//        chuẩn bị chìa khóa để tiến hành giải mã
        return Jwts.parserBuilder().setSigningKey(key).build()
                .parseClaimsJws(token) //Truyền token cần giải mã
                .getBody().getSubject();
    }

}
