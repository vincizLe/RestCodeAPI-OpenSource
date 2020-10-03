package com.open.restcode.Domain.Repository;

import com.open.restcode.Domain.Model.BanckAccount;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface BankAccountRepository extends JpaRepository<BankAccount, Integer> {

    public Optional<BankAccount> findById(int id);
}
