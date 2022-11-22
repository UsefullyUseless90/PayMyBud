package com.paymybuddy.paymybuddy.controllers;

import com.paymybuddy.paymybuddy.models.dao.TransactionDAO;
import com.paymybuddy.paymybuddy.models.dto.TransactionDTO;
import com.paymybuddy.paymybuddy.services.ITransactionService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@CrossOrigin
@RestController
@RequestMapping("/user/transactions")
public class TransactionController {

    private static final Logger log = LogManager.getLogger(TransactionController.class);

    @Autowired
    private ITransactionService iTransactionService;



    @GetMapping
    public Iterable<TransactionDAO>getTransactions() {
        log.info("Recherche en cours, veuillez patienter...");
        log.info("Voici une liste de l'ensemble de vos transactions:" + iTransactionService.getTransactions());
        Iterable<TransactionDAO> transactions = iTransactionService.getTransactions();
        return transactions;
    }
    @RequestMapping("/{id}")
    public List<TransactionDAO> getTransactionParId(@PathVariable ("id") Integer idUtilisateur) {
        log.info("Recherche en cours, veuillez patienter...");
        log.info("Voici une liste de l'ensemble des transactions que vous avez émises:");
        List<TransactionDAO> transactionDAO = iTransactionService.getTransactionsById(idUtilisateur);
        return transactionDAO;
    }
    @RequestMapping("/destinataire/{id}")
    public Optional<TransactionDAO> getTransactionParDestinataire(@PathVariable ("id") Integer idUtilisateur) {
        log.info("Recherche en cours, veuillez patienter...");
        log.info("Voici une liste de l'ensemble des transactions que vous avez reçues:" + iTransactionService.getEchangeParDestinataire(idUtilisateur));
        Optional<TransactionDAO> transaction = iTransactionService.getEchangeParDestinataire(idUtilisateur);
        return transaction;
    }
    @PostMapping("/nouvelleTransaction/{id}")
    public ResponseEntity<TransactionDTO> creationTransaction(@RequestBody TransactionDTO transactionDTO,@PathVariable ("id") Integer idUtilisateur) throws Exception {
        log.info("Création d'une nouvelle transaction, veuillez patienter...");
        iTransactionService.creationTransaction(transactionDTO, idUtilisateur);
        ResponseEntity<TransactionDTO> creation = ResponseEntity.status(HttpStatus.CREATED).body(transactionDTO);
        log.info("Une nouvelle transaction a été crée" + creation);

        return creation;
    }
}
