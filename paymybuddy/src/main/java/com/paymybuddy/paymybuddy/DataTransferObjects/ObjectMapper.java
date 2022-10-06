package com.paymybuddy.paymybuddy.DataTransferObjects;

import com.paymybuddy.paymybuddy.models.dao.TransactionDAO;
import com.paymybuddy.paymybuddy.models.dao.UtilisateurDAO;
import com.paymybuddy.paymybuddy.models.dto.TransactionDTO;
import org.springframework.stereotype.Component;

import java.util.Date;

@Component
public class ObjectMapper {

    public TransactionDTO toDto(TransactionDAO transactionDAO, UtilisateurDAO utilisateurDAO) {

        // ********** Partie EmbeddedTransaction ***********//
        UtilisateurDAO eTransactionEmetteur = new UtilisateurDAO(utilisateurDAO.getIdUtilisateur(), utilisateurDAO.getNom(), utilisateurDAO.getPrenom(), utilisateurDAO.getAdresseEmail(), utilisateurDAO.getDateDeNaissance(), utilisateurDAO.getFondsDisponibles());
        UtilisateurDAO eTransactionDestinataire = new UtilisateurDAO(utilisateurDAO.getIdUtilisateur(), utilisateurDAO.getNom(), utilisateurDAO.getPrenom(), utilisateurDAO.getAdresseEmail(), utilisateurDAO.getDateDeNaissance(), utilisateurDAO.getFondsDisponibles());
        Date transactionDate = transactionDAO.getEmbeddedTransaction().getDate();

        //*********** Partie Transaction ****************//

        String transactionDescription = transactionDAO.getDescription();
        Integer transactionMontant = transactionDAO.getMontant();

        return new TransactionDTO(transactionDAO);
    }
}
   /* public TransactionDAO transfertObjet(TransactionDTO creationTransactionDTO){
        return new TransactionDAO(creationTransactionDTO.getTransactionDescription(),creationTransactionDTO.getTransactionMontant(),creationTransactionDTO.getTransactionDate(),
                creationTransactionDTO.getETransactionEmetteur(),creationTransactionDTO.getETransactionDestinataire());
    */

