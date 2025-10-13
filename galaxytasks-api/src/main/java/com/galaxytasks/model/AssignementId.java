package com.galaxytasks.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import jakarta.persistence.Embeddable;

@Embeddable
@Data
@NoArgsConstructor
@AllArgsConstructor
public class AssignementId implements java.io.Serializable {
    private Integer idTache;
    private Integer idParticipant;
}