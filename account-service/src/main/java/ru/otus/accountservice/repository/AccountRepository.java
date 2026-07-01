package ru.otus.accountservice.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.otus.accountservice.aggregate.entity.Account;
import ru.otus.accountservice.aggregate.entity.AccountStatus;

import java.util.List;
import java.util.Optional;

/**
 * @author: URUNOV Khamdamboy
 * @date 29.06.2026
 * @Project: account-service
 */
@Repository
public interface AccountRepository extends JpaRepository<Account, Long> {
    Optional<Account> findByAccountNumber(String accountNumber);
    List<Account> findByStatus(AccountStatus status);
    List<Account> findByOwnerName(String ownerName);
}
