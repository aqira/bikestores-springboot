package com.tutorial.bikestores.production.category;

import org.springframework.data.domain.Page;

import java.util.ArrayList;
import java.util.List;

public interface CategoryService {
    List<CategoryDTO> getAllCategories();

    void saveCategory(CategoryDTO categoryDTO);

    CategoryDTO getCategoryById(Integer id);

    void deleteCategoryById(Integer id);

    Page<CategoryDTO> getPageCategories(String name, int pageNumber);
}
