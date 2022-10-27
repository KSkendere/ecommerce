package com.kristineskendere.ecommerceapp;

import org.junit.jupiter.api.Test;

import org.springframework.boot.test.context.SpringBootTest;


@SpringBootTest
class EcommerceAppApplicationTests {

//    @Test
//    void contextLoads() {
//    }
}

//    @LocalServerPort
//    private int port;
//
//    private String baseUrl = "http://localhost";
//@Autowired
//    private TestH2Repository testH2Repository;
//
//
//    @MockBean
//    private JwtTokenUtil jwtTokenUtil;
//    @MockBean
//    private UserRepository userRepository;
//
//    private static RestTemplate restTemplate;
//
//    @BeforeAll
//    public static void init() {
//        restTemplate = new RestTemplate();
//    }
//    @BeforeEach
//    public void setup() {
//        baseUrl = baseUrl.concat(":").concat(port + "").concat("/api/ecommerce/products");
//
//    }
//    @WithMockUser(username = "test", password = "test", roles = "admin")
//@Test
//
//    public void testAddProduct(){
//
//        System.out.println("ALL"+ testH2Repository.findAll());
//
////        Category category = new Category();
////        category.setId(1L);
////        category.setCategoryName("test category");
////
////        ProductDto productDto = new ProductDto();
////        productDto.setId(1L);
////        productDto.setName("test");
////        productDto.setActive(true);
////        productDto.setImageUrl("testImageUrl");
////        productDto.setDescription("test description");
////        productDto.setSku("testSku");
////        productDto.setUnitPrice(BigDecimal.valueOf(10));
////        productDto.setUnitPrice(BigDecimal.valueOf(10));
////        productDto.setCategory(category);
////
////        ProductDto response = restTemplate.postForObject(baseUrl + "/admin", productDto, ProductDto.class);
////
////        assertEquals("test", response.getName());
////        assertEquals(1,testH2Repository.findAll().size());
//
//    }
//
//    @WithMockUser(username = "test", password = "test", roles = "admin")
//            @Test
//            @Sql(statements ={
//                    "INSERT INTO product_category(id,category_name)VALUES(1,'Category Name')",
//                    "INSERT INTO PRODUCT(id,category_id,sku,name,description,unit_price,image_url, active,units_in_stock,date_created,last_updated) VALUES(1,1,'AC','TEST','Description',10.00, 'image_url', true,10,null,null)",
//                    "INSERT INTO PRODUCT(id,category_id,sku,name,description,unit_price,image_url, active,units_in_stock,date_created,last_updated) VALUES(2,1,'AC1','TEST1','Description1',10.00, 'image_url1', true,10,null,null)"
//                    },
//                    executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD)
//            public void testGetProducts(){
//
//        Page <ProductDto> products= restTemplate.getForObject(baseUrl + "/?pageNo=0&pageSize=5", Page.class);
//
//
//
//                assertEquals(1,testH2Repository.findAll().size());
//            }
//
//    @Test
//    @Sql(statements ={
//            "INSERT INTO product_category(id,category_name)VALUES(1,'Category Name')",
//            "INSERT INTO PRODUCT(id,category_id,sku,name,description,unit_price,image_url, active,units_in_stock,date_created,last_updated) VALUES(1,1,'AC','TEST','Description',10.00, 'image_url', true,10,null,null)",
//            "INSERT INTO PRODUCT(id,category_id,sku,name,description,unit_price,image_url, active,units_in_stock,date_created,last_updated) VALUES(2,1,'AC1','TEST1','Description1',10.00, 'image_url1', true,10,null,null)"
//    },
//            executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD)
//    public void testGetProduct(){
//
//        ProductDto productResponse = restTemplate.getForObject(baseUrl + "/1", ProductDto.class);
//
//
//        assertEquals(2,testH2Repository.findAll().size());
//    }
//}
