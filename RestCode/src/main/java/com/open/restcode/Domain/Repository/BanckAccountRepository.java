package com.open.restcode.Domain.Repository;

import com.open.restcode.Domain.Model.BanckAccount;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface BanckAccountRepository extends JpaRepository<BanckAccount, Integer> {

    public Optional<BanckAccount> findById(int id);
}
