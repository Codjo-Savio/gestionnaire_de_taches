package com.galaxytasks.security;

import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.galaxytasks.model.ParticipationProjet;
import com.galaxytasks.model.Utilisateur;

public class CustomUserDetails implements UserDetails{
    
    private final Utilisateur utilisateur;
    private final Collection<? extends GrantedAuthority> authorities;
    
    public CustomUserDetails(Utilisateur utilisateur, List<ParticipationProjet> participations) {
        this.utilisateur = utilisateur;
        this.authorities = buildAuthorities(participations);
    }
    
    // Construit les autorités basées sur les rôles dans les projets
    private Collection<? extends GrantedAuthority> buildAuthorities(List<ParticipationProjet> participations) {
        Set<GrantedAuthority> authorities = new HashSet<>();
        
        // Ajouter un rôle de base
        authorities.add(new SimpleGrantedAuthority("ROLE_USER"));
        authorities.add(new SimpleGrantedAuthority("ROLE_" + utilisateur.getStatut().name()));
        
        // Ajouter les rôles spécifiques par projet
        for (ParticipationProjet participation : participations) {
            String role = "ROLE_" + participation.getRole();
            authorities.add(new SimpleGrantedAuthority(role));
            // Optionnel : ajouter des permissions par projet
            String projectRole = "PROJECT_" + participation.getId().getIdProjet() + "_" + participation.getRole().name();
            authorities.add(new SimpleGrantedAuthority(projectRole));
        }
        
        return authorities;
    }
    
    // Implémentation des méthodes UserDetails
    
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return authorities;
    }
    
    @Override
    public String getPassword() {
        return utilisateur.getMotDePasse();
    }
    
    @Override
    public String getUsername() {
        return utilisateur.getEmail(); // Utilise l'email comme identifiant
    }

    @Override
    public boolean isAccountNonExpired() {
        return true; // Tu peux ajouter une logique métier ici si nécessaire
    }
    
    @Override
    public boolean isAccountNonLocked() {
        return true; // Idem, pour gérer les comptes bloqués
    }
    
    @Override
    public boolean isCredentialsNonExpired() {
        return true; // Pour gérer l'expiration des mots de passe
    }
    
    @Override
    public boolean isEnabled() {
        return true; // Pour activer/désactiver des comptes
    }
    
    // Méthodes utilitaires pour accéder aux données de l'utilisateur
    
    public Utilisateur getUtilisateur() {
        return utilisateur;
    }
    
    public int getId() {
        return utilisateur.getIdUtilisateur();
    }
    
    public String getNomUtilisateur() {
        return utilisateur.getNomUtilisateur();
    }
    
    public String getEmail() {
        return utilisateur.getEmail();
    }
    
    // Méthodes utilitaires pour vérifier les permissions
    public boolean hasStatus(Utilisateur.Statut statut) {
        String statutUtilisateur = "ROLE_" + statut.name();
        return authorities.stream()
                .anyMatch(auth -> auth.getAuthority().equals(statutUtilisateur));
    }
    
    public boolean hasRoleInProject(Long projectId,ParticipationProjet.Role role) {
        String projectRole = "PROJECT_" + projectId + "_" + role;
        return authorities.stream()
                .anyMatch(auth -> auth.getAuthority().equals(projectRole));
    }
    
    public boolean isProprietaireOf(Long projectId) {
        return hasRoleInProject(projectId, ParticipationProjet.Role.PROPRIETAIRE);
    }
    
    public boolean isAdminOf(Long projectId) {
        return hasRoleInProject(projectId, ParticipationProjet.Role.ADMIN);
    }
    
     public boolean isMembreOf(Long projectId) {
        return hasRoleInProject(projectId, ParticipationProjet.Role.MEMBRE);
    }
    
    public boolean canManageProject(Long projectId) {
        return isProprietaireOf(projectId) || isAdminOf(projectId);
    }
    
    public List<Long> getAccessibleProjectIds() {
        return authorities.stream()
                .map(GrantedAuthority::getAuthority)
                .filter(auth -> auth.startsWith("PROJECT_"))
                .map(auth -> {
                    String[] parts = auth.split("_");
                    return Long.parseLong(parts[1]);
                })
                .distinct()
                .collect(Collectors.toList());
    }
}