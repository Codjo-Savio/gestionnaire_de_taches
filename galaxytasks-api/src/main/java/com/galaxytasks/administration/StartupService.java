package com.galaxytasks.administration;

import org.springframework.stereotype.Service;

import com.galaxytasks.services.UtilisateurService;

import jakarta.annotation.PostConstruct;

@Service
public class StartupService {

    private final UtilisateurService utilisateurService;

    public StartupService(UtilisateurService utilisateurService, AdminProperties adminProperties) {
        this.utilisateurService = utilisateurService;
    }

    @PostConstruct
    public void initSuperAdmin() {
        utilisateurService.initSuperUtilisateurSiInexistant();;
    }
}

