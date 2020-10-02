package com.open.restcode.Domain.Service;

import com.open.restcode.Domain.Model.BanckAccount;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface BanckAcountService {

    Page<BanckAccount> getAllBanckAcount();
    BanckAccount createBanckAcount(BanckAccount banckAccount);
    BanckAccount getBanckAcountById(Integer id);
}
