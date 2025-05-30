package com.galaxytasks.mappers;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

import com.galaxytasks.model.Utilisateur;
import com.galaxytasks.dto.UtilisateurDTO;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface UtilisateurMapper{
    UtilisateurDTO toDto(Utilisateur utilisateur);

    Utilisateur toEntity(UtilisateurDTO dto);
}
