package me.dio.api.service.impl;

import java.util.NoSuchElementException;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import me.dio.api.domain.model.Account;
import me.dio.api.domain.model.Transaction;
import me.dio.api.domain.repository.TransactionRepository;
import me.dio.api.service.TransactionService;

import org.springframework.transaction.annotation.Transactional;

import org.springframework.stereotype.Service;

@Service
public class TransactionServiceImpl implements TransactionService {

    private final TransactionRepository transactionRepository;

    // Injetando o EntityManager para buscar a conta
    @PersistenceContext
    private EntityManager entityManager;

    public TransactionServiceImpl(TransactionRepository transactionRepository) {
        this.transactionRepository = transactionRepository;
    }

    @Override
    @Transactional(readOnly = true)
    public Transaction findById(Long id) {
        return transactionRepository.findById(id)
                .orElseThrow(() -> new NoSuchElementException("Transaction not found with id " + id));
    }

    @Override
    public Transaction create(Transaction transaction) {
        if (transaction.getId() != null && transactionRepository.existsById(transaction.getId())) {
            throw new IllegalArgumentException("Transaction with id " + transaction.getId() + " already exists.");
        }
        // Verifica se a conta foi informada com um id
        if (transaction.getAccount() == null || transaction.getAccount().getId() == null) {
            throw new IllegalArgumentException("A valid account id must be provided.");
        }
        // Recupera a conta completa usando o EntityManager
        Long accountId = transaction.getAccount().getId();
        Account fullAccount = entityManager.find(Account.class, accountId);
        if (fullAccount == null) {
            throw new NoSuchElementException("Account not found with id " + accountId);
        }
        transaction.setAccount(fullAccount);

        return transactionRepository.save(transaction);
    }
}
