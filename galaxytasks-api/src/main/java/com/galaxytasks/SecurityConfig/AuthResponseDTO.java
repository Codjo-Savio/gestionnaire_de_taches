package com.galaxytasks.SecurityConfig;

import com.galaxytasks.dto.UtilisateurDTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AuthResponseDTO {
    private String accessToken;
    private String refreshToken;
    private Long expiresIn;
    private UtilisateurDTO utilisateur;
}
