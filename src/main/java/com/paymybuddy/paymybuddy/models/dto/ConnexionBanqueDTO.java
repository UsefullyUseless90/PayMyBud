package com.paymybuddy.paymybuddy.models.dto;

import com.paymybuddy.paymybuddy.models.dao.ConnexionBanque;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ConnexionBanqueDTO {

    private UtilisateurDTO appCompte;
    private CompteBancaireDTO banqueCompte;

    public ConnexionBanqueDTO(ConnexionBanque connexionBanque) {
        this.appCompte = new UtilisateurDTO(connexionBanque.getAppCompte());
        this.banqueCompte = new CompteBancaireDTO(connexionBanque.getBankCompte());
    }

}
