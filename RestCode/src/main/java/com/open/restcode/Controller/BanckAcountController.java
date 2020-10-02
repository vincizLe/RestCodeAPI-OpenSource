package com.open.restcode.Controller;


import com.open.restcode.Domain.Model.BanckAccount;
import com.open.restcode.Domain.Repository.BanckAccountRepository;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/RestCode")
public class BanckAcountController {

    private BanckAccountRepository banckAccountRepository;

    @GetMapping("/banckAcounts")
    public List<BanckAccount> getAllBanckAcount(){
        return banckAccountRepository.findAll();
    }


}
