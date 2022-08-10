package com.paymybuddy.paymybuddy.repositories;

import com.paymybuddy.paymybuddy.models.Transaction;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

@Repository
public interface TransactionRepository {

    List<Transaction> findTransactionByDate(@Param("date") Date date);
    List<Transaction> findTransactionByEmetteur(@Param("emetteur") String emetteur);
    List<Transaction> findTransactionByDestinataire(@Param("destinataire") String destinataire);
    List<Transaction> findTransactionByMontant(@Param("montant") Integer montant);
    List<Transaction> findTransactionByDescription(@Param("description") String description);
    List<Transaction> findAllTransactions();

}
