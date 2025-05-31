package com.galaxytasks.mappers;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;
import com.galaxytasks.model.Utilisateur;
import com.galaxytasks.dto.UtilisateurDTO;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface UtilisateurMapper {

    // from Entity to DTO
    @Mapping(source = "idUtilisateur", target = "idUtilisateur")
    @Mapping(source = "nomUtilisateur", target = "nomUtilisateur")
    @Mapping(source = "email", target = "email")
    UtilisateurDTO toDto(Utilisateur utilisateur);

    // from DTO to Entity (on ignore le mot de passe ici)
    @Mapping(source = "idUtilisateur", target = "idUtilisateur")
    @Mapping(source = "nomUtilisateur", target = "nomUtilisateur")
    @Mapping(source = "email", target = "email")
    @Mapping(target = "motDePasse", ignore = true) // sécurité
    Utilisateur toEntity(UtilisateurDTO dto);
}