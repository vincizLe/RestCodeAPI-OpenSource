package com.open.restcode.Service;

import com.open.restcode.Domain.Model.BanckAccount;
import com.open.restcode.Domain.Repository.BanckAccountRepository;
import com.open.restcode.Domain.Repository.UserRepository;
import com.open.restcode.Domain.Service.BanckAcountService;
import com.open.restcode.Exeptions.ResourceNotFoundExeption;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class BanckAcountServiceImplement implements BanckAcountService {

    @Autowired
    private BanckAccountRepository banckAccountRepository;

    @Autowired
    private UserRepository userRepository;

    @Override
    public Page<BanckAccount> getAllBanckAcount() {
        return null;
    }

    @Override
    public BanckAccount getBanckAcountById(Integer id) {
        return banckAccountRepository.findById(id)
                .orElseThrow(()->new ResourceNotFoundExeption(
                        "BanckAcount"
                ));
    }

    @Override
    public Page<BanckAccount> getAllBanckAccountsByuserId(int userId, Pageable pageable) {
        return banckAccountRepository.findByUserId(userId,pageable);
    }

    @Override
    public BanckAccount getBanckAccountByIdAndUserId(int userId, int banckAccountId) {
        return banckAccountRepository.findByIdAndUserId(userId, banckAccountId)
                .orElseThrow(() -> new ResourceNotFoundExeption(
                        "Comment not found with Id " + banckAccountId +
                                " and UserId " + userId));
    }

    @Override
    public BanckAccount createBanckAcount(int userId, BanckAccount banckAccount) {
        return userRepository.findById(userId).map(user -> {
            banckAccount.setUser(user);
            return banckAccountRepository.save(banckAccount);
        }).orElseThrow(() -> new ResourceNotFoundExeption(
                "User", "Id", userId));
    }

    @Override
    public ResponseEntity<?> deleteBanckAccount(int userId, int  banckAccountId) {
        return banckAccountRepository.findByIdAndUserId(banckAccountId, userId).map(banckAccount -> {
            banckAccountRepository.delete(banckAccount);
            return ResponseEntity.ok().build();
        }).orElseThrow(() -> new ResourceNotFoundExeption(
                "Comment not found with Id " + banckAccountId + " and PostId " + userId));
    }
}
