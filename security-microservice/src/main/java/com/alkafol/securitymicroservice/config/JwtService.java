package com.alkafol.securitymicroservice.config;

import com.alkafol.securitymicroservice.models.Role;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.security.Key;
import java.util.Date;
import java.util.Map;
import java.util.function.Function;

@Service
public class JwtService {
    private static final String SECRET_KEY = "782F413F4428472D4B6150645367566B5970337336763979244226452948404D";

    public String getUsername(String jwt){
        return getClaim(jwt, Claims::getSubject);
    }

    private Key getSignInKey(){
        byte[] keyBytes = Decoders.BASE64.decode(SECRET_KEY);
        return Keys.hmacShaKeyFor(keyBytes);
    }

    public <T> T getClaim(String token, Function<Claims, T> claimsResolver){
        Claims claims = getAllClaims(token);
        return claimsResolver.apply(claims);
    }

    public String generateToken(Map<String, Object> claims, UserDetails userDetails){
        return Jwts
                .builder()
                .setClaims(claims)
                .setSubject(userDetails.getUsername())
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis() + 1000 * 60 * 60 * 24))
                .signWith(getSignInKey(), SignatureAlgorithm.HS256)
                .compact();
    }

    public boolean isUserTokenValid(String token){
        Jwts.parserBuilder().setSigningKey(getSignInKey()).build().parseClaimsJws(token);
        return !isTokenExpired(token);
    }

    public boolean isAdminTokenValid(String token){
        Jwts.parserBuilder().setSigningKey(getSignInKey()).build().parseClaimsJws(token);
        return !isTokenExpired(token) && isTokenAdmin(token);
    }

    private boolean isTokenAdmin(String token) {
        return getRole(token) == Role.ADMIN;
    }

    private boolean isTokenExpired(String token){
        return getExpiration(token).before(new Date());
    }

    private Date getExpiration(String token) {
        return getClaim(token, Claims::getExpiration);
    }

    private Role getRole(String token){
        return Role.valueOf(getClaim(token, (a) -> a.get("role")).toString());
    }

    private Claims getAllClaims(String token){
        return Jwts
                .parserBuilder()
                .setSigningKey(getSignInKey())
                .build()
                .parseClaimsJws(token)
                .getBody();
    }
}
