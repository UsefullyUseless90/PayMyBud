package com.paymybuddy.paymybuddy.models.dao;

import com.paymybuddy.paymybuddy.models.CompteBancaire;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
@Getter
@Setter
public class ConnexionBanque implements Serializable {

    private UtilisateurDAO appCompte;
    private CompteBancaire bankCompte;

    public ConnexionBanque (UtilisateurDAO appCompte, CompteBancaire bankCompte){
        this.appCompte = appCompte;
        this.bankCompte = bankCompte;
    }

}
