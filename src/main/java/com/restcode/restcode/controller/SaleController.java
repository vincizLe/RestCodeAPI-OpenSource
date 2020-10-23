package com.restcode.restcode.controller;

import com.restcode.restcode.domain.model.Sale;
import com.restcode.restcode.domain.service.ISaleService;
import com.restcode.restcode.resource.SaleResource;
import com.restcode.restcode.resource.SaveSaleResource;
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

@RestController
@RequestMapping("/api")
public class SaleController {
    @Autowired
    private ModelMapper mapper;

    @Autowired
    private ISaleService saleService;

    private SaleResource convertToResource(Sale entity){
        return mapper.map(entity,SaleResource.class);
    }

    private Sale convertToEntity(SaveSaleResource resource){
        return mapper.map(resource,Sale.class);
    }

    @GetMapping("/sales")
    public Page<SaleResource> getAllSales(Pageable pageable) {
        Page<Sale> salesPage = saleService.getAllSales(pageable);
        List<SaleResource> resources = salesPage.getContent()
                .stream().map(this::convertToResource)
                .collect(Collectors.toList());
        return new PageImpl<>(resources, pageable, resources.size());
    }
    @GetMapping("/sales/{saleId}")
    public SaleResource getSaleById(@PathVariable(value = "saleId") Long saleId) {
        return convertToResource(saleService.getSaleById(saleId));
    }

    @PostMapping("/sales")
    public SaleResource createSale(
            @Valid @RequestBody SaveSaleResource resource) {
        Sale sale = convertToEntity(resource);
        return convertToResource(saleService.createSale(sale));

    }

    @DeleteMapping("/sales/{saleId}")
    public ResponseEntity<?> deleteSale(@PathVariable Long saleId) {
        return saleService.deleteSale(saleId);
    }
}
