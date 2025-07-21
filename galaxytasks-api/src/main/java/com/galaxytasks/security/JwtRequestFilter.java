package com.galaxytasks.security;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.lang.NonNull;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import com.galaxytasks.services.CustomUserDetailsService;
import com.galaxytasks.services.JwtService;

import io.jsonwebtoken.ExpiredJwtException;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Component
public class JwtRequestFilter extends OncePerRequestFilter {
    
    @Autowired
    private CustomUserDetailsService userDetailsService;
    
    @Autowired
    private JwtService jwtService;
    
    @Override
    protected void doFilterInternal(@NonNull HttpServletRequest request, 
                                  @NonNull HttpServletResponse response, 
                                 @NonNull FilterChain chain) throws ServletException, IOException {
        
        String username = null;
        String jwtToken = null;
        
        // lecture du jwt depuis les cookies
        if(request.getCookies()!=null){
            for(var cookie: request.getCookies()){
                if("jwt".equals(cookie.getName())){
                    jwtToken = cookie.getValue();
                    break;
                }
            }
        }

        // lire le jwt depuis l'entête Auhtorization
        if(jwtToken == null){
            final String requestTokenHeader = request.getHeader("Authorization");
            if(requestTokenHeader != null && requestTokenHeader.startsWith("Bearer")){
                jwtToken = requestTokenHeader.substring(7); // On enlève Bearer
            }
        }

        //On va maintenant extraire le nom d'utilisateur et valider le token
        if (jwtToken != null) {
            try {
                username = jwtService.extractUsername(jwtToken);
            } catch (IllegalArgumentException e) {
                logger.error("Impossible d'obtenir le token JWT", e);
            } catch (ExpiredJwtException e) {
                logger.error("Token JWT expiré", e);
            } catch (Exception e) {
                logger.error("Erreur lors du parsing du token JWT", e);
            }
        } else {
            logger.warn("Le token JWT ne commence pas par Bearer String");
        }
        
        // Valider le token et authentifier l'utilisateur
        if (username != null && SecurityContextHolder.getContext().getAuthentication() == null) {
            
            UserDetails userDetails = this.userDetailsService.loadUserByUsername(username);
            
            // Vérifier si le token est valide
            if (jwtService.isTokenValid(jwtToken, userDetails)) {
                
                // Créer l'objet d'authentification
                UsernamePasswordAuthenticationToken authToken = 
                    new UsernamePasswordAuthenticationToken(
                        userDetails, 
                        null, 
                        userDetails.getAuthorities()
                    );
                
                // Ajouter les détails de la requête
                authToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
                
                // Définir l'authentification dans le contexte de sécurité
                SecurityContextHolder.getContext().setAuthentication(authToken);
            }
        }
        
        // Continuer la chaîne des filtres
        chain.doFilter(request, response);
    }
}