package com.paymybuddy.paymybuddy.models;

import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Entity
@NoArgsConstructor
public class Transaction {
    @Id
    private Date date;
    @Id
    private int destinataire;
    @Id
    private int emetteur;
    private String description;
    private int montant;


    @OneToMany(targetEntity = Utilisateur.class, cascade = CascadeType.ALL, fetch = FetchType.LAZY, orphanRemoval = false)
    private List<Utilisateur> transfers;

    public Transaction(String description, int montant, int destinataire, int emetteur, Date date) {
        this.description = description;
        this.montant = montant;
        this.destinataire = destinataire;
        this.emetteur = emetteur;
        this.date = date;
    }
}
