package com.galaxytasks.mappers;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;
import com.galaxytasks.model.Projet;
import com.galaxytasks.dto.ProjetDTO;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface ProjetMapper {
    // from entity to DTO
    @Mapping(source = "intitule", target = "intitule")
    @Mapping(source = "descriptionProjet", target = "descriptionProjet")
    @Mapping(source = "dateCreation", target = "dateCreation")
    ProjetDTO toDto(Projet projet);

    // from DTO to entity
    @Mapping(source = "intitule", target = "intitule")
    @Mapping(source = "descriptionProjet", target = "descriptionProjet")
    @Mapping(source = "dateCreation", target = "dateCreation")
    @Mapping(target = "idProjet", ignore = true)
    @Mapping(target = "proprietaire", ignore = true)
    Projet toEntity(ProjetDTO projetDto);
}
