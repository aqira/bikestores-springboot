package com.tutorial.bikestores.production.category;

import jakarta.persistence.EntityNotFoundException;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service("dbCategoryService")
@AllArgsConstructor
public class CategoryDbService implements CategoryService {

    private final CategoryRepository categoryRepository;
    private final ModelMapper modelMapper;
    private final static Integer PAGE_SIZE = 5;

    @Override
    public List<CategoryDTO> getAllCategories() {
        List<CategoryDTO> categoryDTOList = new ArrayList<>();
        for (Category category : categoryRepository.findAll()) {
            categoryDTOList.add(modelMapper.map(category, CategoryDTO.class));
        }
        return categoryDTOList;
    }

    public Page<CategoryDTO> getPageCategories(String name, int pageNumber){
        Pageable pageable = PageRequest.of(pageNumber - 1, PAGE_SIZE);
        return categoryRepository.findAll(name, pageable);
    }

    @Override
    public void saveCategory(CategoryDTO categoryDTO) {
        categoryRepository.save(modelMapper.map(categoryDTO, Category.class));
    }

    @Override
    public CategoryDTO getCategoryById(Integer id) {
        Optional<Category> optionalCategory = categoryRepository.findById(id);
        Category category = optionalCategory
                .orElseThrow(() -> new EntityNotFoundException("Category Not found by that ID"));

        return modelMapper.map(category, CategoryDTO.class);
    }

    @Override
    public void deleteCategoryById(Integer id) {
        categoryRepository.deleteById(id);
    }
}
