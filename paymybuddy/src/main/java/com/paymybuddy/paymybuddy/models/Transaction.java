package com.paymybuddy.paymybuddy.models;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.stereotype.Component;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Table;

@Component
@Entity
@Table(name = "transactions")
@NoArgsConstructor
@Getter
@Setter
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
