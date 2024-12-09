package com.tutorial.bikestores.production.category;

import com.tutorial.bikestores.shared.DropdownDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface CategoryRepository extends JpaRepository<Category, Integer> {
    @Query("""
            SELECT new com.tutorial.bikestores.production.category.CategoryDTO(c.id, c.name)
            FROM Category c
            WHERE c.name LIKE %:name%
            """)
    Page<CategoryDTO> findAll(@Param("name") String name, Pageable pageable);

    @Query("""
            SELECT new com.tutorial.bikestores.shared.DropdownDTO(c.id, c.name)
            FROM Category c
            """)
    List<DropdownDTO> findCategoriesDropdown();
}
