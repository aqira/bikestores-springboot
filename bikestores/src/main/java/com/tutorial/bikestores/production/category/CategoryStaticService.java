package com.tutorial.bikestores.production.category;

import jakarta.persistence.EntityNotFoundException;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service("staticCategoryService")
public class CategoryStaticService implements CategoryService {

    private final static Map<Integer, Category> categories = new HashMap<>();

    static {
        categories.put(1, new Category(1, "Street Bike"));
        categories.put(2, new Category(2, "Mountain Bike"));
        categories.put(3, new Category(3, "Road Bike"));
        categories.put(4, new Category(4, "Family Bike"));
        categories.put(5, new Category(5, "Children Bike"));
        categories.put(6, new Category(6, "Electric Bike"));
    }

    private static int counter = categories.size();

    @Override
    public List<CategoryDTO> getAllCategories() {
        List<CategoryDTO> categoryDTOList = new ArrayList<>();
        for (Category category : categories.values()) {
            categoryDTOList.add(new CategoryDTO(category.getId(), category.getName()));
        }
        return categoryDTOList;
    }

    @Override
    public void saveCategory(CategoryDTO categoryDTO) {
        if (categoryDTO.getId() == null) {
            categoryDTO.setId(++counter);
        }
        categories.put(categoryDTO.getId(), new Category(
                categoryDTO.getId(),
                categoryDTO.getName())
        );
    }

    @Override
    public CategoryDTO getCategoryById(Integer id) {
        Category category = categories.get(id);
        if (category == null) throw new EntityNotFoundException("Category Not found by that ID");
        return new CategoryDTO(category.getId(), category.getName());
    }

    @Override
    public void deleteCategoryById(Integer id) {
        categories.remove(id);
    }

    @Override
    public Page<CategoryDTO> getPageCategories(String name, int pageNumber) {
        return null;
    }
}
