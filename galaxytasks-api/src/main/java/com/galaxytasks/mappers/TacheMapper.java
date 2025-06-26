package com.galaxytasks.mappers;
import com.galaxytasks.model.Tache;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;

import com.galaxytasks.dto.TacheDTO;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface TacheMapper{
    @Mapping(source = "idTache", target = "idTache")
    @Mapping(source = "titre", target = "titre")
    @Mapping(source = "descriptionTache", target = "descriptionTache")
    @Mapping(source = "dateEcheance", target = "dateEcheance")
    @Mapping(source = "priorite", target = "priorite")
    @Mapping(source = "projet.idProjet", target = "idProjet")   
    @Mapping(source = "proprietaire.idUtilisateur", target = "idProprietaire")   
    TacheDTO toDto(Tache tache);

    @Mapping(source = "idTache", target = "idTache")
    @Mapping(source = "titre", target = "titre")
    @Mapping(source = "descriptionTache", target = "descriptionTache")
    @Mapping(target = "dateCreation",ignore = true)
    @Mapping(target = "dateModification", ignore = true)
    @Mapping(source = "dateEcheance", target = "dateEcheance")
    @Mapping(source = "priorite", target = "priorite")
    @Mapping(target = "projet", ignore = true)
    @Mapping(target = "proprietaire", ignore = true)   
    Tache toEntity(TacheDTO tacheDto);
}


