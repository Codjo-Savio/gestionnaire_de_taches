package com.galaxytasks.administration;

import org.springframework.stereotype.Service;

import com.galaxytasks.services.UtilisateurService;

import jakarta.annotation.PostConstruct;

@Service
public class StartupService {

    private final UtilisateurService utilisateurService;
    //private final AdminProperties adminProperties;

    public StartupService(UtilisateurService utilisateurService) {
        this.utilisateurService = utilisateurService;
        //this.adminProperties = adminProperties;
    }

    @PostConstruct
    public void initSuperAdmin() {
        utilisateurService.initSuperUtilisateurSiInexistant();
    }
}

