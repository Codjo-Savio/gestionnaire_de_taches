package com.galaxytasks.services;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.galaxytasks.dto.CommentaireDTO;
import com.galaxytasks.mappers.CommentaireMapper;
import com.galaxytasks.model.Commentaire;
import com.galaxytasks.model.Tache;
import com.galaxytasks.model.Utilisateur;
import com.galaxytasks.repository.CommentaireRepository;
import com.galaxytasks.repository.TacheRepository;
import com.galaxytasks.repository.UtilisateurRepository;

@Service
public class CommentaireService {
   private CommentaireRepository commentaireRepository;
   private UtilisateurRepository utilisateurRepository;
   private TacheRepository tacheRepository;
   private CommentaireMapper mapper;
   
   @Autowired // Instanciation automatique
   public CommentaireService(CommentaireRepository commentaireRepository,
                            UtilisateurRepository utilisateurRepository,
                            TacheRepository tacheRepository,
                            CommentaireMapper mapper){
        this.commentaireRepository = commentaireRepository;
        this.utilisateurRepository = utilisateurRepository;
        this.tacheRepository = tacheRepository;
        this.mapper = mapper;
    }

    // créer un nouveau commentaire
    public CommentaireDTO create(CommentaireDTO commentaire){
        // On cherche l'utilisateur
        Utilisateur utilisateur = utilisateurRepository.findById(commentaire.getIdUtilisateur())
        .orElseThrow(()-> new RuntimeException("Utilisateur non trouvé"));

        // On cherche la tâche
        Tache tache = tacheRepository.findById(commentaire.getIdTache())
        .orElseThrow(()-> new RuntimeException("Tâche non trouvée"));

        // On met à jour la l'utilisateur et la tâche s'ils sont trouvés
        Commentaire created = mapper.toEntity(commentaire);
        created.setUtilisateur(utilisateur);
        created.setTache(tache);

        return mapper.toDto(commentaireRepository.save(created));
    }

    // récupérer tous les commentaires
    public List<CommentaireDTO> getAllCommentaires(){
        return commentaireRepository.findAll()
        .stream()
        .map(mapper::toDto)
        .collect(Collectors.toList());
    }

    // récupérer une liste de commentaires par tâche
    public List<CommentaireDTO> getByTache(Integer idTache){
        // On cherche la tâche
        Tache tache = tacheRepository.findById(idTache)
        .orElseThrow(()-> new RuntimeException("Tâche non trouvée"));

        return commentaireRepository.findByTache(tache)
        .stream()
        .map(mapper::toDto)
        .collect(Collectors.toList());
    }

    // récupérer une liste de commentaires par utilisateur
    public List<CommentaireDTO> getByUser(Integer idUtilisateur){
        // On cherche l'utilisateur
        Utilisateur utilisateur = utilisateurRepository.findById(idUtilisateur)
        .orElseThrow(()-> new RuntimeException("Utilisateur non trouvé"));

        return commentaireRepository.findByUtilisateur(utilisateur)
        .stream()
        .map(mapper::toDto)
        .collect(Collectors.toList());
    }

    // Mise à jour d'un commentaire
    // créer un nouveau commentaire
    public CommentaireDTO updateById(Integer idCommentaire, CommentaireDTO commentaire){
        // On cherche le commentaire
        Commentaire comment = commentaireRepository.findById(idCommentaire)
        .orElseThrow(()-> new RuntimeException("Commentaire non trouvé"));

        // On met à jour son contenu
        comment.setContenu(commentaire.getContenu());
        return mapper.toDto(commentaireRepository.save(comment));
    }

    // suppression d'un commentaire
    public boolean deleteById(Integer idCommentaire){
        if(commentaireRepository.existsById(idCommentaire)){
            commentaireRepository.deleteById(idCommentaire);
            return true;
        }
        else{
            return false;
        }
    }
}
