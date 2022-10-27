package com.kristineskendere.ecommerceapp.web;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.kristineskendere.ecommerceapp.dtos.ProductDto;
import com.kristineskendere.ecommerceapp.dtos.PurchaseDto;
import com.kristineskendere.ecommerceapp.jwt.JwtTokenUtil;
import com.kristineskendere.ecommerceapp.models.Address;
import com.kristineskendere.ecommerceapp.models.Customer;
import com.kristineskendere.ecommerceapp.models.Order;
import com.kristineskendere.ecommerceapp.models.OrderItem;
import com.kristineskendere.ecommerceapp.repositories.CategoryRepository;
import com.kristineskendere.ecommerceapp.repositories.ProductRepository;
import com.kristineskendere.ecommerceapp.repositories.authRepositories.UserRepository;
import com.kristineskendere.ecommerceapp.services.CheckoutService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.http.MediaType;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.web.servlet.MockMvc;

import javax.transaction.Transactional;
import java.math.BigDecimal;
import java.util.HashSet;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@Sql(statements = {
        "INSERT INTO Product_category(id,category_name)VALUES(1,'Category Name')",
        "INSERT INTO Product(id,category_id,sku,name,description,unit_price,image_url, active,units_in_stock,date_created,last_updated) VALUES(2,1,'AC','TEST','Description',14.99, 'image_url', true,10,null,null)",

})

@AutoConfigureMockMvc
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class CheckoutIT<customer> {

    @Autowired
    private MockMvc mockMvc;
    @Autowired
    private ProductRepository productRepository;
    @Autowired
    private CategoryRepository categoryRepository;
    @Autowired
    private CheckoutService checkoutService;
    @MockBean
    private JwtTokenUtil jwtTokenUtil;
    @MockBean
    private UserRepository userRepository;
    @LocalServerPort
    private int port;
    private String baseUrl = "http://localhost";

    @BeforeEach
    void setup() {
        baseUrl = baseUrl.concat(":").concat(port + "").concat("/api/ecommerce/purchase");
    }

    @Test
    @Transactional
    void testPlaceOrder() throws Exception {

        assertEquals(1, productRepository.findAll().size());

        PurchaseDto purchaseDto = createPurchaseDto();

        mockMvc.perform(post(baseUrl)
                .with(csrf())
                .content(asJsonString(purchaseDto))
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());

    }
private PurchaseDto createPurchaseDto(){
    PurchaseDto purchaseDto = new PurchaseDto();

    Customer customer = createCustomer();
    Address shippingAddress = createShippingAddress();
    Address billingAddress = createBillingAddress();
    Order order = createOrder();
    Set<OrderItem> orderItems = createOrderItems();
    purchaseDto.setCustomer(customer);
    purchaseDto.setShippingAddress(shippingAddress);
    purchaseDto.setBillingAddress(billingAddress);
    purchaseDto.setOrder(order);
    purchaseDto.setOrderItems(orderItems);
        return purchaseDto;
}



    private Customer createCustomer() {
        Customer customer = new Customer();
        customer.setFirstName("John");
        customer.setLastName("Doe");
        customer.setEmail("john@inbox.lv");
        return customer;
    }

    private Address createShippingAddress() {
        Address shippingAddress = new Address();
        shippingAddress.setStreet("TestStreet");
        shippingAddress.setCity("TestCity");
        shippingAddress.setState("TestState");
        shippingAddress.setCountry("TestCountry");
        shippingAddress.setZipCode("TestZipCode");
        return shippingAddress;
    }

    private Address createBillingAddress() {
        Address billingAddress = new Address();
        billingAddress.setStreet("TestStreet");
        billingAddress.setCity("TestCity");
        billingAddress.setState("TestState");
        billingAddress.setCountry("TestCountry");
        billingAddress.setZipCode("TestZipCode");
        return billingAddress;
    }

    private Order createOrder() {
        Order order = new Order();
        order.setTotalQuantity(1);
        order.setTotalPrice(BigDecimal.valueOf(14.99));
        return order;
    }

    private OrderItem createOrderItem() {
        OrderItem orderItem = new OrderItem();
        orderItem.setUnitPrice(BigDecimal.valueOf(14.99));
        orderItem.setImageUrl("image_url");
        orderItem.setQuantity(1);
        orderItem.setProductId(2L);
        return orderItem;
    }

    private Set<OrderItem> createOrderItems() {
        Set<OrderItem> orderItems = new HashSet<>();
        orderItems.add(createOrderItem());
        return orderItems;
    }

    public static String asJsonString(final Object object) {
        try {
            return new ObjectMapper().writeValueAsString(object);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}






