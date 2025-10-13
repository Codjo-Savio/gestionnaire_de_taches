package com.galaxytasks.mappers;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;

import com.galaxytasks.dto.CommentaireDTO;
import com.galaxytasks.model.Commentaire;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface CommentaireMapper {
    @Mapping(source = "idCommentaire", target = "idCommentaire")
    @Mapping(source = "contenu", target = "contenu")
    @Mapping(source = "utilisateur.idUtilisateur", target = "idUtilisateur")
    @Mapping(source = "tache.idTache", target = "idTache")
    CommentaireDTO toDto(Commentaire commentaire);

    @Mapping(source = "idCommentaire", target = "idCommentaire")
    @Mapping(source = "contenu", target = "contenu")
    @Mapping(target = "dateCommentaire", ignore = true)
    @Mapping(target = "utilisateur", ignore = true)
    @Mapping(target = "tache", ignore = true)
    Commentaire toEntity(CommentaireDTO commentaireDTO);
}
