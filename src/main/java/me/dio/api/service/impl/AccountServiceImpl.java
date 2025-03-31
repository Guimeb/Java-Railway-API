package me.dio.api.service.impl;

import java.util.NoSuchElementException;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import me.dio.api.domain.model.Account;
import me.dio.api.domain.model.User;
import me.dio.api.domain.repository.AccountRepository;
import me.dio.api.domain.repository.UserRepository;
import me.dio.api.service.AccountService;

@Service
public class AccountServiceImpl implements AccountService {

    private final AccountRepository accountRepository;
    private final UserRepository userRepository;

    public AccountServiceImpl(AccountRepository accountRepository,
                              UserRepository userRepository) {
        this.accountRepository = accountRepository;
        this.userRepository = userRepository;
    }

    @Override
    public Account findById(Long id) {
        return accountRepository.findById(id)
                .orElseThrow(() -> new NoSuchElementException("Account not found with id " + id));
    }

    @Override
    @Transactional
    public Account create(Account account) {
        // Verifica se a conta possui um usuário associado com id
        if (account.getUser() == null || account.getUser().getId() == null) {
            throw new IllegalArgumentException("Account must have an associated user with a valid id.");
        }
        // Busca o usuário completo a partir do id informado
        User user = userRepository.findById(account.getUser().getId())
                .orElseThrow(() -> new NoSuchElementException("User not found with id " + account.getUser().getId()));
        account.setUser(user);
        return accountRepository.save(account);
    }

    @Override
    @Transactional
    public Account update(Long id, Account accountData) {
        // Busca a conta existente
        Account existingAccount = accountRepository.findById(id)
                .orElseThrow(() -> new NoSuchElementException("Account not found with id " + id));
        
        // Atualiza os campos desejados
        existingAccount.setAgency(accountData.getAgency());
        existingAccount.setBalance(accountData.getBalance());
        existingAccount.setCredit_limit(accountData.getCredit_limit());
        existingAccount.setNumber(accountData.getNumber());
        // Se desejar permitir atualizar o usuário associado, verifique e atualize:
        if (accountData.getUser() != null && accountData.getUser().getId() != null) {
            User user = userRepository.findById(accountData.getUser().getId())
                    .orElseThrow(() -> new NoSuchElementException("User not found with id " + accountData.getUser().getId()));
            existingAccount.setUser(user);
        }
        return accountRepository.save(existingAccount);
    }

    @Override
    @Transactional
    public void delete(Long id) {
        Account existingAccount = accountRepository.findById(id)
                .orElseThrow(() -> new NoSuchElementException("Account not found with id " + id));
        // Se a conta estiver associada a um usuário, remova-a da lista do usuário (caso o relacionamento seja bidirecional)
        if (existingAccount.getUser() != null && existingAccount.getUser().getAccounts() != null) {
            existingAccount.getUser().getAccounts().remove(existingAccount);
        }
        accountRepository.delete(existingAccount);
    }
}
