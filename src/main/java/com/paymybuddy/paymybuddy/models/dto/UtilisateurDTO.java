package com.paymybuddy.paymybuddy.models.dto;

import com.paymybuddy.paymybuddy.models.dao.UtilisateurDAO;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;


@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class UtilisateurDTO implements Serializable {

    private Integer idUtilisateur;

    private String nomPrenom;


    public UtilisateurDTO(UtilisateurDAO utilisateurDAO) {
        this.idUtilisateur = utilisateurDAO.getIdUtilisateur();
        this.nomPrenom = utilisateurDAO.getNom().concat(" ").concat(utilisateurDAO.getPrenom());
    }

}
