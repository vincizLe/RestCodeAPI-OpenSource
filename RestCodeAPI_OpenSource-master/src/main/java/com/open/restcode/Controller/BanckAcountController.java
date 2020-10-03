package com.open.restcode.Controller;


import com.open.restcode.Domain.Model.BanckAccount;
import com.open.restcode.Domain.Model.User;
import com.open.restcode.Domain.Repository.BanckAccountRepository;
import com.open.restcode.Domain.Service.BanckAcountService;
import com.open.restcode.Domain.Service.UserService;
import com.open.restcode.Resource.BanckAcountResource;
import com.open.restcode.Resource.SaveBanckAcountResource;
import com.open.restcode.Resource.SaveUserResource;
import com.open.restcode.Resource.UserResource;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.stream.Collectors;

@Tag(name = "Banck Acount",description = "RestCode API")
@RestController
@RequestMapping("/RestCode")
public class BanckAcountController {


    @Autowired
    private ModelMapper mapper;

    @Autowired
    private BanckAcountService banckAcountService;

    private BanckAccount convertToEntity(SaveBanckAcountResource resource) {

        return mapper.map(resource, BanckAccount.class);
    }

    private BanckAcountResource convertToResource(BanckAccount entity) {

        return mapper.map(entity, BanckAcountResource.class);
    }

    @GetMapping("/user{userId}/banckAccount")
    public Page<BanckAcountResource> getAllbanckAccountByUserId(
            @PathVariable (value = "userId") int userId,
            Pageable pageable) {
        Page<BanckAccount> commentPage = banckAcountService.getAllBanckAccountsByuserId(userId, pageable);
        List<BanckAcountResource> resources = commentPage.getContent().stream()
                .map(this::convertToResource).collect(Collectors.toList());
        return new PageImpl<>(resources, pageable, resources.size());
    }
    @PostMapping("/user{userId}/banckAccount")
    public BanckAcountResource createBackAccount(
            @PathVariable(value = "userId") int userId,
            @Valid @RequestBody SaveBanckAcountResource resource) {
        return convertToResource(banckAcountService.createBanckAcount(userId,
                convertToEntity(resource)));
    }

    @DeleteMapping("/user{userId}/banckAccount/{banckAccountId}")
    public ResponseEntity<?> deleteBanckAcount(
            @PathVariable (value = "userId") int userId,
            @PathVariable (value = "banckAccountId") int banckAcountId) {
        return banckAcountService.deleteBanckAccount(userId, banckAcountId);
    }

}
