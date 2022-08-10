package com.paymybuddy.paymybuddy.models;

import lombok.NoArgsConstructor;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;

@Entity
@NoArgsConstructor
public class Transaction{

    @EmbeddedId
    private EmbeddedTransaction embeddedTransaction;


    private String description;
    private Integer montant;



    public Transaction(String description, int montant) {
        this.description = description;
        this.montant = montant;

        this.embeddedTransaction = new EmbeddedTransaction(embeddedTransaction.getDate(),embeddedTransaction.getEmetteur(),embeddedTransaction.getDestinataire());
    }
}
