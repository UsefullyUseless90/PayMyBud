package com.paymybuddy.paymybuddy.services;

import com.paymybuddy.paymybuddy.models.Commissions_pmb;
import com.paymybuddy.paymybuddy.models.dao.EmbeddedTransaction;
import com.paymybuddy.paymybuddy.models.dao.TransactionDAO;
import com.paymybuddy.paymybuddy.models.dao.UtilisateurDAO;
import com.paymybuddy.paymybuddy.models.dto.TransactionDTO;
import com.paymybuddy.paymybuddy.repositories.CommissionsRepository;
import com.paymybuddy.paymybuddy.repositories.TransactionRepository;
import com.paymybuddy.paymybuddy.repositories.UtilisateurRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Random;

@Service
public class TransactionService implements ITransactionService {

    @Autowired
    TransactionRepository transactionRepository;
    @Autowired
    UtilisateurRepository utilisateurRepository;
    @Autowired
    CommissionsRepository commissionsRepository;
    @Autowired
    TransactionDAO transactionDAO;
    @Autowired
    UtilisateurDAO utilisateurDAO;
    @Autowired
    EmbeddedTransaction embeddedTransaction;


    public Iterable<TransactionDAO> getTransactions(){
        Iterable<TransactionDAO> transactions = transactionRepository.findAll();
        return transactions;
    }
    public Optional<TransactionDAO> getEchangeParEmetteur(Integer idUtilisateur){
        Optional<TransactionDAO> echange = transactionRepository.findById(transactionDAO.getEmbeddedTransaction().getEmetteur().getIdUtilisateur());
        return echange;
    }
    public Optional<TransactionDAO> getEchangeParDestinataire(Integer idUtilisateur){
        Optional<TransactionDAO> echange = transactionRepository.findById(transactionDAO.getEmbeddedTransaction().getDestinataire().getIdUtilisateur());
        return echange;
    }

    @Override
    @Transactional
    public TransactionDTO creationTransaction(TransactionDTO transactionDTO, Integer idUtilisateur) throws Exception {

        UtilisateurDAO emetteur = utilisateurRepository.findById(idUtilisateur).orElse(null);

        UtilisateurDAO emetteurDAO =  emetteur;
        UtilisateurDAO destinataireDAO =  utilisateurRepository.findById(transactionDTO.getDestinataire().getIdUtilisateur()).orElse(null);

        TransactionDAO transactionDAO = new TransactionDAO(transactionDTO.getDescription(), transactionDTO.getMontant(), transactionDTO.getDate(), emetteurDAO, destinataireDAO);

        Commissions_pmb cPmb = new Commissions_pmb();

        if(transactionDTO.getMontant() > emetteurDAO.getFondsDisponibles()){
            throw new Exception("Vous ne disposez pas de suffisamment de fonds pour effectuer cette transaction");
        }else {
            //Calcul commission//
            cPmb.setMontant(transactionDTO.getMontant()*0.5);
            cPmb.setNumeroTransaction((new Random().nextInt()));
            emetteurDAO.setFondsDisponibles(emetteurDAO.getFondsDisponibles() - transactionDTO.getMontant() - cPmb.getMontant());
            destinataireDAO.setFondsDisponibles(destinataireDAO.getFondsDisponibles() + transactionDTO.getMontant());
        }
        transactionDAO = transactionRepository.save(transactionDAO);
        cPmb = commissionsRepository.save(cPmb);

        return new TransactionDTO(transactionDAO);
    }
    @Transactional
    public List<TransactionDAO> getTransactionsById(Integer idUtilisateur){
        List<TransactionDAO> listeTransactionDAO = new ArrayList<>();
        UtilisateurDAO utilisateurDAO = utilisateurRepository.findById(idUtilisateur).orElse(null);
        if(utilisateurDAO != null) {
            listeTransactionDAO = transactionRepository.getMyTransactions(utilisateurDAO);
        }
        return listeTransactionDAO;

    }

}
