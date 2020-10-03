package com.open.restcode.Domain.Repository;

import com.open.restcode.Domain.Model.BanckAccount;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.prefs.BackingStoreException;

public interface BanckAccountRepository extends JpaRepository<BanckAccount, Integer> {
    Page<BanckAccount> findByUserId(int userId, Pageable pageable);
    Optional<BanckAccount> findByIdAndUserId(int id,int userid);
    public Optional<BanckAccount> findById(int id);
}
