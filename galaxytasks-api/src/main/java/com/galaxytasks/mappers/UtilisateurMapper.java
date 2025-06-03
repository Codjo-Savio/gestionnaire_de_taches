package com.galaxytasks.mappers;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;
import com.galaxytasks.model.Utilisateur;
import com.galaxytasks.dto.UtilisateurCreateDTO;
import com.galaxytasks.dto.UtilisateurDTO;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface UtilisateurMapper {

    // from Entity to DTO
    @Mapping(source = "idUtilisateur", target = "idUtilisateur")
    @Mapping(source = "nomUtilisateur", target = "nomUtilisateur")
    @Mapping(source = "email", target = "email")
    @Mapping(source = "motDePasse", target = "motDePasse") 
    UtilisateurDTO toDto(Utilisateur utilisateur);

    // from DTO to CDTO
    @Mapping(source = "idUtilisateur", target = "idUtilisateur")
    @Mapping(source = "nomUtilisateur", target = "nomUtilisateur")
    @Mapping(source = "email", target = "email")
    UtilisateurCreateDTO toCDto(UtilisateurDTO utilisateurDTO);

    // from DTO to Entity (on ignore le mot de passe ici)
    @Mapping(source = "idUtilisateur", target = "idUtilisateur")
    @Mapping(source = "nomUtilisateur", target = "nomUtilisateur")
    @Mapping(source = "email", target = "email")
    @Mapping(source = "motDePasse", target = "motDePasse") // sécurité
    Utilisateur toEntity(UtilisateurDTO dto);

}