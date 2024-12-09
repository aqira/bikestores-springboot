package com.tutorial.bikestores.production.brand;

import com.tutorial.bikestores.shared.DropdownDTO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface BrandRepository extends JpaRepository<Brand, Integer> {

    Brand findByName(String name);

    Boolean existsByName(String name);

    Boolean existsByIdOrName(Integer id, String name);

    List<Brand> findByIdOrNameContaining(Integer id, String name);

    @Query("""
            SELECT new com.tutorial.bikestores.shared.DropdownDTO(b.id, b.name)
            FROM Brand b
            """)
    List<DropdownDTO> findBrandsDropdown();

}
