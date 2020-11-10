package com.restcode.restcode.controller;

import com.restcode.restcode.domain.model.Sale;
import com.restcode.restcode.domain.model.SaleDetail;
import com.restcode.restcode.domain.service.ISaleService;
import com.restcode.restcode.resource.*;
import io.swagger.v3.oas.annotations.Operation;
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

@Tag(name="Sales", description ="Sales API")
@RestController
@RequestMapping("/api")
public class SaleController {
    @Autowired
    private ModelMapper mapper;

    @Autowired
    private ISaleService saleService;

    private SaleResource convertToResource(Sale entity) {
        return mapper.map(entity, SaleResource.class);
    }

    private Sale convertToEntity(SaveSaleResource resource) {
        return mapper.map(resource, Sale.class);
    }

    @Operation(summary = "Get All Sales By Restaurant")
    @GetMapping("/restaurants/{restaurantId}/sales")
    public Page<SaleResource> getAllSalesByRestaurantId(
            @PathVariable(value = "restaurantId") Long restaurantId, Pageable pageable) {
        Page<Sale> salesPage = saleService.getAllSalesByRestaurantId(restaurantId, pageable);
        List<SaleResource> resources = salesPage.getContent()
                .stream().map(this::convertToResource)
                .collect(Collectors.toList());
        return new PageImpl<>(resources, pageable, resources.size());
    }

    @Operation(summary="Get Sale By Id")
    @GetMapping("/sales/{saleId}")
    public SaleResource getSaleById(@PathVariable(value = "saleId") Long saleId) {
        return convertToResource(saleService.getSaleById(saleId));
    }

    @Operation(summary="Create Sale")
    @PostMapping("restaurants/{restaurantId}/sales")
    public SaleResource createSale(
            @PathVariable(value = "restaurantId") Long restaurantId,
            @Valid @RequestBody SaveSaleResource resource) {
        return convertToResource(saleService.createSale(restaurantId,convertToEntity(resource)));
    }

    @Operation(summary="Delete Sale")
    @DeleteMapping("/sales/{saleId}")
    public ResponseEntity<?> deleteSale(@PathVariable Long saleId) {
        return saleService.deleteSale(saleId);
    }


}
