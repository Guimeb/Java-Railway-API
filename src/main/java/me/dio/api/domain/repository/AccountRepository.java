package me.dio.api.domain.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import me.dio.api.domain.model.Account;

@Repository
public interface AccountRepository extends JpaRepository<Account, Long> {
}
