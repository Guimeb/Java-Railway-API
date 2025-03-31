package me.dio.api.controller;

import java.net.URI;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import me.dio.api.domain.model.Account;
import me.dio.api.service.AccountService;

@RestController
@RequestMapping("/accounts")
public class AccountController {

    private final AccountService accountService;

    public AccountController(AccountService accountService) {
         this.accountService = accountService;
    }

    @GetMapping("/{id}")
    public ResponseEntity<Account> findById(@PathVariable Long id) {
         Account account = accountService.findById(id);
         return ResponseEntity.ok(account);
    }
    
    @PostMapping
    public ResponseEntity<Account> create(@RequestBody Account account) {
         // Certifique-se de que o objeto Account venha com uma referência ao usuário (com ao menos o id)
         Account createdAccount = accountService.create(account);
         URI location = ServletUriComponentsBuilder.fromCurrentRequest()
                 .path("/{id}")
                 .buildAndExpand(createdAccount.getId())
                 .toUri();
         return ResponseEntity.created(location).body(createdAccount);
    }
    
    @PutMapping("/{id}")
    public ResponseEntity<Account> update(@PathVariable Long id, @RequestBody Account account) {
         Account updatedAccount = accountService.update(id, account);
         return ResponseEntity.ok(updatedAccount);
    }
    
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
         accountService.delete(id);
         return ResponseEntity.noContent().build();
    }
}
