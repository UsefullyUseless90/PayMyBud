package com.paymybuddy.paymybuddy.services;

import com.paymybuddy.paymybuddy.models.Transaction;

import java.util.Optional;

public interface ITransactionService {

    Iterable<Transaction> getTransactions();
    Optional<Transaction> getEchangeParEmetteur();
    Optional<Transaction> getEchangeParDestinataire();

    Transaction creationTransaction(Transaction transaction);
}
