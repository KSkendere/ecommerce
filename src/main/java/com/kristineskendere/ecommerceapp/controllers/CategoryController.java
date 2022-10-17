package com.kristineskendere.ecommerceapp.controllers;

import com.kristineskendere.ecommerceapp.models.Category;
import com.kristineskendere.ecommerceapp.services.CategoryService;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/api/ecommerce")
public class CategoryController {

    CategoryService categoryService;

    public CategoryController(CategoryService categoryService) {
        this.categoryService = categoryService;
    }
    @PostMapping(value = {"categories"})
    public void saveCategory(@RequestBody Category category){
        categoryService.saveCategory(category);
    }

    @GetMapping(value = {"categories"})
    public List<Category>getCategories(){
        return categoryService.getCategories();
    }

    @GetMapping(value = {"categories/{id}"})
    public Category getCategory(@PathVariable Long id){
        return categoryService.getCategory(id);
    }
}
