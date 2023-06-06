package com.management.staff.security.jwt;
import com.management.staff.security.utils.UserDetailsImpl;
import io.jsonwebtoken.*;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import io.jsonwebtoken.security.SignatureException;
import java.security.Key;
import java.util.*;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.*;
import org.springframework.stereotype.Component;
@Component
public class JwtProvider{
    @Value("${jwt.secret}")
    private String jwtSecret;
    @Value("${jwt.expiration}")
    private int jwtExpiration;
    //generar la clave
    private Key getKey(String secret){
        byte[]secretByte=Decoders.BASE64URL.decode(secret);
        return Keys.hmacShaKeyFor(secretByte);
    }
    
    //generamos el token con el el objeto userDetails
    public String generateToken(Authentication auth){
        UserDetailsImpl userDetailsImpl= (UserDetailsImpl) auth.getPrincipal();
              List<String>roles=userDetailsImpl
                .getAuthorities()
                .stream()
                .map(GrantedAuthority::getAuthority)
                .collect(Collectors.toList());  
        return Jwts
                .builder()
                .signWith(getKey(jwtSecret))
                .setSubject(userDetailsImpl.getUsername())
                .setIssuedAt(new Date())
                .setExpiration(new Date(new Date().getTime()+jwtExpiration*5000))
                .claim("roles", roles)
                .claim("dni", userDetailsImpl.getDni())
                .compact();
    }
    //obtenemos el username desde el token
    public String getEmailFromToken(String token){
        return Jwts.parserBuilder()
                .setSigningKey(getKey(jwtSecret))
                .build()
                .parseClaimsJws(token)
                .getBody()
                .getSubject();
    }
    //validamos el token
    public boolean isValidToken(String token){
        try{
            Jwts.parserBuilder()
                    .setSigningKey(getKey(jwtSecret))
                    .build()
                    .parseClaimsJws(token);
            return true;
        }
        catch (MalformedJwtException | UnsupportedJwtException | ExpiredJwtException | IllegalArgumentException | SignatureException  e)
        {}
        return false;
    }
}