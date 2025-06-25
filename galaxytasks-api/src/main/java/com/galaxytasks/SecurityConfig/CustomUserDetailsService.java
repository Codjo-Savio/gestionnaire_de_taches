package com.galaxytasks.SecurityConfig;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.galaxytasks.model.ParticipationProjet;
import com.galaxytasks.model.Utilisateur;
import com.galaxytasks.repository.ParticipationProjetRepository;
import com.galaxytasks.repository.UtilisateurRepository;

@Service
public class CustomUserDetailsService implements UserDetailsService {
    @Autowired
    private UtilisateurRepository utilisateurRepository;

    @Autowired 
    private ParticipationProjetRepository participationProjetRepository;

    @Transactional(readOnly = true)
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException{
        // Charger l'utilisateur par email
        Utilisateur utilisateur = utilisateurRepository.findByEmail(email)
                .orElseThrow(() -> new UsernameNotFoundException("Utilisateur non trouvé: " + email));
        
        // Charger ses participations aux projets pour construire les rôles
        List<ParticipationProjet> participations = participationProjetRepository
                .findByUtilisateurIdUtilisateur(utilisateur.getIdUtilisateur());

        return new CustomUserDetails(utilisateur, participations);
    }

    // Méthode utilitaire pour charger par ID (utile pour JWT)
    public UserDetails loadUserById(int userId) throws UsernameNotFoundException {
        Utilisateur utilisateur = utilisateurRepository.findById(userId)
                .orElseThrow(() -> new UsernameNotFoundException("Utilisateur non trouvé: " + userId));
        
        List<ParticipationProjet> participations = participationProjetRepository
                .findByUtilisateurIdUtilisateur(userId);
        
        return new CustomUserDetails(utilisateur, participations);
    }
}
