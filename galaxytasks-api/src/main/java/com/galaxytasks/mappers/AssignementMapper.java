package com.galaxytasks.mappers;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;

import com.galaxytasks.dto.AssignementDTO;
import com.galaxytasks.model.Assignement;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface AssignementMapper {
    @Mapping(source = "tache.idTache", target = "idTache")
    @Mapping(source = "participant.idUtilisateur", target = "idParticipant")
    AssignementDTO toDto(Assignement assignement);

    @Mapping(target = "tache", ignore = true)
    @Mapping(target = "participant", ignore = true)
    Assignement toEntity(AssignementDTO assignementDTO);
}
