package com.paymybuddy.paymybuddy.services;

import com.paymybuddy.paymybuddy.models.Transaction;
import com.paymybuddy.paymybuddy.repositories.TransactionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class TransactionService implements ITransactionService {

    @Autowired
    TransactionRepository transactionRepository;
    @Autowired
    Transaction transaction;

    public Iterable<Transaction> getTransactions(){
        Iterable<Transaction> transactions = transactionRepository.findAll();
        return transactions;
    }
    public Optional<Transaction> getEchangeParEmetteur(){
        Optional<Transaction> echange = transactionRepository.findById(transaction.getEmbeddedTransaction().getEmetteur().getIdUtilisateur());
        return echange;
    }
    public Optional<Transaction> getEchangeParDestinataire(){
        Optional<Transaction> echange = transactionRepository.findById(transaction.getEmbeddedTransaction().getDestinataire().getIdUtilisateur());
        return echange;
    }
    public Transaction creationTransaction(Transaction transaction){
        transaction.setEmbeddedTransaction(transaction.getEmbeddedTransaction());
        transaction.setDescription(transaction.getDescription());
        transaction.setMontant(transaction.getMontant());
        return transaction;
    }

}
