package com.galaxytasks.controllers;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.galaxytasks.dto.AuthResponseDTO;
import com.galaxytasks.dto.LoginRequestDTO;
import com.galaxytasks.dto.RegisterDTO;
import com.galaxytasks.dto.UtilisateurDTO;
import com.galaxytasks.exceptions.MessageResponse;
import com.galaxytasks.security.CustomUserDetails;
import com.galaxytasks.services.CustomUserDetailsService;
import com.galaxytasks.services.JwtService;
import com.galaxytasks.services.UtilisateurService;

import jakarta.validation.Valid;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/api/auth")
public class AuthenticationController {
    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private CustomUserDetailsService userDetailsService;

    @Autowired
    private UtilisateurService utilisateurService;

    @Autowired
    private JwtService jwtService;

    @Autowired
    private PasswordEncoder passwordEncoder;

    // POST - Se connecter à son compte
    // api/auth/login
    @PostMapping("/login")
    public  ResponseEntity<?> authenticateUser(@Valid @RequestBody LoginRequestDTO loginRequestDTO){
        try{
            // Authentification par email et mot de passe
            Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                    loginRequestDTO.getEmail(),
                    loginRequestDTO.getMotDePasse()
                )
            );
            
            // génerer un Access Token et un Refresh Token
            CustomUserDetails userDetails = (CustomUserDetails) authentication.getPrincipal();
            String jwtAccess = jwtService.generateAccessToken(userDetails);
            String jwtRefresh = jwtService.generateRefreshToken(userDetails);

            List<String> roles = userDetails.getAuthorities()
                .stream()
                .map(item -> item.getAuthority())
                .collect(Collectors.toList());
            
            return ResponseEntity.ok(new AuthResponseDTO(
                jwtAccess,
                jwtRefresh,
                userDetails.getId(),
                userDetails.getEmail(),
                roles
                )
            );
        } catch(BadCredentialsException e){
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
            .body(new MessageResponse("Erreur: Identifiants invalides"));
        }

    }

    // POST - S'inscrire
    // api/auth/register
    @PostMapping("/register")
    public ResponseEntity<?> registerUser(@Valid @RequestBody RegisterDTO registerDTO){
        try{
            if(userDetailsService.existsByUsername(registerDTO.getUsername())){
            return ResponseEntity.badRequest()
            .body(new MessageResponse("Erreur: Ce nom d'utilisateur est déjà pris"));
            }

            if(userDetailsService.existsByEmail(registerDTO.getEmail())){
                return ResponseEntity.badRequest()
                .body(new MessageResponse("Erreur: Cet Email est déjà pris"));
            }

            // créer le nouvel utilisateur
            UtilisateurDTO utilisateurDTO = new UtilisateurDTO();
            utilisateurDTO.setNomUtilisateur(registerDTO.getUsername());
            utilisateurDTO.setEmail(registerDTO.getEmail());
            utilisateurDTO.setMotDePasse(passwordEncoder.encode(registerDTO.getPassword()));

            utilisateurService.creerUtilisateur(utilisateurDTO);
            return ResponseEntity.ok(new MessageResponse("Utilisateur enregistré avec succès"));

        }catch(Exception e){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
       
    }
    
}
