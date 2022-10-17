package com.kristineskendere.ecommerceapp.services;

import com.kristineskendere.ecommerceapp.dtos.PurchaseDto;
import com.kristineskendere.ecommerceapp.dtos.PurchaseResponseDto;

public interface CheckoutService {

    PurchaseResponseDto placeOrder(PurchaseDto purchaseDto);
}
