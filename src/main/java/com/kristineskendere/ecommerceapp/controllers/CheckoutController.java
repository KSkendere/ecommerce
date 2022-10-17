package com.kristineskendere.ecommerceapp.controllers;

import com.kristineskendere.ecommerceapp.dtos.PurchaseDto;
import com.kristineskendere.ecommerceapp.dtos.PurchaseResponseDto;
import com.kristineskendere.ecommerceapp.services.CheckoutService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/api/ecommerce")
public class CheckoutController {

    private CheckoutService checkoutService;

    public CheckoutController(CheckoutService checkoutService) {
        this.checkoutService = checkoutService;
    }

    @PostMapping("/purchase")

    public ResponseEntity<PurchaseResponseDto> placeOrder(@RequestBody PurchaseDto purchaseDto){
        PurchaseResponseDto purchaseResponseDto = checkoutService.placeOrder(purchaseDto);
        return ResponseEntity.ok(purchaseResponseDto);
    }
}
