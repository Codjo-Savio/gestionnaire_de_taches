package com.galaxytasks.mappers;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;

import com.galaxytasks.dto.ParticipationProjetDTO;
import com.galaxytasks.model.ParticipationProjet;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface ParticipationProjetMapper {
    @Mapping(source = "id.idProjet", target = "idProjet")
    @Mapping(source = "id.idUtilisateur", target = "idUtilisateur")
    @Mapping(source = "role", target = "role")
    ParticipationProjetDTO toDto(ParticipationProjet participationProjet);

    @Mapping(source = "idProjet", target = "id.idProjet")
    @Mapping(source = "idUtilisateur", target = "id.idUtilisateur")
    @Mapping(target = "projet", ignore = true)
    @Mapping(target = "utilisateur", ignore = true)
    @Mapping(source = "role", target = "role")
    @Mapping(target = "dateAjout", ignore = true)
    ParticipationProjet toEntity(ParticipationProjetDTO participationProjetDTO);
}
