package com.galaxytasks.mappers;
import com.galaxytasks.model.Tache;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;

import com.galaxytasks.dto.TacheDTO;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface TacheMapper{
    @Mapping(source = "titre", target = "titre")
    @Mapping(source = "descriptionTache", target = "descriptionTache")
    @Mapping(source = "dateCreation", target = "dateCreation")
    @Mapping(source = "dateModification", target = "dateModification")
    @Mapping(source = "dateEcheance", target = "dateEcheance")
    @Mapping(source = "priorite", target = "priorite")
    @Mapping(source = "projet", target = "projet")   
    TacheDTO toDto(Tache tache);

    @Mapping(source = "titre", target = "titre")
    @Mapping(source = "descriptionTache", target = "descriptionTache")
    @Mapping(source = "dateCreation", target = "dateCreation")
    @Mapping(source = "dateModification", target = "dateModification")
    @Mapping(source = "dateEcheance", target = "dateEcheance")
    @Mapping(source = "priorite", target = "priorite")
    @Mapping(source = "projet", target = "projet")   
    Tache toEntity(TacheDTO tacheDto);
}

