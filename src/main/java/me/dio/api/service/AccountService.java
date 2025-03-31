package me.dio.api.service;

import me.dio.api.domain.model.Account;

public interface AccountService {
    Account findById(Long id);
    Account create(Account account);
    Account update(Long id, Account account);
    void delete(Long id);
}
