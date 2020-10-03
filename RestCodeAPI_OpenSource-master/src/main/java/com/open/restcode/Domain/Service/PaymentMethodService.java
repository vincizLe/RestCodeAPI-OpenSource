package com.open.restcode.Domain.Service;

import com.open.restcode.Domain.Model.PaymentMethod;

public interface PaymentMethodService {

    PaymentMethod getPaymentMethodById(Integer id);
    PaymentMethod createPaymenthMethod(Integer id,PaymentMethod paymentMethod);
}
