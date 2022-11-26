package com.paymybuddy.paymybuddy.models.dto;

import com.paymybuddy.paymybuddy.models.dao.TransfertEntrant;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class TransfertEntrantDTO {

    private UtilisateurDTO reception;
    private CompteBancaireDTO envoi;
    private double montant;

    public TransfertEntrantDTO(TransfertEntrant transfertEntrant) {
        this.reception = new UtilisateurDTO(transfertEntrant.getReception());
        this.envoi = new CompteBancaireDTO(transfertEntrant.getEnvoi());
        this.montant = transfertEntrant.getMontant();
    }
}
