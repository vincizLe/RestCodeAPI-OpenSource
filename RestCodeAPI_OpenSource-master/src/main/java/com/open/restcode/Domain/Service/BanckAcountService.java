package com.open.restcode.Domain.Service;

import com.open.restcode.Domain.Model.BanckAccount;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;

public interface BanckAcountService {

    Page<BanckAccount> getAllBanckAcount();
    Page<BanckAccount> getAllBanckAccountsByuserId(int userId,Pageable pageable);
    BanckAccount getBanckAccountByIdAndUserId(int userId,int banckAccountId);
    BanckAccount createBanckAcount( int userId,BanckAccount banckAccount);
    BanckAccount getBanckAcountById(Integer id);
    ResponseEntity<?> deleteBanckAccount(int userId,int backAccountId);
}
