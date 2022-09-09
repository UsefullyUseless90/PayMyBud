package com.paymybuddy.paymybuddy.controllers;

import com.paymybuddy.paymybuddy.models.Transaction;
import com.paymybuddy.paymybuddy.models.Utilisateur;
import com.paymybuddy.paymybuddy.services.ITransactionService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.json.JSONException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.Optional;
@CrossOrigin
@RestController

public class TransactionController {

    private static final Logger log = LogManager.getLogger(TransactionController.class);

    @Autowired
    ITransactionService iTransactionService;

    @CrossOrigin
    @GetMapping
    @RequestMapping("/transaction")
    public Iterable<Transaction>getTransactions() {
        log.info("Recherche en cours, veuillez patienter...");
        log.info("Voici une liste de l'ensemble de vos transactions:" + iTransactionService.getTransactions());
        Iterable<Transaction> transactions = iTransactionService.getTransactions();
        return transactions;
    }

    @RequestMapping("transaction/emetteur")
    public Optional<Transaction> getTransactionParEmetteur() {
        log.info("Recherche en cours, veuillez patienter...");
        log.info("Voici une liste de l'ensemble des transactions que vous avez émises:" + iTransactionService.getEchangeParEmetteur());
        Optional<Transaction> transaction = iTransactionService.getEchangeParEmetteur();
        return transaction;
    }
    @RequestMapping("transaction/destinataire")
    public Optional<Transaction> getTransactionParDestinataire() {
        log.info("Recherche en cours, veuillez patienter...");
        log.info("Voici une liste de l'ensemble des transactions que vous avez reçues:" + iTransactionService.getEchangeParDestinataire());
        Optional<Transaction> transaction = iTransactionService.getEchangeParDestinataire();
        return transaction;
    }
    @PostMapping("transaction/nouvelleTransaction")
    public ResponseEntity<?> creationTransaction(@RequestBody Transaction transaction) throws IOException, JSONException {
        log.info("Création du nouvel utilisateur, veuillez patienter...");
        iTransactionService.creationTransaction(transaction);
        ResponseEntity<?> creation = ResponseEntity.status(HttpStatus.CREATED).body(transaction);
        log.info("Une nouvelle transaction a été crée" + creation);
        return creation;
    }
}
