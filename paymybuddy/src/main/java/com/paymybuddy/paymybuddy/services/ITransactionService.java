package com.paymybuddy.paymybuddy.services;

import com.paymybuddy.paymybuddy.models.dao.TransactionDAO;
import com.paymybuddy.paymybuddy.models.dto.TransactionDTO;

import java.util.List;
import java.util.Optional;

public interface ITransactionService {

    Iterable<TransactionDAO> getTransactions();
    Optional<TransactionDAO> getEchangeParEmetteur(Integer idUtilisateur);
    Optional<TransactionDAO> getEchangeParDestinataire(Integer idUtilisateur);
    TransactionDTO creationTransaction(TransactionDTO transactionDTO);
    List<TransactionDAO> getTransactionsById(Integer idUtilisateur);
}
