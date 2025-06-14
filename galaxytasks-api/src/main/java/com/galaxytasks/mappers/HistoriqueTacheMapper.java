package com.galaxytasks.mappers;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;

import com.galaxytasks.dto.HistoriqueTacheDTO;
import com.galaxytasks.model.HistoriqueTache;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface HistoriqueTacheMapper {
    @Mapping(source = "id", target = "id")
    @Mapping(source = "tache.idTache", target = "idTache")
    @Mapping(source = "participant.idUtilisateur", target = "idParticipant")
    @Mapping(source = "action", target = "action")
    @Mapping(source = "ancienneValeur", target = "ancienneValeur")
    @Mapping(source = "nouvelleValeur", target = "nouvelleValeur")
    HistoriqueTacheDTO toDto(HistoriqueTache historiqueTache);

    @Mapping(source = "id", target = "id")
    @Mapping(target = "tache", ignore = true)
    @Mapping(target = "participant", ignore = true)
    @Mapping(source = "action", target = "action")
    @Mapping(source = "ancienneValeur", target = "ancienneValeur")
    @Mapping(source = "nouvelleValeur", target = "nouvelleValeur")
    @Mapping(target = "dateAction", ignore = true)
    HistoriqueTache toEntity(HistoriqueTacheDTO historiqueTacheDTO);
}
