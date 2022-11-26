package com.paymybuddy.paymybuddy.models.dao;

import com.paymybuddy.paymybuddy.models.CompteBancaire;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

@Getter
@Setter

public class TransfertEntrant implements Serializable {

    private UtilisateurDAO reception;
    private CompteBancaire envoi;
    private double montant;

    public TransfertEntrant (UtilisateurDAO reception, CompteBancaire envoi, double montant){
        this.reception = reception;
        this.envoi = envoi;
        this.montant = montant;
    }

}
