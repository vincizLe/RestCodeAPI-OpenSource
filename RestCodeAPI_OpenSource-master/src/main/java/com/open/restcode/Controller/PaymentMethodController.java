package com.open.restcode.Controller;


import com.open.restcode.Domain.Model.PaymentMethod;
import com.open.restcode.Domain.Repository.PaymentMethodRepository;
import com.open.restcode.Exeptions.ResourceNotFoundExeption;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@Tag(name = "PAYMENT METHODS",description = "RestCode API")
@RestController
@RequestMapping("/RestCode")
public class PaymentMethodController {

    @Autowired
    private PaymentMethodRepository paymentMethodRepository;

    @GetMapping("/paymentMethods")
    public List<PaymentMethod> getAllPaymentMethod(){
        return paymentMethodRepository.findAll();

    }

    @PostMapping("/paymentMethods")
    public PaymentMethod createPaymenthod(@Valid @RequestBody PaymentMethod paymentMethod){
        return paymentMethodRepository.save(paymentMethod);
    }

    @PutMapping("/paymentMethods/{paymentmethodId}")
    public PaymentMethod updatePaymentMethodo(@PathVariable Integer paymentmethodId, @RequestBody PaymentMethod paymentMethodRequest){
        return paymentMethodRepository.findById(paymentmethodId)
                .map(paymentMethod -> {
                    paymentMethod.setBankName(paymentMethodRequest.getBankName());
                   paymentMethod.setNumber(paymentMethodRequest.getNumber());
                   return paymentMethodRepository.save(paymentMethod);
                }).orElseThrow(()->new ResourceNotFoundExeption(
                        "User", "Name", "PaymenMethodId" + paymentmethodId + "not found"
                ));
    }

    public ResponseEntity<?> deletePaymentMethod(@PathVariable Integer paymentMethodId){
        return paymentMethodRepository.findById(paymentMethodId).map(PaymentMethod->{
            paymentMethodRepository.delete(PaymentMethod);
            return ResponseEntity.ok().build();
        }).orElseThrow(()->new ResourceNotFoundExeption(
                "User", "Name", "PaymenthMethodId" + paymentMethodId + "not found "
        ));
    }
}
