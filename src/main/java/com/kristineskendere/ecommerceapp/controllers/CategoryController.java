package com.kristineskendere.ecommerceapp.controllers;

import com.kristineskendere.ecommerceapp.dtos.CategoryDto;
import com.kristineskendere.ecommerceapp.exceptions.RecordNotFoundException;
import com.kristineskendere.ecommerceapp.services.CategoryService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.lang.NonNull;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/ecommerce")
public class CategoryController {

    CategoryService categoryService;

    public CategoryController(CategoryService categoryService) {
        this.categoryService = categoryService;
    }

    @PostMapping(value = {"categories"})
    public ResponseEntity<CategoryDto> saveCategory(@Valid @RequestBody CategoryDto categoryDto) {
        CategoryDto savedCategoryDto = categoryService.saveCategory(categoryDto);
        return ResponseEntity.status(HttpStatus.CREATED).body(savedCategoryDto);
    }

    @GetMapping(value = {"categories"})
    public ResponseEntity<List<CategoryDto>> getCategories() {
        List<CategoryDto> categoriesDto = categoryService.getCategories();
        return ResponseEntity.ok(categoriesDto);
    }

    @GetMapping(value = {"categories/{id}"})
    public ResponseEntity<CategoryDto> getCategory(@NonNull @PathVariable Long id) throws RecordNotFoundException {
        CategoryDto categoryDto = categoryService.getCategory(id);
        return ResponseEntity.ok(categoryDto);
    }

    @DeleteMapping(value = {"categories/{id}"})
    public ResponseEntity<CategoryDto> deleteCategory(@NonNull @PathVariable Long id) throws RecordNotFoundException {
        CategoryDto categorydto = categoryService.getCategory(id);
        categoryService.deleteCategory(id);
        return ResponseEntity.ok(categorydto);
    }

}
