package com.open.restcode.Domain.Service;

import com.open.restcode.Domain.Model.BanckAccount;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface BankAcountService {

    Page<BankAccount> getAllBankAcount();
    BankAccount createBankAcount(BankAccount bankAccount);
    BankAccount getBankAcountById(Integer id);
}
