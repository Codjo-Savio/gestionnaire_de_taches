package com.galaxytasks.controllers;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.galaxytasks.dto.AuthResponseDTO;
import com.galaxytasks.dto.LoginRequestDTO;
import com.galaxytasks.dto.RegisterDTO;
import com.galaxytasks.dto.UtilisateurDTO;
import com.galaxytasks.exceptions.MessageResponse;
import com.galaxytasks.mappers.UtilisateurMapper;
import com.galaxytasks.model.Utilisateur;
import com.galaxytasks.repository.UtilisateurRepository;
import com.galaxytasks.security.CustomUserDetails;
import com.galaxytasks.services.CustomUserDetailsService;
import com.galaxytasks.services.JwtService;
import com.galaxytasks.services.UtilisateurService;

import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
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
    UtilisateurRepository utilisateurRepository;

    @Autowired
    UtilisateurMapper mapper;

    @Autowired
    private JwtService jwtService;

    // POST - Se connecter à son compte
    // api/auth/login
    @PostMapping("/login")
    public  ResponseEntity<?> authenticateUser(@Valid @RequestBody LoginRequestDTO loginRequestDTO, HttpServletResponse response){
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

            // Stocker le jwt dans un cookie
            Cookie jwtAccessCookie = new Cookie("jwt", jwtAccess);
            jwtAccessCookie.setHttpOnly(true);
            jwtAccessCookie.setSecure(true); // à activer en prod https
            jwtAccessCookie.setPath("/");
            jwtAccessCookie.setMaxAge(3600);
            response.addCookie(jwtAccessCookie);

            String jwtRefresh = "";
            if(loginRequestDTO.isRememberMe()){
                jwtRefresh = jwtService.generateRefreshToken(userDetails);
                // Stocker le jwt dans un cookie
                Cookie jwtRefreshCookie = new Cookie("jwt", jwtAccess);
                jwtRefreshCookie.setHttpOnly(true);
                jwtRefreshCookie.setSecure(true); // à activer en prod https
                jwtRefreshCookie.setPath("/");
                jwtRefreshCookie.setMaxAge(3600);
                response.addCookie(jwtRefreshCookie);
            }

            List<String> roles = userDetails.getAuthorities()
                .stream()
                .map(item -> item.getAuthority())
                .collect(Collectors.toList());
            
            return ResponseEntity.ok(new AuthResponseDTO(
                "",
                jwtRefresh,
                userDetails.getId(),
                userDetails.getEmail(),
                userDetails.getNomUtilisateur(),
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
            utilisateurDTO.setMotDePasse(registerDTO.getPassword());

            utilisateurService.creerUtilisateur(utilisateurDTO);
            return ResponseEntity.ok(new MessageResponse("Utilisateur enregistré avec succès"));

        }catch(Exception e){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
       
    }


    // GET - récupérer le nom d'utilisateur
    @GetMapping("/profile")
    public ResponseEntity<?> getUsername(HttpServletRequest request){
        String token = jwtService.extractTokenFromCookie(request);
        if(token == null){
            return ResponseEntity.status((HttpStatus.UNAUTHORIZED))
            .body(new MessageResponse("Utilisateur non connecté"));
        }
        
        String email = jwtService.extractUsername(token);
        Utilisateur utilisateur =utilisateurRepository.findByEmail(email)
        .orElseThrow(()-> new RuntimeException("Utilisateur non trouvé"));

        return ResponseEntity.ok(Map.of(
            "id", utilisateur.getIdUtilisateur(),
            "nomUtilisateur", utilisateur.getNomUtilisateur(),
            "email", utilisateur.getEmail()
        ));
    }
}
