package com.paymybuddy.paymybuddy.services;

import com.paymybuddy.paymybuddy.models.dao.UtilisateurDAO;
import com.paymybuddy.paymybuddy.models.dto.TransactionDTO;
import com.paymybuddy.paymybuddy.DataTransferObjects.ObjectMapper;
import com.paymybuddy.paymybuddy.models.dao.EmbeddedTransaction;
import com.paymybuddy.paymybuddy.models.dao.TransactionDAO;
import com.paymybuddy.paymybuddy.repositories.TransactionRepository;
import com.paymybuddy.paymybuddy.repositories.UtilisateurRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class TransactionService implements ITransactionService {

    @Autowired
    TransactionRepository transactionRepository;
    @Autowired
    UtilisateurRepository utilisateurRepository;
    @Autowired
    TransactionDAO transactionDAO;
    @Autowired
    UtilisateurDAO utilisateurDAO;
    @Autowired
    EmbeddedTransaction embeddedTransaction;
    @Autowired
    ObjectMapper mapper;

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
    public TransactionDTO creationTransaction(TransactionDTO transactionDTO) {

        UtilisateurDAO emetteurDAO =  utilisateurRepository.findById(transactionDTO.getEmetteur().getIdUtilisateur()).orElse(null);
        UtilisateurDAO destinataireDAO =  utilisateurRepository.findById(transactionDTO.getDestinataire().getIdUtilisateur()).orElse(null);

        TransactionDAO transactionDAO = new TransactionDAO(transactionDTO.getDescription(), transactionDTO.getMontant(), transactionDTO.getDate(), emetteurDAO, destinataireDAO);

        transactionDAO = transactionRepository.save(transactionDAO);

        return new TransactionDTO(transactionDAO);
    }

   /* public CreationTransactionDTO creationTransaction(CreationTransactionDTO creationTransactionDTO) {
        cTransactionDto.setNomEmetteur(cTransactionDto.getNomEmetteur());
        cTransactionDto.setNomDestinataire(cTransactionDto.getNomDestinataire());
        cTransactionDto.setTransactionDescription(cTransactionDto.getTransactionDescription());
        cTransactionDto.setTransactionDate(cTransactionDto.getTransactionDate());
        cTransactionDto.setTransactionMontant(cTransactionDto.getTransactionMontant());
        Transaction transaction = mapper.transfertObjet(cTransactionDto);
        transactionRepository.save(transaction);
        return creationTransactionDTO;
    }
    */
    public List<TransactionDAO> getTransactionsById(Integer idUtilisateur){
        List<TransactionDAO> listeTransactionDAO = new ArrayList<>();
        UtilisateurDAO utilisateurDAO = utilisateurRepository.findById(idUtilisateur).orElse(null);
        if(utilisateurDAO != null) {
            listeTransactionDAO = transactionRepository.getMyTransactions(utilisateurDAO);
        }
        return listeTransactionDAO;

    }

}
