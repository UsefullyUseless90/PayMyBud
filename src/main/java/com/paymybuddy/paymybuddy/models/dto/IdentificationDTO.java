package com.paymybuddy.paymybuddy.models.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class IdentificationDTO {

    private String idUtilisateur;
    private String token;

    public IdentificationDTO(String idUtilisateur, String token) {
        this.idUtilisateur = idUtilisateur;
        this.token = token;
    }
}
