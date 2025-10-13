package com.galaxytasks.mappers;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;
import com.galaxytasks.model.Projet;
import com.galaxytasks.dto.ProjetCreateDTO;
import com.galaxytasks.dto.ProjetDTO;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface ProjetMapper{
    // from entity to DTO
    @Mapping(source = "idProjet", target = "idProjet")
    @Mapping(source = "intitule", target = "intitule")
    @Mapping(source = "descriptionProjet", target = "descriptionProjet")
    @Mapping(source = "dateCreation", target = "dateCreation")
    @Mapping(source = "proprietaire", target = "proprietaire")
    ProjetDTO toDto(Projet projet);
    
    @Mapping(source = "idProjet", target = "idProjet")
    @Mapping(source = "intitule", target = "intitule")
    @Mapping(source = "descriptionProjet", target = "descriptionProjet")
    @Mapping(source = "dateCreation", target = "dateCreation")
    @Mapping(target = "proprietaire", ignore = true)
    ProjetDTO CDTOtoDto(ProjetCreateDTO projetCDto);

    // from DTO to entity
    @Mapping(source = "idProjet", target = "idProjet")
    @Mapping(source = "intitule", target = "intitule")
    @Mapping(source = "descriptionProjet", target = "descriptionProjet")
    @Mapping(source = "dateCreation", target = "dateCreation")
    @Mapping(source = "proprietaire", target = "proprietaire")
    Projet toEntity(ProjetDTO projetDto);
}

