package me.dio.api.service;

import org.springframework.stereotype.Service;

import me.dio.api.domain.model.Transaction;
import me.dio.api.service.TransactionService;

@Service
public interface TransactionService {
    Transaction findById(Long id);

    Transaction create(Transaction transactionToCreate);
}
