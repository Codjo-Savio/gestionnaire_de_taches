package com.galaxytasks.dto;

import com.galaxytasks.model.Utilisateur.Statut;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UtilisateurDTO {
    @NotNull
    private Integer idUtilisateur;

    @NotBlank
    @Size(min = 2, max = 50)
    private String nomUtilisateur;

    @NotBlank
    @Email
    private String email;

    @Size(min = 8, message = "Le mot de passe doit contenir au moins 8 caractères")
    private String motDePasse;

    private Statut statut;
}
