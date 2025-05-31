package com.galaxytasks.mappers;

import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;
import com.galaxytasks.model.Projet;
import com.galaxytasks.dto.ProjetDTO;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface ProjetMapper {
    ProjetDTO toDto(Projet projet);

    Projet toEntity(ProjetDTO projetDto);
}
