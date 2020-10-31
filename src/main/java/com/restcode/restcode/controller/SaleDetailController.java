package com.restcode.restcode.controller;

import com.restcode.restcode.domain.model.Sale;
import com.restcode.restcode.domain.model.SaleDetail;
import com.restcode.restcode.domain.service.ISaleDetailService;
import com.restcode.restcode.resource.SaleDetailResource;
import com.restcode.restcode.resource.SaleResource;
import com.restcode.restcode.resource.SaveSaleDetailResource;
import com.restcode.restcode.resource.SaveSaleResource;
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

@Tag(name="Sale-Details", description ="Sales Details API")
@RestController
@RequestMapping("/api")
public class SaleDetailController {
    @Autowired
    private ModelMapper mapper;

    @Autowired
    private ISaleDetailService saleDetailService;

    private SaleDetailResource convertToResource(SaleDetail entity){
        return mapper.map(entity, SaleDetailResource.class);
    }

    private SaleDetail convertToEntity(SaveSaleDetailResource resource){
        return mapper.map(resource,SaleDetail.class);
    }

    @Operation(summary="Get All Sale Details By Sale Id")
    @GetMapping("/sales/{saleId}/sale-details")
    public Page<SaleDetailResource> getAllSaleDetailsBySaleId (
            @PathVariable(value = "saleId") Long saleId, Pageable pageable){
        Page<SaleDetail> saleDetailsPage = saleDetailService.getAllSaleDetailsBySaleId(saleId,pageable);
        List<SaleDetailResource> resources = saleDetailsPage.getContent()
                .stream().map(this::convertToResource)
                .collect(Collectors.toList());
        return new PageImpl<>(resources, pageable, resources.size());
    }

    @Operation(summary="Get Sale Detail By Id And Sale Id")
    @GetMapping("/sales/{saleId}/sale-details/{saleDetailId}")
    public SaleDetailResource getSaleByIdAndSaleDetailId(
            @PathVariable(name = "saleId") Long saleId,
            @PathVariable(name = "saleDetailId") Long saleDetailId) {
        return convertToResource(saleDetailService.getSaleDetailByIdAndSaleId(saleId, saleDetailId));
    }

    @Operation(summary="Create Sale Detail")
    @PostMapping("/sales/{saleId}/sale-details")
    public SaleDetailResource createSaleDetail(
            @PathVariable(value = "saleId") Long saleId,
            @Valid @RequestBody SaveSaleDetailResource resource) {
        return convertToResource(saleDetailService.createSaleDetail(saleId, convertToEntity(resource)));
    }

    @Operation(summary="Delete Sale Detail")
    @DeleteMapping("/sales/{saleId}/sale-details/{saleDetailId}")
    public ResponseEntity<?> deleteSaleDetail(
            @PathVariable (value = "saleId") Long saleId,
            @PathVariable (value = "saleDetailId") Long saleDetailId) {
        return saleDetailService.deleteSaleDetail(saleId, saleDetailId);
    }

}
