package com.paymybuddy.paymybuddy.models.dto;

import com.paymybuddy.paymybuddy.models.dao.TransactionDAO;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;
import java.util.Date;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class TransactionDTO implements Serializable {

    private UtilisateurDTO emetteur;
    private UtilisateurDTO destinataire;
    private String description;
    private Date date;
    private Integer montant;

    public TransactionDTO(TransactionDAO transactionDAO) {
        this.emetteur = new UtilisateurDTO(transactionDAO.getEmbeddedTransaction().getEmetteur());
        this.destinataire = new UtilisateurDTO(transactionDAO.getEmbeddedTransaction().getDestinataire());
        this.description = transactionDAO.getDescription();
        this.date = transactionDAO.getEmbeddedTransaction().getDate();
        this.montant = transactionDAO.getMontant();
    }

}
