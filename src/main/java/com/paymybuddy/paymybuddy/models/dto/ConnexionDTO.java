package com.paymybuddy.paymybuddy.models.dto;

import com.paymybuddy.paymybuddy.models.dao.ConnexionDAO;
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
public class ConnexionDTO implements Serializable {

    private UtilisateurDTO demandeur;
    private UtilisateurDTO receveur;

    public ConnexionDTO(ConnexionDAO connexionDAO) {
        this.demandeur = new UtilisateurDTO(connexionDAO.getEmbeddedConnexion().getDemandeur());
        this.receveur = new UtilisateurDTO(connexionDAO.getEmbeddedConnexion().getReceveur());
    }
}
