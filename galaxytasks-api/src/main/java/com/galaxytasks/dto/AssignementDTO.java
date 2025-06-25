package com.galaxytasks.dto;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AssignementDTO {
    @NotNull
    private Integer idTache;

    @NotNull
    private Integer idParticipant;
}
