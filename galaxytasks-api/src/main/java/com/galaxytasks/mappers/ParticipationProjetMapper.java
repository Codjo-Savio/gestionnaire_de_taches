package com.galaxytasks.mappers;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;

import com.galaxytasks.dto.ParticipationProjetDTO;
import com.galaxytasks.model.ParticipationProjet;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface ParticipationProjetMapper {
    @Mapping(target = "idProjet", ignore = true)
    @Mapping(target = "intitule", ignore = true)
    @Mapping(target = "idUtilisateur", ignore = true)
    @Mapping(target = "nomUtilisateur", ignore = true)
    @Mapping(source = "role", target = "role")
    ParticipationProjetDTO toDto(ParticipationProjet participationProjet);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "idProjet", ignore = true)
    @Mapping(target = "idUtilisateur", ignore = true)
    @Mapping(source = "role", target = "role")
    @Mapping(target = "dateAjout", ignore = true)
    ParticipationProjet toEntity(ParticipationProjetDTO participationProjetDTO);
}
