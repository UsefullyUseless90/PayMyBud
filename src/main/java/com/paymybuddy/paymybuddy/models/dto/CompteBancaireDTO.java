package com.paymybuddy.paymybuddy.models.dto;

import com.paymybuddy.paymybuddy.models.CompteBancaire;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class CompteBancaireDTO implements Serializable {

    private String idCompte;
    private String banque;

    public CompteBancaireDTO(CompteBancaire compteBancaire) {
        this.idCompte = compteBancaire.getIdCompte();
        this.banque = compteBancaire.getBanque();
    }
}
