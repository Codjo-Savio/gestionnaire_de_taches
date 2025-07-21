package com.galaxytasks.services;

import java.security.Key;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;

@Service
public class JwtService {
    // clé secrète
    private String SECRET_KEY;

    public JwtService(@Value("${spring.security.jwt.secret}") String SECRET_KEY) {
        this.SECRET_KEY = SECRET_KEY;
    }
    private static final long ACCESS_TOKEN_EXPIRATION = 900000; // 15 min
    private static final long REFRESH_TOKEN_EXPIRATION = 604800000; // 7 jours

    // générer un acces token
    public String generateAccessToken(UserDetails userDetails){
        return generateToken(userDetails, ACCESS_TOKEN_EXPIRATION);
    }

    // générer un refresh token
    public String generateRefreshToken(UserDetails userDetails){
        return generateToken(userDetails, REFRESH_TOKEN_EXPIRATION);
    }

    // Méthode principale pour générer un token
    private String generateToken(UserDetails userDetails, long expiration) {
        Map<String, Object> claims = new HashMap<>();
        
        // Ajouter des claims personnalisés
        claims.put("email", userDetails.getUsername());
        claims.put("roles", userDetails.getAuthorities().stream()
                .map(GrantedAuthority::getAuthority)
                .collect(Collectors.toList()));
        
        return createToken(claims, userDetails.getUsername(), expiration);
    }

    // Convertir la clé en une vraie clé cryptographique
    private Key getSigningKey() {
        return Keys.hmacShaKeyFor(SECRET_KEY.getBytes());
    }   

    // Méthode pour créer un token
    private String createToken(Map<String, Object> claims, String subject, long expiration ){
        return Jwts.builder()
        .setClaims(claims) // récupérer les informations dont on a besoin
        .setSubject(subject) // l'identifiant(dans notre cas, l'email)
        .setIssuedAt(new Date(System.currentTimeMillis())) // Date de création du token
        .setExpiration(new Date(System.currentTimeMillis() + expiration)) // Date d'expiration du Token
        .signWith(getSigningKey(), SignatureAlgorithm.HS256) // Signature avec la clé secrète
        .compact(); // générer le String final
    }

    // Extraction des claims (Lecture du Token)
    
    // Extrait tous les claims du token
    private Claims extractAllClaims(String token) {
        return Jwts.parserBuilder()
                .setSigningKey(getSigningKey())    // Utilise la même clé pour vérifier la signature
                .build()
                .parseClaimsJws(token)          // Parse et vérifie le token
                .getBody();                     // Récupère le contenu (claims)
    }

     // Méthode générique pour extraire n'importe quel claim
    public <T> T extractClaim(String token, Function<Claims, T> claimsResolver) {
        final Claims claims = extractAllClaims(token);
        return claimsResolver.apply(claims);
    }

    // Extraction de l'identifiant
    public String extractUsername(String token){
        return extractClaim(token, Claims::getSubject);
    }

    // Extraction de la date d'expiration
    public Date extractExpiration(String token){
        return extractClaim(token, Claims::getExpiration);
    }

    // Extraction des rôles
    public List<String> extractRoles(String token) {
        Claims claims = extractAllClaims(token);
        Object rolesObj = claims.get("roles");
        List<String> roles = new ArrayList<>();
        if (rolesObj instanceof List<?>) {
            for (Object role : (List<?>) rolesObj) {
                if (role instanceof String) {
                    roles.add((String) role);
                }
            }
        }
        return roles;
    }

    // Validation des tokens

    // vérifier si le token a expire
    public boolean isTokenExpired(String token){
        return (extractExpiration(token).before(new Date()));
    }

    // vérifier si le token est valide
    public boolean isTokenValid(String token, org.springframework.security.core.userdetails.UserDetails userDetails){
        final String username = extractUsername(token);
        return (username.equals(userDetails.getUsername()) && !isTokenExpired(token));
    }

     // Vérifie si l'utilisateur a un rôle spécifique
    public boolean hasRole(String token, String role) {
        List<String> roles = extractRoles(token);
        return roles != null && roles.contains(role);
    }

    // extraire un token à partir du cookie
    public String extractTokenFromCookie(HttpServletRequest request){
        if(request.getCookies() != null){
            for(Cookie cookie : request.getCookies()){
                if("jwt".equals(cookie.getName())){
                    return cookie.getValue();
                }
            }
        }
        return null;
    }
}
