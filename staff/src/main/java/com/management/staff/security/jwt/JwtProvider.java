package com.management.staff.security.jwt;
import com.management.staff.global.exceptions.InvalidTokenException;
import com.management.staff.security.dto.token.JwtToken;
import com.management.staff.security.services.userService.UserDetailsImpl;
import com.nimbusds.jwt.*;
import io.jsonwebtoken.*;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import io.jsonwebtoken.security.SignatureException;
import java.security.Key;
import java.text.ParseException;
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
                .setExpiration(new Date(new Date().getTime()+jwtExpiration*10000))
                .claim("roles", roles)
                .claim("dni", userDetailsImpl.getDni())
                .claim("email", userDetailsImpl.getEmail())
                .compact();
    }
    public String generateRefreshToken(JwtToken token) throws ParseException{
        try {
            Jwts.parserBuilder().setSigningKey(getKey(jwtSecret)).build().parseClaimsJws(token.getToken());
        }
        catch(ExpiredJwtException e){
            
        JWT jwt = JWTParser.parse(token.getToken());
        JWTClaimsSet claims = jwt.getJWTClaimsSet();
        String username =claims.getSubject();
        int dni = claims.getIntegerClaim("dni");
        String email= claims.getStringClaim("email");
        List<String>roles=(List<String>) claims.getClaim("roles");
        
        return Jwts
                .builder()
                .signWith(getKey(jwtSecret))
                .setSubject(username)
                .setIssuedAt(new Date())
                .setExpiration(new Date(new Date().getTime()+jwtExpiration*10000))
                .claim("roles", roles)
                .claim("dni", dni)
                .claim("email", email)
                .compact();
        }
        return null;
    }
    //obtenemos el username desde el token
    public String getUsernameFromToken(String token){
        return Jwts.parserBuilder()
                .setSigningKey(getKey(jwtSecret))
                .build()
                .parseClaimsJws(token)
                .getBody()
                .getSubject();
    }
    //validamos el token
    public boolean isValidToken(String token)throws InvalidTokenException{
        try{
            Jwts.parserBuilder()
                    .setSigningKey(getKey(jwtSecret))
                    .build()
                    .parseClaimsJws(token);
            return true;
        }
        catch(MalformedJwtException |
              UnsupportedJwtException |
              ExpiredJwtException |
              IllegalArgumentException |
              SignatureException e){
            throw new InvalidTokenException(e.getMessage());
        }
    }
}