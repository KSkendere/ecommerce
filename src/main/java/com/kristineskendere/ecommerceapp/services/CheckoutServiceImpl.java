package com.kristineskendere.ecommerceapp.services;

import com.kristineskendere.ecommerceapp.dtos.PurchaseDto;
import com.kristineskendere.ecommerceapp.dtos.PurchaseResponseDto;
import com.kristineskendere.ecommerceapp.models.Customer;
import com.kristineskendere.ecommerceapp.models.Order;
import com.kristineskendere.ecommerceapp.models.OrderItem;
import com.kristineskendere.ecommerceapp.repositories.CustomerRepository;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Set;
import java.util.UUID;

@Service
public class CheckoutServiceImpl implements CheckoutService {

    private CustomerRepository customerRepository;

    public CheckoutServiceImpl(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }

    @Override
    @Transactional
    public PurchaseResponseDto placeOrder(PurchaseDto purchaseDto) {
        //retrieve the order info from dto
        Order order = purchaseDto.getOrder();
        //generate tracking number
        String orderTrackingNumber = generateOrderTrackingNumber();
        order.setOrderTrackerNumber(orderTrackingNumber);
        //populate order with order items
        Set<OrderItem> orderItems = purchaseDto.getOrderItems();
        orderItems.forEach(order::add);
        //populate order with billingAddress and shippingAddress
        order.setBillingAddress(purchaseDto.getBillingAddress());
        order.setShippingAddress(purchaseDto.getShippingAddress());
        //populate customer with order
        Customer customer = purchaseDto.getCustomer();
        //check if this is an existing customer
        String email = customer.getEmail();
        Customer customerFromDb = customerRepository.findByEmail(email);
        if (customerFromDb != null) {
            customer = customerFromDb;
        }
            customer.addOrder(order);
            // save to the database
            customerRepository.save(customer);

        //return a response



        return new PurchaseResponseDto(orderTrackingNumber);
    }

    private String generateOrderTrackingNumber() {
        //unique id that is hard to guess and is random
        //generate random UUID number (UUID version-4)
        return UUID.randomUUID().toString();
    }
}
