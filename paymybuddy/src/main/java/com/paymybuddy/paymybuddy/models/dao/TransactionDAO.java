package com.paymybuddy.paymybuddy.models.dao;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.stereotype.Component;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Component
@Entity(name = "Transaction")
@Table(name = "transactions")
@NoArgsConstructor
@Getter
@Setter
public class TransactionDAO implements Serializable {


    @EmbeddedId
    private EmbeddedTransaction embeddedTransaction;

    @Column(name = "description")
    private String description;
    @Column(name = "montant")
    private Integer montant;


    public TransactionDAO(String description, int montant, Date date, UtilisateurDAO emetteur, UtilisateurDAO destinataire) {
        this.description = description;
        this.montant = montant;

        this.embeddedTransaction = new EmbeddedTransaction(date, emetteur, destinataire);
    }
}
