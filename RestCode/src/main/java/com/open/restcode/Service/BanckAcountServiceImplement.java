package com.open.restcode.Service;

import com.open.restcode.Domain.Model.BanckAccount;
import com.open.restcode.Domain.Repository.BanckAccountRepository;
import com.open.restcode.Domain.Service.BanckAcountService;
import com.open.restcode.Exeptions.ResourceNotFoundExeption;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BanckAcountServiceImplement implements BanckAcountService {

    @Autowired
    private BanckAccountRepository banckAccountRepository;


    @Override
    public Page<BanckAccount> getAllBanckAcount() {
        return null;
    }

    @Override
    public BanckAccount createBanckAcount(BanckAccount banckAccount) {
        return banckAccountRepository.save(banckAccount);
    }

    @Override
    public BanckAccount getBanckAcountById(Integer id) {
        return banckAccountRepository.findById(id)
                .orElseThrow(()->new ResourceNotFoundExeption(
                        "BanckAcount", "Id", id
                ));
    }
}
