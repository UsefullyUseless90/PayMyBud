package com.paymybuddy.paymybuddy.repositories;

import com.paymybuddy.paymybuddy.models.Transaction;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TransactionRepository extends CrudRepository<Transaction, Integer> {
}
