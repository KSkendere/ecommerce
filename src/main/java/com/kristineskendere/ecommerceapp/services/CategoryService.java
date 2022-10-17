package com.kristineskendere.ecommerceapp.services;

import com.kristineskendere.ecommerceapp.models.Category;

import java.util.List;

public interface CategoryService {
    void saveCategory(Category category);

    Category getCategory(Long id);

    List<Category> getCategories();
}
